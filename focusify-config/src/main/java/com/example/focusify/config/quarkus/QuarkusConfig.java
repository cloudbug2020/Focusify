package com.example.focusify.config.quarkus;

import com.example.focusify.adapter.db.panache.PanacheTodoRepository;
import com.example.focusify.usecase.todo.AddTodo;
import com.example.focusify.usecase.todo.DeleteTodo;
import com.example.focusify.usecase.todo.GetTodo;
import com.example.focusify.usecase.todo.UpdateTodo;
import com.example.focusify.usecase.todo.port.TodoRepository;

public class QuarkusConfig {

  private final TodoRepository todoRepository = new PanacheTodoRepository();
  private final AddTodo addTodo = new AddTodo(todoRepository);
  private final GetTodo getTodo = new GetTodo(todoRepository);
  private final UpdateTodo updateTodo = new UpdateTodo(todoRepository);
  private final DeleteTodo deleteTodo = new DeleteTodo(todoRepository);

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
}
