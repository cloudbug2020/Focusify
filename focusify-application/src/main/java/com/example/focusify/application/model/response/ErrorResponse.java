package com.example.focusify.application.model.response;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.LinkedList;
import java.util.List;

@RegisterForReflection
public class ErrorResponse {

  private List<String> body;

  public ErrorResponse() {
    this.body = new LinkedList<>();
  }

  public ErrorResponse(List<String> errors) {
    this.body = errors;
  }

  public List<String> getBody() {
    return body;
  }
}
