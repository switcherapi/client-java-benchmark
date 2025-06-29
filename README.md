# About
This benchmark compares 5 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
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
| Benchmark                                      | Type   | Mode  | Score           | Units |
|:-----------------------------------------------|:-------|:------|:----------------|:------|
| Simple Boolean (static feature flag)           | Local  | thrpt | 585,493,539.045 | ops/s |
| ClientJavaBenchmark.testSwitcherRemoteThrottle | Async  | thrpt | 39,513,548.118  | ops/s |
| ClientJavaBenchmark.testSwitcherLocal          | Local  | thrpt | 19,208,123.019  | ops/s |
| ClientJavaBenchmark.testHarness                | Async  | thrpt | 6,192,729.284   | ops/s |
| ClientJavaBenchmark.testSplit (*)              | Local  | thrpt | 5,421,889.921   | ops/s |
| ClientJavaBenchmark.testOptimizely             | Async  | thrpt | 536,292.594     | ops/s |
| ClientJavaBenchmark.testTogglz                 | Local  | thrpt | 419,103.750     | ops/s |
| ClientJavaBenchmark.testUnleash                | Local  | thrpt | 279,909.287     | ops/s |
| ClientJavaBenchmark.testSwitcherRemote         | Remote | thrpt | 460.793         | ops/s |

### Switcher Client SDK:
 - when Async, can be up to 6.5x faster than the other SDKs.
 - when Local, can be up to 3x faster than the other SDKs.
 - 100% remote can only be enabled with Switcher Client SDK.

## Average calls/ns in 5s
| Benchmark                                        | Mode | Score         | Units |
|:-------------------------------------------------|:-----|:--------------|:------|
| ClientJavaBenchmark.testSwitcherRemoteThrottle   | avgt | 26.563        | ns/op |
| ClientJavaBenchmark.testSwitcherLocal            | avgt | 54.325        | ns/op |
| ClientJavaBenchmark.testHarness                  | avgt | 153.292       | ns/op |
| ClientJavaBenchmark.testSplit                    | avgt | 180.326       | ns/op |
| ClientJavaBenchmark.testOptimizely               | avgt | 1,933.287     | ns/op |
| ClientJavaBenchmark.testUnleash                  | avgt | 3,643.121     | ns/op |
| ClientJavaBenchmark.testTogglz                   | avgt | 4,568.257     | ns/op |
| ClientJavaBenchmark.testSwitcherRemote           | avgt | 2,233,430.924 | ns/op |
