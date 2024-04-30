package com.github.switcherapi.benchmark.optimizely;

import org.openjdk.jmh.annotations.*;

import com.github.switcherapi.benchmark.Fail;
import com.optimizely.ab.Optimizely;
import com.optimizely.ab.OptimizelyFactory;
import com.optimizely.ab.OptimizelyUserContext;
import com.optimizely.ab.optimizelydecision.OptimizelyDecision;

@State(Scope.Benchmark)
public class OptimizelySDKState {

	private Optimizely optimizelyClient;
	private OptimizelyUserContext user;
	
	@Setup(Level.Trial)
    public void doSetup() {
		optimizelyClient = OptimizelyFactory.newDefaultInstance("[API-KEY]");
		if (optimizelyClient.isValid()) {
			user = optimizelyClient.createUserContext("1");
		}
    }

	@TearDown
	public void doTearDown() {
		optimizelyClient.close();
	}
	
	public void run() {
		final OptimizelyDecision decision = user.decide("my_feature");

		if (!decision.getEnabled()) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		OptimizelySDKState state = new OptimizelySDKState();
		state.doSetup();
		state.run();
		state.doTearDown();
	}

}
