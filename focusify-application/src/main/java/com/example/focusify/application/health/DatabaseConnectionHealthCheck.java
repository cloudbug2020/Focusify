package com.example.focusify.application.health;

import com.example.focusify.adapter.controller.TodoController;
import com.example.focusify.config.quarkus.ApplicationConfig;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

  private final ApplicationConfig quarkusConfig = new ApplicationConfig();
  private final TodoController todoController =
      new TodoController(
          quarkusConfig.addTodo(),
          quarkusConfig.getTodo(),
          quarkusConfig.updateTodo(),
          quarkusConfig.deleteTodo());

  @Override
  public HealthCheckResponse call() {
    HealthCheckResponseBuilder responseBuilder =
        HealthCheckResponse.named("Focusify Datasource connection health check");

    try {
      long items = todoController.countTodos();
      responseBuilder.withData("Number of Todos in the database", items).up();
    } catch (IllegalStateException e) {
      responseBuilder.down();
    }

    return responseBuilder.build();
  }
}
