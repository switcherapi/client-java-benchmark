package com.github.switcherapi.benchmark.togglz;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.user.NoOpUserProvider;

import com.github.switcherapi.benchmark.Fail;

@State(Scope.Benchmark)
public class TogglzSDKState {
	
	private FeatureManager featureManager;
	
	@SuppressWarnings("unchecked")
	@Setup(Level.Trial)
	public void doSetup() {
		featureManager = new FeatureManagerBuilder()
                .featureEnums(Features.class)
                .stateRepository(new TogglzConfiguration().getStateRepository())
                .userProvider(new NoOpUserProvider())
                .build();
	}
	
	public void run() {
		if (!featureManager.isActive(Features.MY_FEATURE))
			throw new Fail();
	}
	
	public static void main(String[] args) {
		TogglzSDKState state = new TogglzSDKState();
		state.doSetup();
		state.run();
	}

}
