package com.example.focusify.usecase.todo.exception;

import com.example.focusify.domain.exception.BusinessException;

public class TodoNotFoundException extends BusinessException {

  public TodoNotFoundException(String message) {
    super(2, message);
  }

  public TodoNotFoundException() {
    super(2, "Todo was not found");
  }
}
