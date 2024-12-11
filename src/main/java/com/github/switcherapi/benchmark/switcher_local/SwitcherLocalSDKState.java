package com.github.switcherapi.benchmark.switcher_local;

import com.github.switcherapi.benchmark.Fail;
import com.github.switcherapi.client.ContextBuilder;
import com.github.switcherapi.client.model.SwitcherBuilder;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import static com.github.switcherapi.benchmark.switcher_local.Features.MY_SWITCHER;
import static com.github.switcherapi.client.SwitcherContextBase.*;

@State(Scope.Benchmark)
public class SwitcherLocalSDKState {

	private SwitcherBuilder switcher;
	
	@Setup(Level.Trial)
    public void doSetup() {
		configure(ContextBuilder.builder()
				.context(Features.class.getName())
				.snapshotLocation("src/main/resources")
				.environment("switcher-sdk")
				.local(true));
		
		initializeClient();
		switcher = getSwitcher(MY_SWITCHER);
    }
	
	public void run() {
		if (!switcher.isItOn()) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		SwitcherLocalSDKState state = new SwitcherLocalSDKState();
		state.doSetup();
		state.run();
	}
	
}
