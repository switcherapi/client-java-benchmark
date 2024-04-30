package com.github.switcherapi.benchmark.harness;

import com.github.switcherapi.benchmark.Fail;
import io.harness.cf.client.api.CfClient;
import io.harness.cf.client.api.FeatureFlagInitializeException;
import io.harness.cf.client.dto.Target;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class HarnessSDKState {

    private CfClient cfClient;
    private Target target;

    @Setup(Level.Trial)
    public void doSetup() {
        try {
            cfClient = new CfClient("[API-KEY]");
            cfClient.waitForInitialization();

            target = Target.builder()
                .identifier("javasdk")
                .name("JavaSDK")
                .attribute("location", "emea")
                .build();
        } catch (FeatureFlagInitializeException e) {
            throw new Fail();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Fail();
        }
    }

    public void run() {
        boolean result = cfClient.boolVariation("feature_flag", target, false);

        if (!result) {
            throw new Fail();
        }
    }

    public static void main(String[] args) {
        HarnessSDKState state = new HarnessSDKState();
        state.doSetup();
        state.run();
    }

}
