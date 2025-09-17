package com.github.switcherapi.benchmark.amplitude;

import com.amplitude.experiment.Experiment;
import com.amplitude.experiment.ExperimentUser;
import com.amplitude.experiment.LocalEvaluationClient;
import com.amplitude.experiment.Variant;
import com.github.switcherapi.benchmark.Fail;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Map;

@State(Scope.Benchmark)
public class AmplitudeExperimentSDKLocalState {

	private LocalEvaluationClient experiment;
	private ExperimentUser user;
	
	@Setup(Level.Trial)
    public void doSetup() {
		experiment = Experiment.initializeLocal("[API_KEY]");
		user = ExperimentUser.builder()
				.userId("user1")
				.deviceId("user1")
				.userProperty("premium", true)
				.build();

		experiment.start();
    }
	
	public void run() {
		Map<String, Variant> variants = experiment.evaluateV2(user);
		Variant variant = variants.get("feature_flag_local");

		if (!Variant.valueEquals(variant, "on")) {
			throw new Fail();
		}
	}
	
	public static void main(String[] args) {
		AmplitudeExperimentSDKLocalState state = new AmplitudeExperimentSDKLocalState();
		state.doSetup();
		state.run();
	}
	
}
