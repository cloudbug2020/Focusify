package com.example.focusify.application;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.jboss.logging.Logger;

@ApplicationScoped
public class FocusifyApplicationLifecycle {

  private static final Logger LOGGER = Logger.getLogger(FocusifyApplicationLifecycle.class);

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("The application is starting with profile " + ProfileManager.getActiveProfile());
  }

  void onStop(@Observes ShutdownEvent ev) {
    LOGGER.info("The application FOCUSIFY is stopping...");
  }
}
