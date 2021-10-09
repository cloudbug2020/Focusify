package com.example.focusify.application.constants;

import org.testcontainers.utility.DockerImageName;

public interface PostgreSQLTestImages {
  DockerImageName POSTGRES_TEST_IMAGE = DockerImageName.parse("postgres:13.4");
}
