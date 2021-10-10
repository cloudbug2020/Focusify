package com.example.focusify.usecase.todo.exception;

import com.example.focusify.domain.exception.BusinessException;

public class TodoNotFoundException extends BusinessException {

  public TodoNotFoundException(String message) {
    super(2, message);
  }

}
