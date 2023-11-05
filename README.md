# About
This benchmark compares 4 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
The tests included in this benchmark are focused only in performance and does not reflect nor measure any other SDK capabilities.<br>
The intent is to gain more knowledge and define a baseline to improve Switcher Client SDK overall performance.

## Run
Build jar and run the benchmark
```
mvn clean verify
java -jar target/benchmarks.jar
```

## Tests explained
Below is described how each state benchmark was configured and some other additional information

### testSwitcherThrottle

Uses throttle to use cache system and asynchronous call to in-memory snapshot.<br>
**Offline**: yes

### testSwitcherThrottle

Same as `testSwitcherThrottle`, however, it triggers the remote API every 500ms.<br>
**Offline**: partial

### testSwitcher, testTogglz, testSplit and testUnleash

In-memory configuration loaded from file.<br>
**Offline**: yes
<p>* Split SDK does not provide free access to fetch data from remote service - in this test the SDK is returning a default value.</p>

### testSwitcherOnline and testOptimizely

These tests are 100% remote.<br>
**Offline**: no

## Operations /s in 5s
Benchmark     |  Mode | Score | Units
:-------------|:-----|:------|:-------
FeatureFlagBenchmark.testSwitcherThrottle        | thrpt |       33334921,860          | ops/s
FeatureFlagBenchmark.testSwitcherOnlineThrottle  | thrpt |       33213251,194          | ops/s
FeatureFlagBenchmark.testSwitcherOffline         | thrpt |       10581289,556          | ops/s
FeatureFlagBenchmark.testUnleash                 | thrpt |         997235,067          | ops/s
FeatureFlagBenchmark.testSplit                   | thrpt |         618944,829          | ops/s
FeatureFlagBenchmark.testTogglz                  | thrpt |         156885,606          | ops/s
FeatureFlagBenchmark.testSwitcherOnline          | thrpt |            175,870          | ops/s
FeatureFlagBenchmark.testOptimizely              | thrpt |            159,561          | ops/s

## Average calls/ns in 5s
Benchmark     |  Mode | Score             | Units
:-------------|:-----|:------------------|:-------
FeatureFlagBenchmark.testSwitcherThrottle        | avgt | 29,235            | ns/op
FeatureFlagBenchmark.testSwitcherOnlineThrottle  | avgt | 31,661            | ns/op
FeatureFlagBenchmark.testSwitcherOffline         | avgt | 88,666            | ns/op
FeatureFlagBenchmark.testSplit                   | avgt | 809,649           | ns/op
FeatureFlagBenchmark.testUnleash                 | avgt | 950,517           | ns/op
FeatureFlagBenchmark.testTogglz                  | avgt | 5708,587          | ns/op
FeatureFlagBenchmark.testSwitcherOnline          | avgt | 5759681,034       | ns/op
FeatureFlagBenchmark.testOptimizely              | avgt | 6452909,009       | ns/op