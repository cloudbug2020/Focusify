package com.example.focusify.application.health;

import com.example.focusify.application.api.TodoResource;
import com.example.focusify.application.api.UserResource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class RestApiHealthCheck implements HealthCheck {

  @Inject TodoResource todoResource;
  @Inject UserResource userResource;

  @Override
  public HealthCheckResponse call() {
    final var todoHello = this.todoResource.hello();
    final var userHello = this.userResource.hello();

    if (isNotNullOrEmpty(todoHello) || isNotNullOrEmpty(userHello)) {
      return HealthCheckResponse.named("Focusify REST Endpoints").up().build();
    } else {
      return HealthCheckResponse.named("Focusify REST Endpoints").down().build();
    }
  }

  private boolean isNotNullOrEmpty(String a) {
    return a != null && !a.isEmpty() && !a.isBlank();
  }
}
