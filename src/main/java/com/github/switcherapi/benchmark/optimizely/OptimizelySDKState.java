package com.github.switcherapi.benchmark.optimizely;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.switcherapi.benchmark.Fail;
import com.optimizely.ab.Optimizely;
import com.optimizely.ab.OptimizelyFactory;
import com.optimizely.ab.OptimizelyUserContext;
import com.optimizely.ab.optimizelydecision.OptimizelyDecision;

@State(Scope.Benchmark)
public class OptimizelySDKState {
	
	private OptimizelyUserContext user;
	
	@Setup(Level.Trial)
    public void doSetup() {
		try (Optimizely optimizelyClient = OptimizelyFactory.newDefaultInstance("API_KEY")) {
			if (optimizelyClient.isValid()) {
				user = optimizelyClient.createUserContext("1");
			}
		}
    }
	
	public void run() {
		final OptimizelyDecision decision = user.decide("my_feature");
		if (!decision.getEnabled())
			throw new Fail();
	}
	
	public static void main(String[] args) {
		OptimizelySDKState state = new OptimizelySDKState();
		state.doSetup();
		state.run();
	}

}
