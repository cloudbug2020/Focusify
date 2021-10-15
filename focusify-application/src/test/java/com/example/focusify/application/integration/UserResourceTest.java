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
import static org.junit.jupiter.api.Assertions.*;

import com.example.focusify.application.DatabaseResource;
import com.example.focusify.application.AuthenticationProviderResource;
import com.example.focusify.application.constants.TestConstants;
import com.example.focusify.application.model.request.AddUserRequest;
import com.example.focusify.application.model.request.AddTodoRequest;
import com.example.focusify.application.model.request.UpdateUserRequest;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceTest {

  private static final String API_ENDPOINT = TestConstants.API_PREFIX + "/users";

  private static Long userId;
  private static final String DEFAULT_USERNAME = "username";
  private static final String DEFAULT_EMAIL = "email@test.com";
  private static final String UPDATED_USERNAME = "updated";
  private static final String UPDATED_EMAIL = "update@test.com";

  @Test
  @Order(1)
  void shouldNotGetDataOnEmptyDatabase() {
    given().when().get(API_ENDPOINT + "/0").then().statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(2)
  void shouldAddValidRequest() {
    AddUserRequest request = new AddUserRequest();
    request.setUsername(DEFAULT_USERNAME);
    request.setEmail(DEFAULT_EMAIL);

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
    userId = Long.valueOf(segments[segments.length - 1]);
    assertNotNull(userId);
  }

  @Test
  @Order(3)
  void shouldGetAnItem() {
    given()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .get(API_ENDPOINT + "/"+userId)
        .then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .body("username", Is.is(DEFAULT_USERNAME))
        .body("email", Is.is(DEFAULT_EMAIL));
  }

  @Test
  @Order(4)
  void shouldUpdateAnItem() {
    UpdateUserRequest request = new UpdateUserRequest();
    request.setEmail(UPDATED_EMAIL);
    request.setUsername(UPDATED_USERNAME);

    given()
        .body(request)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .put(API_ENDPOINT + "/"+userId)
        .then()
        .statusCode(OK.getStatusCode());

    given()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .get(API_ENDPOINT + "/"+userId)
        .then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .body("username", Is.is(UPDATED_USERNAME))
        .body("email", Is.is(UPDATED_EMAIL));

  }

  @Test
  @Order(5)
  void shouldRemoveAnItem() {
    given()
        .when()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .delete(API_ENDPOINT + "/"+userId)
        .then()
        .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  void shouldNotAddInvalidRequest() {
    AddUserRequest request = new AddUserRequest();
    request.setEmail("invalidEmail");
    request.setUsername("validUsername");

    given()
        .body(request)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .post(API_ENDPOINT)
        .then()
        .statusCode(422);
  }

}