package com.example.focusify.application.api;

import com.example.focusify.adapter.controller.TodoController;
import com.example.focusify.adapter.controller.model.TodoWeb;
import com.example.focusify.application.model.request.AddTodoRequest;
import com.example.focusify.application.model.request.DeleteTodoRequest;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

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
  public Response addTodo(@Valid @NotNull AddTodoRequest addTodoRequest, @Context UriInfo uriInfo) {
    var todoWeb =
        new TodoWeb(
            addTodoRequest.getTitle(), addTodoRequest.getDescription(), addTodoRequest.getStatus());
    var storedTodo = todoController.createTodo(todoWeb);

    final URI uri =
        uriInfo.getAbsolutePathBuilder().path(Long.toString(storedTodo.getId())).build();

    return Response.created(uri).status(Status.OK).entity(storedTodo).build();
  }

  @GET
  @Path("/{id}")
  public Response getTodoById(@PathParam("id") Long id) {
    final TodoWeb foundTodo = todoController.getTodoById(id);

    return Response.status(Status.OK).entity(foundTodo).build();
  }

  @GET
  public Response getTodosByStatus(@Valid @NotNull GetTodoByStatusRequest getTodoByStatusRequest) {
    final List<TodoWeb> todosByStatus =
        todoController.getTodosByStatus(getTodoByStatusRequest.getStatus());

    return Response.status(Status.OK).entity(todosByStatus).build();
  }

  @PUT
  @Transactional
  public Response updateTodo(@Valid @NotNull UpdateTodoRequest updateTodoRequest) {
    final TodoWeb todoWeb =
        new TodoWeb(
            updateTodoRequest.getId(),
            updateTodoRequest.getTitle(),
            updateTodoRequest.getDescription(),
            updateTodoRequest.getStatus());
    final TodoWeb updatedTodo = todoController.updateTodo(todoWeb);

    return Response.status(Status.OK).entity(updatedTodo).build();
  }

  @DELETE
  @Transactional
  public Response deleteTodo(@Valid @NotNull DeleteTodoRequest deleteTodoRequest) {
    todoController.deleteTodo(deleteTodoRequest.getId());
    return Response.status(Status.OK).build();
  }
}
