package com.example.focusify.application.health;

import com.example.focusify.application.api.TodoResource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class RestApiHealthCheck implements HealthCheck {

  @Inject TodoResource todoResource;

  @Override
  public HealthCheckResponse call() {
    final var hello = todoResource.hello();

    if (hello.isEmpty() || hello.isBlank()) {
      return HealthCheckResponse.named("Focusify REST Endpoint").down().build();
    } else {
      return HealthCheckResponse.named("Focusify REST Endpoint").up().build();
    }
  }
}
