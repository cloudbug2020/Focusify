package com.example.focusify.usecase.todo.exception;

public class TodoValidationException extends RuntimeException {

  public TodoValidationException() {
    super("Given Todo is invalid");
  }

  public TodoValidationException(final String message) {
    super(message);
  }
}
