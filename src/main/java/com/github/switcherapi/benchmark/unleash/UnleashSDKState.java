package com.github.switcherapi.benchmark.unleash;

import com.github.switcherapi.benchmark.Fail;
import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class UnleashSDKState {

	private Unleash unleash;
	
	@Setup(Level.Trial)
    public void doSetup() {
		UnleashConfig config =
                UnleashConfig.builder()
                        .appName("test")
                        .unleashAPI("http://unleash.org")
                        .backupFile("./src/main/resources/unleash-sdk.json")
                        .build();

		unleash = new DefaultUnleash(config);
    }
	
	public void run() {
		if (!unleash.isEnabled("featureX")) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		UnleashSDKState state = new UnleashSDKState();
		state.doSetup();
		state.run();
	}

}
