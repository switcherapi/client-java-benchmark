package com.github.switcherapi.benchmark.switcher;

import static com.github.switcherapi.benchmark.switcher.Features.MY_SWITCHER;
import static com.github.switcherapi.client.SwitcherContextBase.configure;
import static com.github.switcherapi.client.SwitcherContextBase.getSwitcher;
import static com.github.switcherapi.client.SwitcherContextBase.initializeClient;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.switcherapi.benchmark.Fail;
import com.github.switcherapi.client.ContextBuilder;

@State(Scope.Benchmark)
public class SwitcherSDKState {
	
	@Setup(Level.Trial)
    public void doSetup() {
		configure(ContextBuilder.builder()
				.contextLocation(Features.class.getName())
				.snapshotFile(getClass().getResource("/switcher-sdk.json").getFile())
				.offlineMode(true));
		
		initializeClient();
    }
	
	public void run() {
		if (!getSwitcher(MY_SWITCHER).isItOn())
			throw new Fail();
	}
	
	public void runThrottle() {
		if (!getSwitcher(MY_SWITCHER).throttle(1000).isItOn())
			throw new Fail();
	}
	
	public static void main(String[] args) {
		SwitcherSDKState state = new SwitcherSDKState();
		state.doSetup();
		state.run();
	}
	
}
