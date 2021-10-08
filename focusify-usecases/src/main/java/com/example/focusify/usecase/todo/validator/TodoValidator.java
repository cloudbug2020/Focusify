package com.example.focusify.usecase.todo.validator;

import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.exception.TodoValidationException;

public class TodoValidator {

  private TodoValidator() throws InstantiationException {
    throw new InstantiationException("This utility class is not intended to be instantiated");
  }

  public static void validateAddTodo(final Todo todo) {
    if (todo == null) {
      throw new TodoValidationException("Todo should not be null");
    }
    if (todo.getTitle().isBlank()) {
      throw new TodoValidationException("Email should not be null");
    }
    if (todo.getDescription().isBlank() || todo.getDescription().isEmpty()) {
      throw new TodoValidationException("First name should not be null");
    }
  }
}
