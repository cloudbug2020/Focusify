package com.example.focusify.config.quarkus;

import com.example.focusify.adapter.db.panache.PanacheTodoRepository;
import com.example.focusify.adapter.db.panache.PanacheUserRepository;
import com.example.focusify.usecase.todo.AddTodo;
import com.example.focusify.usecase.todo.DeleteTodo;
import com.example.focusify.usecase.todo.GetTodo;
import com.example.focusify.usecase.todo.UpdateTodo;
import com.example.focusify.usecase.todo.port.TodoRepository;
import com.example.focusify.usecase.user.CreateUser;
import com.example.focusify.usecase.user.DeleteUser;
import com.example.focusify.usecase.user.GetUser;
import com.example.focusify.usecase.user.UpdateUser;
import com.example.focusify.usecase.user.port.UserRepository;

public class ApplicationConfig {

  private final TodoRepository todoRepository = new PanacheTodoRepository();
  private final UserRepository userRepository = new PanacheUserRepository();

  private final AddTodo addTodo = new AddTodo(todoRepository);
  private final GetTodo getTodo = new GetTodo(todoRepository);
  private final UpdateTodo updateTodo = new UpdateTodo(todoRepository);
  private final DeleteTodo deleteTodo = new DeleteTodo(todoRepository);

  private final CreateUser createUser = new CreateUser(userRepository);
  private final DeleteUser deleteUser = new DeleteUser(userRepository);
  private final GetUser getUser = new GetUser(userRepository);
  private final UpdateUser updateUser = new UpdateUser(userRepository);

  public AddTodo addTodo() {
    return addTodo;
  }

  public GetTodo getTodo() {
    return getTodo;
  }

  public UpdateTodo updateTodo() {
    return updateTodo;
  }

  public DeleteTodo deleteTodo() {
    return deleteTodo;
  }

  public CreateUser createUser() { return createUser; }

  public DeleteUser deleteUser() {
    return deleteUser;
  }

  public GetUser getUser() {
    return getUser;
  }

  public UpdateUser updateUser() {
    return updateUser;
  }
}
