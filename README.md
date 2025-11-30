# About
This benchmark compares 6 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
The tests included in this benchmark are focused only on performance and do not reflect nor measure any other SDK capabilities.<br>
The goal is to gain more knowledge and define a baseline to improve Switcher Client SDK overall performance.

## Run
Build jar and run the benchmark
```
mvn clean verify
java -jar target/benchmarks.jar
```

## Tests explained

Tests results are subject to change based on the environment and configuration.

### [Async] testSwitcherRemoteThrottle, testHarness and testOptimizely
Uses in-memory cache by preloading or using async calls to remote API.

### [Local] testSwitcherLocal, testSplit and testUnleash
In-memory configuration loaded from a file.

(*) Split SDK does not provide free access to fetch data from remote API - in this test the SDK is returning a default value.

### [Remote] testSwitcherRemote
These tests are fully remote.

## Operations /s in 5s
| Benchmark                                         | Type   | Mode  | Score           | Units |
|:--------------------------------------------------|:-------|:------|:----------------|:------|
| Simple Boolean (static feature flag)              | Local  | thrpt | 585,493,539.045 | ops/s |
| ClientJavaBenchmark.testSwitcherRemoteThrottle    | Async  | thrpt | 112,963,043.838 | ops/s |
| ClientJavaBenchmark.testSwitcherLocal             | Local  | thrpt | 100,293,042.601 | ops/s |
| ClientJavaBenchmark.testSplit (*)                 | Local  | thrpt | 7,729,980.923   | ops/s |
| ClientJavaBenchmark.testHarness                   | Async  | thrpt | 6,933,354.921   | ops/s |
| ClientJavaBenchmark.testAmplitudeExperimentLocal  | Local  | thrpt | 1,414,068.549   | ops/s |
| ClientJavaBenchmark.testOptimizely                | Async  | thrpt | 536,292.594     | ops/s |
| ClientJavaBenchmark.testTogglz                    | Local  | thrpt | 419,103.750     | ops/s |
| ClientJavaBenchmark.testUnleash                   | Local  | thrpt | 282,153.192     | ops/s |
| ClientJavaBenchmark.testSwitcherRemote            | Remote | thrpt | 460.793         | ops/s |
| ClientJavaBenchmark.testAmplitudeExperiment       | Remote | thrpt | 96.813          | ops/s |

### Switcher Client SDK:
 - when Async, can be up to 16x faster than the other SDKs.
 - when Local, can be up to 6x faster than the other SDKs.
 - 100% remote can only be enabled with Switcher Client and Amplitude Experiment SDKs.

## Average calls/ns in 5s
| Benchmark                                        | Mode | Score          | Units |
|:-------------------------------------------------|:-----|:---------------|:------|
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | avgt | 9.248          | ns/op |
| ClientJavaBenchmark.testSwitcherLocal            | avgt | 9.660          | ns/op |
| ClientJavaBenchmark.testSplit                    | avgt | 121.621        | ns/op |
| ClientJavaBenchmark.testHarness                  | avgt | 147.048        | ns/op |
| ClientJavaBenchmark.testAmplitudeExperimentLocal | avgt | 699.503        | ns/op |
| ClientJavaBenchmark.testOptimizely               | avgt | 1,933.287      | ns/op |
| ClientJavaBenchmark.testUnleash                  | avgt | 3,571.001      | ns/op |
| ClientJavaBenchmark.testTogglz                   | avgt | 4,568.257      | ns/op |
| ClientJavaBenchmark.testSwitcherRemote           | avgt | 2,233,430.924  | ns/op |
| ClientJavaBenchmark.testAmplitudeExperiment      | avgt | 10,262,096.319 | ns/op |
