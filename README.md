# About
This benchmark compares 6 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
The tests included in this benchmark are focused only on performance and do not reflect nor measure any other SDK capabilities.<br>
The goal is to gain more knowledge and define a baseline to improve Switcher Client SDK overall performance.

## Run
Build jar and run the benchmark
```
mvn clean verify
java -Dbenchmark=testSwitcherRemoteThrottle -jar target/benchmarks.jar
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
| Simple Boolean (static feature flag)             | Local  | thrpt | 681,846,666.467 | ops/s |
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | Async  | thrpt | 192,274,538.844 | ops/s |
| ClientJavaBenchmark.testSwitcherLocal            | Local  | thrpt | 104,297,879.028 | ops/s |
| ClientJavaBenchmark.testSplit (*)                | Local  | thrpt | 7,595,298.222   | ops/s |
| ClientJavaBenchmark.testHarness                  | Async  | thrpt | 6,069,009.437   | ops/s |
| ClientJavaBenchmark.testAmplitudeExperimentLocal | Local  | thrpt | 1,419,570.823   | ops/s |
| ClientJavaBenchmark.testOptimizely               | Async  | thrpt | 601,086.850     | ops/s |
| ClientJavaBenchmark.testTogglz                   | Local  | thrpt | 393,263.446     | ops/s |
| ClientJavaBenchmark.testUnleash                  | Local  | thrpt | 390,850.724     | ops/s |
| ClientJavaBenchmark.testSwitcherRemote           | Remote | thrpt | 3,446.726       | ops/s |
| ClientJavaBenchmark.testAmplitudeExperiment      | Remote | thrpt | 98.396          | ops/s |

### Switcher Client SDK:
 - when Async, can be up to 31x faster than the other SDKs.
 - when Local, can be up to 13x faster than the other SDKs.
 - 100% remote can only be enabled with Switcher Client and Amplitude Experiment SDKs.

## Average calls/ns in 5s
| Benchmark                                        | Mode | Score          | Units |
|:-------------------------------------------------|:-----|:---------------|:------|
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | avgt | 5.161          | ns/op |
| ClientJavaBenchmark.testSwitcherLocal            | avgt | 9.329          | ns/op |
| ClientJavaBenchmark.testSplit                    | avgt | 129.785        | ns/op |
| ClientJavaBenchmark.testHarness                  | avgt | 156.572        | ns/op |
| ClientJavaBenchmark.testAmplitudeExperimentLocal | avgt | 692.889        | ns/op |
| ClientJavaBenchmark.testOptimizely               | avgt | 1,675.677      | ns/op |
| ClientJavaBenchmark.testTogglz                   | avgt | 2,451.217      | ns/op |
| ClientJavaBenchmark.testUnleash                  | avgt | 2,616.761      | ns/op |
| ClientJavaBenchmark.testSwitcherRemote           | avgt | 313,660.701    | ns/op |
| ClientJavaBenchmark.testAmplitudeExperiment      | avgt | 10,039,482.766 | ns/op |

### Switcher Client SDK performance explained:
Switcher Client SDK implements several data structures optimizations and smart caching strategies such as Stale-While-Revalidate (SWR) to achieve the best possible performance and throughput.<br>
The SDK currently supports a wide combination of settings that can balance between performance and resource utilization without compromising assertiveness and reliability.<br>
Local context settings are not necessarily static and it can be updated using features such as scheduled updates.