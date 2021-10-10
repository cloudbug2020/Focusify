package com.example.focusify.application.api;

import com.example.focusify.adapter.controller.TodoController;
import com.example.focusify.adapter.controller.model.TodoWeb;
import com.example.focusify.application.model.request.AddTodoRequest;
import com.example.focusify.application.model.request.GetTodoByStatusRequest;
import com.example.focusify.application.model.request.UpdateTodoRequest;
import com.example.focusify.config.quarkus.QuarkusConfig;
import java.net.URI;
import java.util.List;
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

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

  private final QuarkusConfig quarkusConfig = new QuarkusConfig();
  private final TodoController todoController =
      new TodoController(
          quarkusConfig.addTodo(),
          quarkusConfig.getTodo(),
          quarkusConfig.updateTodo(),
          quarkusConfig.deleteTodo());

  @POST
  @Transactional
  @Counted(
      name = "countAddTodo",
      description = "Counts how many times the addTodo method has been invoked")
  @Timed(
      name = "timeAddTodo",
      description = "Times how long it takes to invoke the addTodo method",
      unit = MetricUnits.MILLISECONDS)
  public Response addTodo(@Valid @NotNull AddTodoRequest addTodoRequest, @Context UriInfo uriInfo) {

    var todoWeb =
        new TodoWeb(
            null,
            addTodoRequest.getTitle(),
            addTodoRequest.getDescription(),
            addTodoRequest.getStatus());
    var storedTodo = todoController.createTodo(todoWeb);

    final URI uri =
        uriInfo.getAbsolutePathBuilder().path(Long.toString(storedTodo.getId())).build();

    return Response.created(uri).entity(storedTodo).build();
  }

  @GET
  @Path("/{id}")
  @Counted(
      name = "countGetTodoById",
      description = "Counts how many times the getTodoById method has been invoked")
  @Timed(
      name = "timeGetTodoById",
      description = "Times how long it takes to invoke the getTodoById method",
      unit = MetricUnits.MILLISECONDS)
  public Response getTodoById(@PathParam("id") Long id) {
    final TodoWeb foundTodo = todoController.getTodoById(id);

    return Response.ok().entity(foundTodo).build();
  }

  @GET
  @Counted(
      name = "countGetTodosByStatus",
      description = "Counts how many times the getTodosByStatus method has been invoked")
  @Timed(
      name = "timeGetTodosByStatus",
      description = "Times how long it takes to invoke the getTodosByStatus method",
      unit = MetricUnits.MILLISECONDS)
  public Response getTodosByStatus(@Valid @NotNull GetTodoByStatusRequest getTodoByStatusRequest) {
    final List<TodoWeb> todosByStatus =
        todoController.getTodosByStatus(getTodoByStatusRequest.getStatus());

    return Response.ok().entity(todosByStatus).build();
  }

  @PUT
  @Transactional
  @Counted(
      name = "countUpdateTodo",
      description = "Counts how many times the updateTodo method has been invoked")
  @Timed(
      name = "timeUpdateTodo",
      description = "Times how long it takes to invoke the updateTodo method",
      unit = MetricUnits.MILLISECONDS)
  @Path("/{id}")
  public Response updateTodo(
      @Valid @NotNull UpdateTodoRequest updateTodoRequest, @NotNull @PathParam("id") Long id) {
    final TodoWeb todoWeb =
        new TodoWeb(
            id,
            updateTodoRequest.getTitle(),
            updateTodoRequest.getDescription(),
            updateTodoRequest.getStatus());
    final TodoWeb updatedTodo = todoController.updateTodo(todoWeb);

    return Response.ok().entity(updatedTodo).build();
  }

  @DELETE
  @Transactional
  @Counted(
      name = "countDeleteTodo",
      description = "Counts how many times the deleteTodo method has been invoked")
  @Timed(
      name = "timeDeleteTodo",
      description = "Times how long it takes to invoke the deleteTodo method",
      unit = MetricUnits.MILLISECONDS)
  @Path("/{id}")
  public Response deleteTodo(@NotNull @PathParam("id") Long id) {
    todoController.deleteTodo(id);
    return Response.noContent().build();
  }

  public String hello() {
    return "hello";
  }
}
