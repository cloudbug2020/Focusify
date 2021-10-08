package com.example.focusify.domain.exception;

import static java.util.Collections.singletonList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BusinessException extends RuntimeException {

  private final int code;
  private final List<String> messages;

  public BusinessException(int code, String message) {
    super(message);
    this.code = code;
    this.messages = new LinkedList<>(singletonList(message));
  }

  public BusinessException(int code, List<String> messages) {
    super(String.join(", ", messages));
    this.code = code;
    this.messages = messages;
  }

  public int getCode() {
    return code;
  }

  public List<String> getMessages() {
    return messages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessException that = (BusinessException) o;
    return code == that.code && Objects.equals(messages, that.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, messages);
  }

  @Override
  public String toString() {
    return "BusinessException{" +
        "code=" + code +
        ", messages=" + messages +
        '}';
  }
}
