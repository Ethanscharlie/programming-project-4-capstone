package io.github.ethanscharlie;

/*
 * Team name: AEB
 * Team members: Alejandro Pasillas, Ethan Hadley, Ben Paul
 * Course and section: CS 2430-502
 * Project name: Programming Project 4 - Spring 2026
 * Primary author for this file: Ben Paul
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // The four reporting checkpoints required by Part 4.
    private static final int[] CHECKPOINTS = {1_000, 10_000, 100_000, 1_000_000};
    // Each jail strategy must be run independently ten times.
    private static final int RUNS_PER_STRATEGY = 10;
    // All generated evidence files are written under a single output root.
    private static final Path OUTPUT_DIR = Path.of("output");

    public static void main(String[] args) throws Exception {
        Files.createDirectories(OUTPUT_DIR);

        // Collect summary statistics while the datasets are being generated so we do not
        // have to re-read every CSV file afterward.
        var report = new BatchReport();
        for (var strategy : JailStrategy.values()) {
            for (int runNumber = 1; runNumber <= RUNS_PER_STRATEGY; runNumber++) {
                var snapshots = runSimulation(strategy);
                writeRunOutputs(strategy, runNumber, snapshots);
                report.addRun(strategy, runNumber, snapshots);
            }
        }

        report.writeSummary(OUTPUT_DIR.resolve("report_summary.md"));
        report.writeDatasetManifest(OUTPUT_DIR.resolve("dataset_manifest.txt"));

        System.out.printf(
                "Generated %d datasets and report summary in %s%n",
                JailStrategy.values().length * RUNS_PER_STRATEGY * CHECKPOINTS.length,
                OUTPUT_DIR.toAbsolutePath()
        );
    }

    private static Map<Integer, Snapshot> runSimulation(JailStrategy strategy) throws Exception {
        // We instantiate the board and player directly so a single 1,000,000-turn run can
        // yield all four checkpoints. That avoids rerunning the first 1,000/10,000/100,000
        // turns separately for the same dataset family.
        var board = new Board();
        var player = new Player(board, strategy);
        var snapshots = new LinkedHashMap<Integer, Snapshot>();

        int checkpointIndex = 0;
        int finalTurn = CHECKPOINTS[CHECKPOINTS.length - 1];
        for (int turn = 1; turn <= finalTurn; turn++) {
            player.takeTurn();

            // Capture the entire board state exactly at the requested reporting milestones.
            if (turn == CHECKPOINTS[checkpointIndex]) {
                snapshots.put(turn, Snapshot.capture(board, turn));
                checkpointIndex++;
                if (checkpointIndex == CHECKPOINTS.length) {
                    break;
                }
            }
        }

        return snapshots;
    }

    private static void writeRunOutputs(
            JailStrategy strategy,
            int runNumber,
            Map<Integer, Snapshot> snapshots
    ) throws IOException {
        // Each strategy gets its own directory, then each run gets a stable run_XX folder
        // so Alejandro can cite individual datasets in the report or screencast.
        var strategyDir = OUTPUT_DIR.resolve(strategyDirectoryName(strategy));
        var runDir = strategyDir.resolve(String.format("run_%02d", runNumber));
        Files.createDirectories(runDir);

        for (var snapshot : snapshots.values()) {
            // Every CSV is self-describing: strategy, run number, checkpoint size, and then
            // the 40-square table used for later analysis.
            var outputFile = runDir.resolve(String.format("n_%d.csv", snapshot.turns));
            try (var writer = new PrintWriter(Files.newBufferedWriter(outputFile))) {
                writer.printf("strategy,%s%n", strategy);
                writer.printf("run,%d%n", runNumber);
                writer.printf("turns,%d%n", snapshot.turns);
                writer.printf("total_landings_recorded,%d%n", snapshot.totalLandings());
                writer.println("index,square,count,percentage");

                for (var square : snapshot.squares) {
                    writer.printf(
                            "%d,%s,%d,%.8f%n",
                            square.index,
                            square.name,
                            square.count,
                            square.percentage
                    );
                }
            }
        }
    }

    private static String strategyDirectoryName(JailStrategy strategy) {
        return switch (strategy) {
            case ImmediateExit -> "strategy_a_immediate_exit";
            case TryForDoubles -> "strategy_b_try_for_doubles";
        };
    }

    private static final class SquareResult {
        private final int index;
        private final String name;
        private final int count;
        private final double percentage;

        private SquareResult(int index, String name, int count, double percentage) {
            this.index = index;
            this.name = name;
            this.count = count;
            this.percentage = percentage;
        }
    }

    private static final class Snapshot {
        private final int turns;
        private final List<SquareResult> squares;

        private Snapshot(int turns, List<SquareResult> squares) {
            this.turns = turns;
            this.squares = squares;
        }

        private static Snapshot capture(Board board, int turns) {
            // Snapshot is a detached copy of the landing table at a specific turn count.
            // That makes it safe to keep sampling while the live board continues mutating.
            var squares = new ArrayList<SquareResult>(board.getAmountOfSquares());
            for (int i = 0; i < board.getAmountOfSquares(); i++) {
                var square = board.getSquareAtLocation(i);
                squares.add(new SquareResult(i, square.name, square.landingCount, square.landingCount / (double) turns));
            }
            return new Snapshot(turns, squares);
        }

        private int totalLandings() {
            int total = 0;
            for (var square : squares) {
                total += square.count;
            }
            return total;
        }

        private List<SquareResult> topSquares(int limit) {
            // The report only needs the highest-frequency squares for quick comparison.
            var sorted = new ArrayList<>(squares);
            Collections.sort(sorted, (a, b) -> {
                if (b.count != a.count) return Integer.compare(b.count, a.count);
                return a.name.compareTo(b.name);
            });
            return sorted.subList(0, Math.min(limit, sorted.size()));
        }
    }

    private static final class BatchReport {
        private final Map<JailStrategy, Map<Integer, List<Snapshot>>> snapshotsByStrategyAndTurn =
                new HashMap<>();

        private BatchReport() {
            // Pre-build the report buckets so each run can be appended directly at collection time.
            for (var strategy : JailStrategy.values()) {
                var byTurn = new LinkedHashMap<Integer, List<Snapshot>>();
                for (var checkpoint : CHECKPOINTS) {
                    byTurn.put(checkpoint, new ArrayList<>());
                }
                snapshotsByStrategyAndTurn.put(strategy, byTurn);
            }
        }

        private void addRun(JailStrategy strategy, int runNumber, Map<Integer, Snapshot> snapshots) {
            for (var checkpoint : CHECKPOINTS) {
                var snapshot = snapshots.get(checkpoint);
                if (snapshot == null) {
                    throw new IllegalStateException("Missing snapshot for run " + runNumber + " at n=" + checkpoint);
                }
                snapshotsByStrategyAndTurn.get(strategy).get(checkpoint).add(snapshot);
            }
        }

        private void writeSummary(Path summaryFile) throws IOException {
            // This markdown file is meant to be copied into the formal report with minimal
            // cleanup, so it emphasizes tables rather than raw prose.
            try (var writer = new PrintWriter(Files.newBufferedWriter(summaryFile))) {
                writer.println("# Monopoly Simulation Batch Summary");
                writer.println();
                writer.printf(
                        "Generated %d runs for each strategy across checkpoints %s.%n",
                        RUNS_PER_STRATEGY,
                        Arrays.toString(CHECKPOINTS)
                );
                writer.println();

                writeConvergenceSection(writer);
                writeTopSquaresSection(writer);
                writeStrategyComparisonSection(writer);
            }
        }

        private void writeDatasetManifest(Path manifestFile) throws IOException {
            // The manifest is a simple evidence artifact proving that all 80 requested
            // datasets were generated and where each one lives.
            try (var writer = new PrintWriter(Files.newBufferedWriter(manifestFile))) {
                int datasetCount = 0;
                for (var strategy : JailStrategy.values()) {
                    for (int runNumber = 1; runNumber <= RUNS_PER_STRATEGY; runNumber++) {
                        for (var checkpoint : CHECKPOINTS) {
                            writer.printf(
                                    "%s/run_%02d/n_%d.csv%n",
                                    strategyDirectoryName(strategy),
                                    runNumber,
                                    checkpoint
                            );
                            datasetCount++;
                        }
                    }
                }
                writer.printf("%nTotal datasets: %d%n", datasetCount);
            }
        }

        private void writeConvergenceSection(PrintWriter writer) {
            writer.println("## Convergence Snapshot");
            writer.println();
            writer.println("| Strategy | Turns | Mean square range | Max square range |");
            writer.println("| --- | ---: | ---: | ---: |");

            // For each checkpoint, summarize how much the ten runs disagree with each other.
            // Smaller ranges suggest more stable long-run percentages.
            for (var strategy : JailStrategy.values()) {
                for (var checkpoint : CHECKPOINTS) {
                    var snapshots = snapshotsByStrategyAndTurn.get(strategy).get(checkpoint);
                    var rangeStats = calculateRangeStats(snapshots);
                    writer.printf(
                            "| %s | %,d | %.8f | %.8f |%n",
                            strategy,
                            checkpoint,
                            rangeStats.meanRange,
                            rangeStats.maxRange
                    );
                }
            }
            writer.println();
            writer.println("Range means `max(percentage) - min(percentage)` for each square across the 10 runs.");
            writer.println("`total_landings_recorded` may exceed `turns` because the current engine records extra movement caused by doubles inside the same player turn.");
            writer.println();
        }

        private void writeTopSquaresSection(PrintWriter writer) {
            writer.println("## Top Squares At n = 1,000,000");
            writer.println();

            for (var strategy : JailStrategy.values()) {
                writer.printf("### %s%n%n", strategy);
                writer.println("| Rank | Square | Avg percentage | Top-5 appearances |");
                writer.println("| ---: | --- | ---: | ---: |");

                var snapshots = snapshotsByStrategyAndTurn.get(strategy).get(1_000_000);
                var averagePercentages = averagePercentages(snapshots);
                var topFiveAppearances = countTopFiveAppearances(snapshots);

                // Rank by average percentage across the ten independent runs, then report
                // how often a square appeared in an individual run's top five.
                var sortedAverages = new ArrayList<>(averagePercentages);
                Collections.sort(sortedAverages, (a, b) -> {
                    int cmp = Double.compare(b.averagePercentage, a.averagePercentage);
                    if (cmp != 0) return cmp;
                    return a.name.compareTo(b.name);
                });

                // Only show the top 5 squares in the report.
                for (int rank = 0; rank < 5 && rank < sortedAverages.size(); rank++) {
                    var average = sortedAverages.get(rank);
                    String key = average.index + ":" + average.name;
                    int appearances = topFiveAppearances.containsKey(key) ? topFiveAppearances.get(key) : 0;
                    writer.printf(
                            "| %d | %d - %s | %.8f | %d/10 |%n",
                            rank + 1,
                            average.index,
                            average.name,
                            average.averagePercentage,
                            appearances
                    );
                }

                writer.println();
            }
        }

        private void writeStrategyComparisonSection(PrintWriter writer) {
            writer.println("## Strategy Comparison At n = 1,000,000");
            writer.println();
            writer.println("| Square | ImmediateExit avg % | TryForDoubles avg % | Absolute difference |");
            writer.println("| --- | ---: | ---: | ---: |");

            // Compare the strategy averages square-by-square at the final checkpoint so the
            // report can call out where the jail policy appears to matter most.
            var immediateAverages = averagePercentages(
                    snapshotsByStrategyAndTurn.get(JailStrategy.ImmediateExit).get(1_000_000)
            );
            var doublesAverages = averagePercentages(
                    snapshotsByStrategyAndTurn.get(JailStrategy.TryForDoubles).get(1_000_000)
            );

            var differences = new ArrayList<SquareDifference>();
            for (int i = 0; i < immediateAverages.size(); i++) {
                var immediate = immediateAverages.get(i);
                var doubles = doublesAverages.get(i);
                differences.add(new SquareDifference(
                        immediate.index,
                        immediate.name,
                        immediate.averagePercentage,
                        doubles.averagePercentage,
                        Math.abs(immediate.averagePercentage - doubles.averagePercentage)
                ));
            }

            // Sort by biggest difference first, then by name for ties.
            Collections.sort(differences, (a, b) -> {
                int cmp = Double.compare(b.absoluteDifference, a.absoluteDifference);
                if (cmp != 0) return cmp;
                return a.name.compareTo(b.name);
            });

            // Show the top 10 most different squares.
            for (int i = 0; i < 10 && i < differences.size(); i++) {
                var diff = differences.get(i);
                writer.printf(
                        "| %d - %s | %.8f | %.8f | %.8f |%n",
                        diff.index,
                        diff.name,
                        diff.immediateExitAverage,
                        diff.tryForDoublesAverage,
                        diff.absoluteDifference
                );
            }

            writer.println();
        }

        private RangeStats calculateRangeStats(List<Snapshot> snapshots) {
            // Range is used instead of standard deviation to keep the report logic simple:
            // for each square, look at the spread between the highest and lowest run.
            int squareCount = snapshots.get(0).squares.size();
            double totalRange = 0.0;
            double maxRange = 0.0;

            for (int squareIndex = 0; squareIndex < squareCount; squareIndex++) {
                double min = Double.POSITIVE_INFINITY;
                double max = Double.NEGATIVE_INFINITY;

                for (var snapshot : snapshots) {
                    var percentage = snapshot.squares.get(squareIndex).percentage;
                    min = Math.min(min, percentage);
                    max = Math.max(max, percentage);
                }

                double range = max - min;
                totalRange += range;
                maxRange = Math.max(maxRange, range);
            }

            return new RangeStats(totalRange / squareCount, maxRange);
        }

        private List<SquareAverage> averagePercentages(List<Snapshot> snapshots) {
            // Convert ten snapshots into one per-square average table.
            int squareCount = snapshots.get(0).squares.size();
            var averages = new ArrayList<SquareAverage>(squareCount);

            for (int squareIndex = 0; squareIndex < squareCount; squareIndex++) {
                double total = 0.0;
                String name = snapshots.get(0).squares.get(squareIndex).name;
                for (var snapshot : snapshots) {
                    total += snapshot.squares.get(squareIndex).percentage;
                }
                averages.add(new SquareAverage(squareIndex, name, total / snapshots.size()));
            }

            return averages;
        }

        private Map<String, Integer> countTopFiveAppearances(List<Snapshot> snapshots) {
            // Duplicate square names exist on the Monopoly board (for example multiple
            // Chance and Community Chest spaces), so the key includes the index.
            var appearances = new LinkedHashMap<String, Integer>();

            for (var snapshot : snapshots) {
                for (var square : snapshot.topSquares(5)) {
                    String key = square.index + ":" + square.name;
                    if (appearances.containsKey(key)) {
                        appearances.put(key, appearances.get(key) + 1);
                    } else {
                        appearances.put(key, 1);
                    }
                }
            }

            return appearances;
        }
    }

    private static final class RangeStats {
        private final double meanRange;
        private final double maxRange;

        private RangeStats(double meanRange, double maxRange) {
            this.meanRange = meanRange;
            this.maxRange = maxRange;
        }
    }

    private static final class SquareAverage {
        private final int index;
        private final String name;
        private final double averagePercentage;

        private SquareAverage(int index, String name, double averagePercentage) {
            this.index = index;
            this.name = name;
            this.averagePercentage = averagePercentage;
        }
    }

    private static final class SquareDifference {
        private final int index;
        private final String name;
        private final double immediateExitAverage;
        private final double tryForDoublesAverage;
        private final double absoluteDifference;

        private SquareDifference(
                int index,
                String name,
                double immediateExitAverage,
                double tryForDoublesAverage,
                double absoluteDifference
        ) {
            this.index = index;
            this.name = name;
            this.immediateExitAverage = immediateExitAverage;
            this.tryForDoublesAverage = tryForDoublesAverage;
            this.absoluteDifference = absoluteDifference;
        }
    }
}
