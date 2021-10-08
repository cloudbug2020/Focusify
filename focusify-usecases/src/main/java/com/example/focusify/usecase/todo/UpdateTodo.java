package com.example.focusify.usecase.todo;

import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.port.TodoRepository;

public class UpdateTodo {

  private final TodoRepository todoRepository;

  public UpdateTodo(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo updateTodo(Todo todo) {
    return this.todoRepository.updateTodo(todo);
  }

}
