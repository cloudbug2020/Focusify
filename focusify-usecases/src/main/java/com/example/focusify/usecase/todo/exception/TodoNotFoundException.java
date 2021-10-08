package com.example.focusify.usecase.todo.exception;

public class TodoNotFoundException extends RuntimeException {

  public TodoNotFoundException() {
    super("Todo could not be found");
  }

  public TodoNotFoundException(String message) {
    super(message);
  }
}
