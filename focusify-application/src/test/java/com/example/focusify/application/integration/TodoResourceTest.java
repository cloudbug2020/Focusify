package com.example.focusify.application.integration;

import static io.quarkus.test.keycloak.server.KeycloakTestResourceLifecycleManager.getAccessToken;
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
import com.example.focusify.application.model.request.GetTodoByStatusRequest;
import com.example.focusify.application.model.request.UpdateTodoRequest;
import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.keycloak.server.KeycloakTestResourceLifecycleManager;
import java.util.Arrays;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
@QuarkusTestResource(KeycloakTestResourceLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoResourceTest {

  private static final String API_ENDPOINT = TestConstants.API_PREFIX + "/todos";

  public static final String UPDATED_TITLE = "Updated Title";
  public static final String UPDATED_DESCRIPTION = "Updated Description";
  public static final Status UPDATE_STATUS = Status.DONE;

  private static Long todoId;
  private static final String DEFAULT_TITLE = "shouldAddValidRequest title";
  private static final String DEFAULT_DESC = "shouldAddValidRequest description";
  private static final Status DEFAULT_STATUS = Status.TODO;

  @Test
  void shouldNotAddInvalidRequest() {
    AddTodoRequest request = new AddTodoRequest();

    given()
        .body(request)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .auth()
        .oauth2(getAccessToken("alice"))
        .when()
        .post(API_ENDPOINT)
        .then()
        .statusCode(422);
  }

  @Test
  @Order(1)
  void shouldNotGetDataOnEmptyDatabase() {
    given()
        .auth()
        .oauth2(getAccessToken("alice"))
        .when()
        .get(API_ENDPOINT + "/0")
        .then()
        .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(2)
  void shouldAddValidRequest() {
    AddTodoRequest request = new AddTodoRequest();
    request.setTitle(DEFAULT_TITLE);
    request.setDescription(DEFAULT_DESC);
    request.setStatus(DEFAULT_STATUS);

    String location =
        given()
            .body(request)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .auth()
            .oauth2(getAccessToken("alice"))
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
  void shouldGetAnItem() {
    given()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .auth()
        .oauth2(getAccessToken("alice"))
        .when()
        .get(API_ENDPOINT + "/" + todoId)
        .then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .body("title", Is.is(DEFAULT_TITLE))
        .body("description", Is.is(DEFAULT_DESC))
        .body("status", Is.is(DEFAULT_STATUS.name()));
  }

  @Test
  @Order(3)
  void shouldGetOnlyItemsWithStatusTodo() {

    GetTodoByStatusRequest request = new GetTodoByStatusRequest();
    request.setStatus(Status.TODO);

    Todo[] todos =
        given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
            .auth()
            .oauth2(getAccessToken("alice"))
            .body(request)
            .when()
            .get(API_ENDPOINT)
            .then()
            .statusCode(OK.getStatusCode())
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .extract()
            .as(Todo[].class);

    assertTrue(Arrays.stream(todos).allMatch(a -> a.getStatus().equals(Status.TODO)));
  }

  @Test
  @Order(4)
  void shouldUpdateAnItem() {
    UpdateTodoRequest request = new UpdateTodoRequest();
    request.setTitle(UPDATED_TITLE);
    request.setDescription(UPDATED_DESCRIPTION);
    request.setStatus(UPDATE_STATUS);

    given()
        .body(request)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .auth()
        .oauth2(getAccessToken("alice"))
        .when()
        .put(API_ENDPOINT + "/" + todoId)
        .then()
        .statusCode(OK.getStatusCode());

    given()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .when()
        .auth()
        .oauth2(getAccessToken("alice"))
        .get(API_ENDPOINT + "/" + todoId)
        .then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .body("title", Is.is(UPDATED_TITLE))
        .body("description", Is.is(UPDATED_DESCRIPTION))
        .body("status", Is.is(UPDATE_STATUS.name()));
  }

  @Test
  @Order(5)
  void shouldRemoveAnItem() {
    given()
        .when()
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON)
        .auth()
        .oauth2(getAccessToken("alice"))
        .delete(API_ENDPOINT + "/" + todoId)
        .then()
        .statusCode(NO_CONTENT.getStatusCode());
  }
}
