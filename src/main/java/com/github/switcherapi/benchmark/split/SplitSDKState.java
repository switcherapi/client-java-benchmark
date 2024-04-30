package com.github.switcherapi.benchmark.split;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import com.github.switcherapi.benchmark.Fail;

import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;
import io.split.storages.enums.StorageMode;

@State(Scope.Benchmark)
public class SplitSDKState {
	
	private SplitClient client;
	
	@Setup(Level.Trial)
    public void doSetup() {
		SplitClientConfig config = SplitClientConfig.builder()
                .impressionsRefreshRate(60)
                .storageMode(StorageMode.MEMORY)
                .build();
		
		try {
			SplitFactory splitFactory = SplitFactoryBuilder.build("[API_KEY]", config);
			client = splitFactory.client();
		} catch (IOException | URISyntaxException e) {
			throw new Fail();
		}
    }
	
	@TearDown
	public void tearDown() {
		client.destroy();
	}
	
	public void run() {
		if (client.getTreatment("FEATURE", "").equals("on")) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		SplitSDKState state = new SplitSDKState();
		state.doSetup();
		state.run();
	}

}
