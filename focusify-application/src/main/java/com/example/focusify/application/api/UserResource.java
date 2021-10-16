package com.example.focusify.application.api;

import com.example.focusify.adapter.controller.UserController;
import com.example.focusify.adapter.controller.model.UserWeb;
import com.example.focusify.application.model.request.AddUserRequest;
import com.example.focusify.application.model.request.UpdateUserRequest;
import com.example.focusify.config.quarkus.ApplicationConfig;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  private final ApplicationConfig applicationConfig = new ApplicationConfig();
  private final UserController userController =
      new UserController(
          applicationConfig.createUser(),
          applicationConfig.deleteUser(),
          applicationConfig.getUser(),
          applicationConfig.updateUser());

  @POST
  @Transactional
  @Counted(
      name = "countAddUser",
      description = "Counts how many times the addUser method has been invoked")
  @Timed(
      name = "timeAddUser",
      description = "Times how long it takes to invoke the addUser method",
      unit = MetricUnits.MILLISECONDS)
  public Response addUser(@Valid @NotNull AddUserRequest request, @Context UriInfo uriInfo) {

    final UserWeb userWeb =
        UserWeb.builder().username(request.getUsername()).email(request.getEmail()).build();

    UserWeb storedUser = userController.createUser(userWeb);

    final URI uri =
        uriInfo.getAbsolutePathBuilder().path(Long.toString(storedUser.getId())).build();

    return Response.created(uri).entity(storedUser).build();
  }

  @GET
  @Counted(
      name = "countGetUser",
      description = "Counts how many times the getUser method has been invoked")
  @Timed(
      name = "timeGetUser",
      description = "Times how long it takes to invoke the getUser method",
      unit = MetricUnits.MILLISECONDS)
  @Path("/{id}")
  public Response getUser(@NotNull @PathParam("id") Long id) {
    final UserWeb user = userController.getUser(id);

    return Response.ok(user).build();
  }

  @PUT
  @Transactional
  @Counted(
      name = "countUpdateUser",
      description = "Counts how many times the updateUser method has been invoked")
  @Timed(
      name = "timeUpdateUser",
      description = "Times how long it takes to invoke the updateUser method",
      unit = MetricUnits.MILLISECONDS)
  @Path("/{id}")
  public Response updateUser(
      @Valid @NotNull UpdateUserRequest updateUserRequest, @PathParam("id") Long id) {
    final UserWeb userWeb =
        UserWeb.builder()
            .id(id)
            .username(updateUserRequest.getUsername())
            .email(updateUserRequest.getEmail())
            .build();
    final UserWeb response = this.userController.updateUser(userWeb);

    return Response.ok(response).build();
  }

  @DELETE
  @Transactional
  @Counted(
      name = "countDeleteUser",
      description = "Counts how many times the deleteUser method has been invoked")
  @Timed(
      name = "timeDeleteUser",
      description = "Times how long it takes to invoke the deleteUser method",
      unit = MetricUnits.MILLISECONDS)
  @Path("/{id}")
  public Response deleteUser(@NotNull @PathParam("id") Long id) {
    this.userController.deleteUser(id);
    return Response.noContent().build();
  }

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello, User Resource";
  }
}
