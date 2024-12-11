package com.github.switcherapi.benchmark;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;

@State(Scope.Benchmark)
public class SimpleBoolState {

	private StaticFeatureFlag featureFlag;
	
	@Setup(Level.Trial)
    public void doSetup() {
		featureFlag = new StaticFeatureFlag();
		featureFlag.addFeature("my-feature", true);
    }
	
	public void run() {
		if (!featureFlag.isEnabled("my-feature")) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		SimpleBoolState state = new SimpleBoolState();
		state.doSetup();
		state.run();
	}

	static class StaticFeatureFlag {

		private final Map<String, Boolean> features = new HashMap<>();

		public void addFeature(String feature, boolean isEnabled) {
			features.put(feature, isEnabled);
		}

		public boolean isEnabled(String feature) {
			return features.get(feature);
		}
	}

}
