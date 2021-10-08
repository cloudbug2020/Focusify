package com.example.focusify.usecase.todo.exception;

import com.example.focusify.domain.exception.BusinessException;

public class TodoValidationException extends BusinessException {

  public TodoValidationException(String message) {
    super(1, message);
  }

  public TodoValidationException() {
    super(1, "Validation of TODO failed");
  }

}
