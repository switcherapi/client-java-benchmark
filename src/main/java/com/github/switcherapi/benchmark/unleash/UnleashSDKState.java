package com.github.switcherapi.benchmark.unleash;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.switcherapi.benchmark.Fail;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;

@State(Scope.Benchmark)
public class UnleashSDKState {

	private Unleash unleash;
	
	@Setup(Level.Trial)
    public void doSetup() {
		UnleashConfig config =
                UnleashConfig.builder()
                        .appName("test")
                        .unleashAPI("http://http://unleash.org")
                        .backupFile(getClass().getResource("/unleash-sdk.json").getFile())
                        .build();

		unleash = new DefaultUnleash(config);
    }
	
	public void run() {
		if (!unleash.isEnabled("featureX"))
			throw new Fail();
	}
	
	public static void main(String[] args) {
		UnleashSDKState state = new UnleashSDKState();
		state.doSetup();
		state.run();
	}

}
