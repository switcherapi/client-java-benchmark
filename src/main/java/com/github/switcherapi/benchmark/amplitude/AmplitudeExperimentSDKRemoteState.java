package com.github.switcherapi.benchmark.amplitude;

import com.amplitude.experiment.Experiment;
import com.amplitude.experiment.ExperimentUser;
import com.amplitude.experiment.RemoteEvaluationClient;
import com.amplitude.experiment.Variant;
import com.github.switcherapi.benchmark.Fail;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@State(Scope.Benchmark)
public class AmplitudeExperimentSDKRemoteState {

	private RemoteEvaluationClient experiment;
	private ExperimentUser user;
	
	@Setup(Level.Trial)
    public void doSetup() {
		experiment = Experiment.initializeRemote("[API_KEY]");
		user = ExperimentUser.builder()
				.userId("user1")
				.deviceId("user1")
				.userProperty("premium", true)
				.build();
    }
	
	public void run() {
		try {
			Map<String, Variant> variants = experiment.fetch(user).get();
			Variant variant = variants.get("feature_flag");

			if (!Variant.valueEquals(variant, "on")) {
				throw new Fail();
			}
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		AmplitudeExperimentSDKRemoteState state = new AmplitudeExperimentSDKRemoteState();
		state.doSetup();
		state.run();
	}
	
}
