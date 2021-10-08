package com.example.focusify.application;

import static com.example.focusify.application.constants.PostgreSQLTestImages.POSTGRES_TEST_IMAGE;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Collections;
import java.util.Map;
import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {

  private static final PostgreSQLContainer DATABASE =
      new PostgreSQLContainer<>(POSTGRES_TEST_IMAGE)
          .withDatabaseName("focusify_integrationtest")
          .withUsername("hibernate")
          .withPassword("hibernate")
          .withExposedPorts(5432);

  @Override
  public Map<String, String> start() {
    DATABASE.start();
    return Collections.singletonMap("quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl());
  }

  @Override
  public void stop() {
    DATABASE.stop();
  }
}
