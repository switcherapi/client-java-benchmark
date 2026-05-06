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
| Benchmark                                        | Type   | Mode  | Score           | Units |
|:-------------------------------------------------|:-------|:------|:----------------|:------|
| Simple Boolean (static feature flag)             | Local  | thrpt | 585,493,539.045 | ops/s |
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | Async  | thrpt | 188,184,291.488 | ops/s |
| ClientJavaBenchmark.testSwitcherLocal            | Local  | thrpt | 100,546,841.909 | ops/s |
| ClientJavaBenchmark.testSplit (*)                | Local  | thrpt | 7,218,579.125   | ops/s |
| ClientJavaBenchmark.testHarness                  | Async  | thrpt | 6,000,684.947   | ops/s |
| ClientJavaBenchmark.testAmplitudeExperimentLocal | Local  | thrpt | 1,373,333.472   | ops/s |
| ClientJavaBenchmark.testOptimizely               | Async  | thrpt | 593,926.377     | ops/s |
| ClientJavaBenchmark.testTogglz                   | Local  | thrpt | 403,239.572     | ops/s |
| ClientJavaBenchmark.testUnleash                  | Local  | thrpt | 371,876.849     | ops/s |
| ClientJavaBenchmark.testSwitcherRemote           | Remote | thrpt | 3,446.726       | ops/s |
| ClientJavaBenchmark.testAmplitudeExperiment      | Remote | thrpt | 16.539          | ops/s |

### Switcher Client SDK:
 - when Async, can be up to 31x faster than the other SDKs.
 - when Local, can be up to 13x faster than the other SDKs.
 - 100% remote can only be enabled with Switcher Client and Amplitude Experiment SDKs.

## Average calls/ns in 5s
| Benchmark                                        | Mode | Score          | Units |
|:-------------------------------------------------|:-----|:---------------|:------|
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | avgt | 5.599          | ns/op |
| ClientJavaBenchmark.testSwitcherLocal            | avgt | 9.857          | ns/op |
| ClientJavaBenchmark.testSplit                    | avgt | 138.086        | ns/op |
| ClientJavaBenchmark.testHarness                  | avgt | 169.320        | ns/op |
| ClientJavaBenchmark.testAmplitudeExperimentLocal | avgt | 693.702        | ns/op |
| ClientJavaBenchmark.testOptimizely               | avgt | 1,769.742      | ns/op |
| ClientJavaBenchmark.testTogglz                   | avgt | 2,476.243      | ns/op |
| ClientJavaBenchmark.testUnleash                  | avgt | 2,650.046      | ns/op |
| ClientJavaBenchmark.testSwitcherRemote           | avgt | 313,660.701    | ns/op |
| ClientJavaBenchmark.testAmplitudeExperiment      | avgt | 51,005,960.606 | ns/op |

### Switcher Client SDK performance explained:
Switcher Client SDK implements several data structures optimizations and smart caching strategies such as Stale-While-Revalidate (SWR) to achieve the best possible performance and throughput.<br>
The SDK currently supports a wide combination of settings that can balance between performance and resource utilization without compromising assertiveness and reliability.<br>
Local context settings are not necessarily static and it can be updated using features such as scheduled updates.