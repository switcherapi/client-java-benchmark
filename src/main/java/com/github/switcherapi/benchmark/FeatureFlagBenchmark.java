package com.github.switcherapi.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.github.switcherapi.benchmark.optimizely.OptimizelySDKState;
import com.github.switcherapi.benchmark.split.SplitSDKState;
import com.github.switcherapi.benchmark.switcher.SwitcherSDKState;
import com.github.switcherapi.benchmark.switcher_online.SwitcherOnlineSDKState;
import com.github.switcherapi.benchmark.togglz.TogglzSDKState;
import com.github.switcherapi.benchmark.unleash.UnleashSDKState;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
public class FeatureFlagBenchmark {
	
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
    public void testSwitcher(SwitcherSDKState state) {
		state.run();
    }
	
	@Benchmark
    public void testSwitcherThrottle(SwitcherSDKState state) {
		state.runThrottle();
    }
	
	@Benchmark
    public void testSwitcherOnline(SwitcherOnlineSDKState state) {
		state.run();
    }
	
	@Benchmark
    public void testSwitcherOnlineThrottle(SwitcherOnlineSDKState state) {
		state.runThrottle();
    }
	
    public static void main(String[] args) throws Exception {
    	Options opt = new OptionsBuilder()
                .include(FeatureFlagBenchmark.class.getSimpleName())
                .build();
    	
    	new Runner(opt).run();
    }

}
