# Monopoly Simulation Batch Summary

Generated 10 runs for each strategy across checkpoints [1000, 10000, 100000, 1000000].

## Convergence Snapshot

| Strategy | Turns | Mean square range | Max square range |
| --- | ---: | ---: | ---: |
| ImmediateExit | 1,000 | 0.01595000 | 0.02400000 |
| ImmediateExit | 10,000 | 0.00535750 | 0.00780000 |
| ImmediateExit | 100,000 | 0.00153850 | 0.00246000 |
| ImmediateExit | 1,000,000 | 0.00048860 | 0.00078000 |
| TryForDoubles | 1,000 | 0.01560000 | 0.02400000 |
| TryForDoubles | 10,000 | 0.00491000 | 0.00910000 |
| TryForDoubles | 100,000 | 0.00153975 | 0.00235000 |
| TryForDoubles | 1,000,000 | 0.00051812 | 0.00075300 |

Range means `max(percentage) - min(percentage)` for each square across the 10 runs.
`total_landings_recorded` may exceed `turns` because the current engine records extra movement caused by doubles inside the same player turn.

## Top Squares At n = 1,000,000

### ImmediateExit

| Rank | Square | Avg percentage | Top-5 appearances |
| ---: | --- | ---: | ---: |
| 1 | 7 - Chance | 0.03294170 | 10/10 |
| 2 | 19 - New York Avenue | 0.03262280 | 10/10 |
| 3 | 8 - Vermont Avenue | 0.03249500 | 10/10 |
| 4 | 4 - Income Tax | 0.03198890 | 10/10 |
| 5 | 9 - Connecticut Avenue | 0.03185600 | 6/10 |

### TryForDoubles

| Rank | Square | Avg percentage | Top-5 appearances |
| ---: | --- | ---: | ---: |
| 1 | 7 - Chance | 0.03300160 | 10/10 |
| 2 | 8 - Vermont Avenue | 0.03251510 | 10/10 |
| 3 | 19 - New York Avenue | 0.03250180 | 10/10 |
| 4 | 4 - Income Tax | 0.03197120 | 8/10 |
| 5 | 9 - Connecticut Avenue | 0.03195090 | 8/10 |

## Strategy Comparison At n = 1,000,000

| Square | ImmediateExit avg % | TryForDoubles avg % | Absolute difference |
| --- | ---: | ---: | ---: |
| 17 - Community Chest | 0.03032750 | 0.03019140 | 0.00013610 |
| 33 - Community Chest | 0.03115210 | 0.03127360 | 0.00012150 |
| 19 - New York Avenue | 0.03262280 | 0.03250180 | 0.00012100 |
| 22 - Chance | 0.03011050 | 0.03022530 | 0.00011480 |
| 13 - States Avenue | 0.02907180 | 0.02917880 | 0.00010700 |
| 2 - Community Chest | 0.02830690 | 0.02840510 | 0.00009820 |
| 9 - Connecticut Avenue | 0.03185600 | 0.03195090 | 0.00009490 |
| 10 - In Jail (Just Visiting) | 0.03120090 | 0.03111030 | 0.00009060 |
| 12 - Electric Company | 0.02994180 | 0.02985680 | 0.00008500 |
| 37 - Park Place | 0.03018660 | 0.03026150 | 0.00007490 |

