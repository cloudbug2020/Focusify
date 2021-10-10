package com.example.focusify.application.integration;

import static io.restassured.RestAssured.given;
import static io.smallrye.common.constraint.Assert.assertTrue;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.focusify.application.DatabaseResource;
import com.example.focusify.application.constants.TestConstants;
import com.example.focusify.application.model.request.AddTodoRequest;
import com.example.focusify.domain.todo.Status;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoResourceTest {

  public static final String API_ENDPOINT = TestConstants.API_PREFIX + "/todos";

  private static Long todoId;

  @Test
  void shouldNotAddInvalidRequest() {
    AddTodoRequest request = new AddTodoRequest();

    given()
        .body(request)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .post(API_ENDPOINT)
        .then()
        .statusCode(422)
    ;
  }

  @Test
  @Order(1)
  void shouldNotGetDataOnEmptyDatabase() {
    given()
        .when()
        .get(API_ENDPOINT + "/0")
        .then()
        .statusCode(NOT_FOUND.getStatusCode())
    ;
  }

  @Test
  @Order(2)
  void shouldAddValidRequest() {
    AddTodoRequest request = new AddTodoRequest();
    request.setTitle("shouldAddValidRequest title");
    request.setDescription("shouldAddValidRequest description");
    request.setStatus(Status.TODO);

    String location =
        given()
            .body(request)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .when()
            .post(API_ENDPOINT)
            .then()
            .statusCode(CREATED.getStatusCode())
            .extract()
            .header("Location");
    assertTrue(location.contains(API_ENDPOINT));

    String[] segments = location.split("/");
    todoId = Long.valueOf(segments[segments.length - 1]);
    assertNotNull(todoId);
  }

  @Test
  @Order(3)
  void shouldRemoveAnItem() {
    given()
        .when()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .delete(API_ENDPOINT + "/"+todoId)
        .then()
        .statusCode(NO_CONTENT.getStatusCode())
    ;
  }

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
