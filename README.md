# About
This benchmark compares 5 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
The tests included in this benchmark are focused only in performance and does not reflect nor measure any other SDK capabilities.<br>
The intent is to gain more knowledge and define a baseline to improve Switcher Client SDK overall performance.

## Run
Build jar and run the benchmark
```
mvn clean verify
java -jar target/benchmarks.jar
```

## Tests explained

### [Async] testSwitcherRemoteThrottle, testHarness and testOptimizely
Uses in-memory cache by preloading or using async calls to remote service.<br>

### [Local] testSwitcherLocal, testSplit and testUnleash
In-memory configuration loaded from file.<br>
<p>* Split SDK does not provide free access to fetch data from remote service - in this test the SDK is returning a default value.</p>

### [Remote] testSwitcherRemote
These tests are 100% remote.<br>

## Operations /s in 5s
| Benchmark                                       | Type   | Mode  | Score          | Units |
|:------------------------------------------------|:-------|:------|:---------------|:------|
| FeatureFlagBenchmark.testSwitcherRemoteThrottle | Async  | thrpt | 15,255,667.081 | ops/s |
| FeatureFlagBenchmark.testSwitcherLocal          | Local  | thrpt | 14,254,786.801 | ops/s |
| FeatureFlagBenchmark.testHarness                | Async  | thrpt | 2,486,530.012  | ops/s |
| FeatureFlagBenchmark.testSplit                  | Local  | thrpt | 2,054,740.656  | ops/s |
| FeatureFlagBenchmark.testUnleash                | Local  | thrpt | 1,283,176.606  | ops/s |
| FeatureFlagBenchmark.testTogglz                 | Local  | thrpt | 181,059.633    | ops/s |
| FeatureFlagBenchmark.testOptimizely             | Async  | thrpt | 233,482.488    | ops/s |
| FeatureFlagBenchmark.testSwitcherRemote         | Remote | thrpt | 187.247        | ops/s |

### Switcher Client SDK:
 - when Async, can be up to 6x faster than the other SDKs.
 - when Local, can be up to 7x faster than the other SDKs.
 - 100% remote can only be enabled with Switcher Client SDK.

## Average calls/ns in 5s
| Benchmark                                       | Mode | Score         | Units |
|:------------------------------------------------|:-----|:--------------|:------|
| FeatureFlagBenchmark.testSwitcherRemoteThrottle | avgt | 31,661        | ns/op |
| FeatureFlagBenchmark.testSwitcherLocal          | avgt | 63.852        | ns/op |
| FeatureFlagBenchmark.testHarness                | avgt | 360.610       | ns/op |
| FeatureFlagBenchmark.testSplit                  | avgt | 430.571       | ns/op |
| FeatureFlagBenchmark.testUnleash                | avgt | 800.930       | ns/op |
| FeatureFlagBenchmark.testOptimizely             | avgt | 3,844.586     | ns/op |
| FeatureFlagBenchmark.testTogglz                 | avgt | 4,568.257     | ns/op |
| FeatureFlagBenchmark.testSwitcherRemote         | avgt | 5,699,296.355 | ns/op |