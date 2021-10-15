package com.example.focusify.application.integration;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RestApiTest {

  @Test
  @Order(9)
  void shouldPingOpenAPI() {
    given()
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .get("/q/openapi")
        .then()
        .statusCode(OK.getStatusCode());
  }

  @Test
  @Order(9)
  void shouldPingMetrics() {
    given()
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .get("/q/metrics/application")
        .then()
        .statusCode(OK.getStatusCode());
  }

  @Test
  @Order(9)
  void shouldPingSwaggerUI() {
    given().when().get("/q/swagger-ui").then().statusCode(OK.getStatusCode());
  }

  @Test
  @Order(9)
  void shouldPingHealthCheck() {
    given().when().get("/q/health/live").then().statusCode(OK.getStatusCode());
  }

  @Test
  @Order(9)
  void shouldPingReadiness() {
    given().when().get("/q/health/ready").then().statusCode(OK.getStatusCode());
  }

}
