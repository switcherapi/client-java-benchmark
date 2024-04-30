package com.github.switcherapi.benchmark.togglz;

import java.io.File;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.UserProvider;

public class TogglzConfiguration implements TogglzConfig {

    public Class<? extends Feature> getFeatureClass() {
        return Features.class;
    }

    public StateRepository getStateRepository() {
        return new FileBasedStateRepository(new File("./src/main/resources/togglz-sdk.properties"));
    }

    public UserProvider getUserProvider() {
        return null;
    }

}