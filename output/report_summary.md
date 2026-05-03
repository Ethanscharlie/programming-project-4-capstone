# Monopoly Simulation Batch Summary

Generated 10 runs for each strategy across checkpoints [1000, 10000, 100000, 1000000].

## Convergence Snapshot

| Strategy | Turns | Mean square range | Max square range |
| --- | ---: | ---: | ---: |
| ImmediateExit | 1,000 | 0.01522500 | 0.02400000 |
| ImmediateExit | 10,000 | 0.00528250 | 0.00830000 |
| ImmediateExit | 100,000 | 0.00147150 | 0.00251000 |
| ImmediateExit | 1,000,000 | 0.00052677 | 0.00096500 |
| TryForDoubles | 1,000 | 0.01552500 | 0.03000000 |
| TryForDoubles | 10,000 | 0.00460250 | 0.00690000 |
| TryForDoubles | 100,000 | 0.00162550 | 0.00254000 |
| TryForDoubles | 1,000,000 | 0.00049898 | 0.00081100 |

Range means `max(percentage) - min(percentage)` for each square across the 10 runs.
`total_landings_recorded` may exceed `turns` because the current engine records extra movement caused by doubles inside the same player turn.

## Top Squares At n = 1,000,000

### ImmediateExit

| Rank | Square | Avg percentage | Top-5 appearances |
| ---: | --- | ---: | ---: |
| 1 | 7 - Chance | 0.03305360 | 10/10 |
| 2 | 19 - New York Avenue | 0.03258380 | 10/10 |
| 3 | 8 - Vermont Avenue | 0.03255710 | 10/10 |
| 4 | 4 - Income Tax | 0.03193480 | 8/10 |
| 5 | 9 - Connecticut Avenue | 0.03190940 | 7/10 |

### TryForDoubles

| Rank | Square | Avg percentage | Top-5 appearances |
| ---: | --- | ---: | ---: |
| 1 | 7 - Chance | 0.03309430 | 10/10 |
| 2 | 8 - Vermont Avenue | 0.03258150 | 10/10 |
| 3 | 19 - New York Avenue | 0.03256660 | 10/10 |
| 4 | 4 - Income Tax | 0.03186200 | 8/10 |
| 5 | 9 - Connecticut Avenue | 0.03183290 | 5/10 |

## Strategy Comparison At n = 1,000,000

| Square | ImmediateExit avg % | TryForDoubles avg % | Absolute difference |
| --- | ---: | ---: | ---: |
| 16 - St. James Place | 0.02994440 | 0.03012900 | 0.00018460 |
| 25 - B&O Railroad | 0.02913270 | 0.02931680 | 0.00018410 |
| 32 - North Carolina Avenue | 0.02869540 | 0.02881080 | 0.00011540 |
| 13 - States Avenue | 0.02914480 | 0.02903020 | 0.00011460 |
| 12 - Electric Company | 0.02991180 | 0.02980240 | 0.00010940 |
| 38 - Luxury Tax | 0.02963510 | 0.02953060 | 0.00010450 |
| 34 - Pennsylvania Avenue | 0.02967720 | 0.02957590 | 0.00010130 |
| 5 - Reading Railroad | 0.03070290 | 0.03079280 | 0.00008990 |
| 37 - Park Place | 0.03023540 | 0.03032420 | 0.00008880 |
| 18 - Tennessee Avenue | 0.03029220 | 0.03037140 | 0.00007920 |

