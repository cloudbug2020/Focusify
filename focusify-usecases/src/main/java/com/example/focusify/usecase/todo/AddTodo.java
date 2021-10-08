package com.example.focusify.usecase.todo;

import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.port.TodoRepository;
import com.example.focusify.usecase.todo.validator.TodoValidator;

public class AddTodo {

  private final TodoRepository todoRepository;

  public AddTodo(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo addTodo(final Todo todo) {
    TodoValidator.validateAddTodo(todo);

    var todoToSave =
        Todo.builder()
            .id(todo.getId())
            .title(todo.getTitle())
            .description(todo.getDescription())
            .done(todo.getStatus())
            .build();

    return todoRepository.createTodo(todoToSave);
  }
}
