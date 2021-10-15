package com.example.focusify.usecase.user.exception;

import com.example.focusify.domain.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException(String message) {
    super(1, message);
  }
}
