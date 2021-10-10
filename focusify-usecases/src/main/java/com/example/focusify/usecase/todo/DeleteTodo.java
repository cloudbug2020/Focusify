package com.example.focusify.usecase.todo;

import com.example.focusify.usecase.todo.port.TodoRepository;

public class DeleteTodo {

  private final TodoRepository todoRepository;

  public DeleteTodo(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public void deleteTodo(Long id) {
    this.todoRepository.deleteTodo(id);
  }
}
