# About
This benchmark compares 4 major Feature Flag SDKs against Switcher Client SDK for Java.<br>
The tests included in this benchmark are focused only in performance and does not reflect nor measure any other SDK capabilities.<br>
The intent is to gain more knowledge and define a baseline to improve Switcher Client SDK overall performance.

## Tests explained
Below is described how each state benchmark was configured and some other additional informations

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
FeatureFlagBenchmark.testSwitcherThrottle        | thrpt |       17890932,716          | ops/s
FeatureFlagBenchmark.testSwitcherOnlineThrottle  | thrpt |       12225676,856          | ops/s
FeatureFlagBenchmark.testSwitcher                | thrpt |        7909521,194          | ops/s
FeatureFlagBenchmark.testUnleash                 | thrpt |         997235,067          | ops/s
FeatureFlagBenchmark.testSplit                   | thrpt |         618944,829          | ops/s
FeatureFlagBenchmark.testTogglz                  | thrpt |         156885,606          | ops/s
FeatureFlagBenchmark.testOptimizely              | thrpt |            159,561          | ops/s
FeatureFlagBenchmark.testSwitcherOnline          | thrpt |            130,897          | ops/s

## Avarage calls/ns in 5s
Benchmark     |  Mode | Score | Units
:-------------|:-----|:------|:-------
FeatureFlagBenchmark.testSwitcherThrottle        | avgt |            52,151          | ns/op
FeatureFlagBenchmark.testSwitcherOnlineThrottle  | avgt |            79,109          | ns/op
FeatureFlagBenchmark.testSwitcher                | avgt |           118,516          | ns/op
FeatureFlagBenchmark.testSplit                   | avgt |           809,649          | ns/op
FeatureFlagBenchmark.testUnleash                 | avgt |           950,517          | ns/op
FeatureFlagBenchmark.testTogglz                  | avgt |          5708,587          | ns/op
FeatureFlagBenchmark.testOptimizely              | avgt |       6452909,009          | ns/op
FeatureFlagBenchmark.testSwitcherOnline          | avgt |       7156941,429          | ns/op