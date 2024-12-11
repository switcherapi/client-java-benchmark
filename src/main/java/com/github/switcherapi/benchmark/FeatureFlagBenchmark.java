package com.github.switcherapi.benchmark;

import com.github.switcherapi.benchmark.harness.HarnessSDKState;
import com.github.switcherapi.benchmark.optimizely.OptimizelySDKState;
import com.github.switcherapi.benchmark.split.SplitSDKState;
import com.github.switcherapi.benchmark.switcher_local.SwitcherLocalSDKState;
import com.github.switcherapi.benchmark.switcher_remote.SwitcherRemoteSDKState;
import com.github.switcherapi.benchmark.switcher_remote.SwitcherRemoteThrottleSDKState;
import com.github.switcherapi.benchmark.togglz.TogglzSDKState;
import com.github.switcherapi.benchmark.unleash.UnleashSDKState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1)
@Measurement(iterations = 1, time = 5)
@Warmup(iterations = 1, time = 5)
public class FeatureFlagBenchmark {

	@Benchmark
	public void testSimpleBool(SimpleBoolState state) {
		state.run();
	}

	@Benchmark
	public void testOptimizely(OptimizelySDKState state) {
		state.run();
	}

	@Benchmark
	public void testSplit(SplitSDKState state) {
		state.run();
	}

	@Benchmark
	public void testUnleash(UnleashSDKState state) {
		state.run();
	}

	@Benchmark
	public void testTogglz(TogglzSDKState state) {
		state.run();
	}

	@Benchmark
	public void testHarness(HarnessSDKState state) {
		state.run();
	}

	@Benchmark
    public void testSwitcherLocal(SwitcherLocalSDKState state) {
		state.run();
    }

	@Benchmark
    public void testSwitcherRemote(SwitcherRemoteSDKState state) {
		state.run();
    }

	@Benchmark
    public void testSwitcherRemoteThrottle(SwitcherRemoteThrottleSDKState state) {
		state.run();
    }
	
    public static void main(String[] args) throws Exception {
    	Options opt = new OptionsBuilder()
                .include(FeatureFlagBenchmark.class.getSimpleName())
                .build();
    	
    	new Runner(opt).run();
    }

}
