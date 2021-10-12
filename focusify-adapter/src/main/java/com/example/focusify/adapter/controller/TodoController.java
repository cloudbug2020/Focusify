package com.example.focusify.adapter.controller;

import com.example.focusify.adapter.controller.model.TodoWeb;
import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.AddTodo;
import com.example.focusify.usecase.todo.DeleteTodo;
import com.example.focusify.usecase.todo.GetTodo;
import com.example.focusify.usecase.todo.UpdateTodo;
import java.util.List;
import java.util.stream.Collectors;

public class TodoController {

  private final AddTodo addTodo;
  private final GetTodo getTodo;
  private final UpdateTodo updateTodo;
  private final DeleteTodo deleteTodo;

  public TodoController(
      AddTodo addTodo, GetTodo getTodo, UpdateTodo updateTodo, DeleteTodo deleteTodo) {
    this.addTodo = addTodo;
    this.getTodo = getTodo;
    this.updateTodo = updateTodo;
    this.deleteTodo = deleteTodo;
  }

  public TodoWeb createTodo(final TodoWeb todoWeb) {
    var todo = TodoWeb.toTodo(todoWeb);
    return TodoWeb.toTodoWeb(addTodo.addTodo(todo));
  }

  public TodoWeb getTodoById(final Long id) {
    var todo = getTodo.getTodoById(id);
    return TodoWeb.toTodoWeb(todo);
  }

  public Long countTodos() {
    return getTodo.countTodos();
  }

  public List<TodoWeb> getTodosByStatus(final Status status) {
    return getTodo.getTodosByStatus(status).stream()
        .map(TodoWeb::toTodoWeb)
        .collect(Collectors.toList());
  }

  public TodoWeb updateTodo(final TodoWeb todoWeb) {
    final Todo todo = TodoWeb.toTodo(todoWeb);
    return TodoWeb.toTodoWeb(updateTodo.updateTodo(todo));
  }

  public void deleteTodo(final Long id) {
    deleteTodo.deleteTodo(id);
  }
}
