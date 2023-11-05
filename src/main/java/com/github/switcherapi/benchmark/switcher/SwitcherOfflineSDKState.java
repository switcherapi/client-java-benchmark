package com.github.switcherapi.benchmark.switcher;

import static com.github.switcherapi.benchmark.switcher.Features.MY_SWITCHER;
import static com.github.switcherapi.client.SwitcherContextBase.configure;
import static com.github.switcherapi.client.SwitcherContextBase.getSwitcher;
import static com.github.switcherapi.client.SwitcherContextBase.initializeClient;

import com.github.switcherapi.client.model.SwitcherBuilder;
import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.switcherapi.benchmark.Fail;
import com.github.switcherapi.client.ContextBuilder;

import java.nio.file.Paths;

@State(Scope.Benchmark)
public class SwitcherOfflineSDKState {

	private SwitcherBuilder switcher;
	
	@Setup(Level.Trial)
    public void doSetup() {
		configure(ContextBuilder.builder()
				.contextLocation(Features.class.getName())
				.snapshotLocation(Paths.get(StringUtils.EMPTY).toAbsolutePath() + "/src/main/resources")
				.environment("switcher-sdk")
				.offlineMode(true));
		
		initializeClient();
		switcher = getSwitcher(MY_SWITCHER);
    }
	
	public void run() {
		if (!switcher.isItOn())
			throw new Fail();
	}
	
	public static void main(String[] args) {
		SwitcherOfflineSDKState state = new SwitcherOfflineSDKState();
		state.doSetup();
		state.run();
	}
	
}
