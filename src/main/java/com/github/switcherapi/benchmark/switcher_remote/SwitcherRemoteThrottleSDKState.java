package com.github.switcherapi.benchmark.switcher_remote;

import com.github.switcherapi.benchmark.Fail;
import com.github.switcherapi.client.ContextBuilder;
import com.github.switcherapi.client.model.Switcher;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import static com.github.switcherapi.benchmark.switcher_remote.Features.*;

@State(Scope.Benchmark)
public class SwitcherRemoteThrottleSDKState {

	private Switcher switcher;
	
	@Setup(Level.Trial)
    public void doSetup() {
		configure(ContextBuilder.builder()
				.context(Features.class.getName())
				.url("https://api.switcherapi.com")
				.apiKey("[API_KEY]")
				.domain("Playground")
				.component("benchmark"));

		initializeClient();
		switcher = getSwitcher(MY_ONLINE_SWITCHER).throttle(5000);
    }
	
	public void run() {
		if (!switcher.isItOn()) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		SwitcherRemoteThrottleSDKState state = new SwitcherRemoteThrottleSDKState();
		state.doSetup();
		state.run();
	}
	
}
