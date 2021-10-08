package com.example.focusify.application;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ApplicationLifeCycle {

  @Inject Logger log;

  void onStart(@Observes StartupEvent ev) {
    log.info("The application is starting with profile " + ProfileManager.getActiveProfile());
  }
}
