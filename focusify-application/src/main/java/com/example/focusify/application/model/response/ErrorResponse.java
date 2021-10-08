package com.example.focusify.application.model.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.LinkedList;
import java.util.List;

public class ErrorResponse {

  private List<String> body;

  public ErrorResponse() {
    this.body = new LinkedList<>();
  }

  public ErrorResponse(String error) {
    this();
    this.body.add(error);
  }

  public ErrorResponse(List<String> errors) {
    this.body = errors;
  }

  public List<String> getBody() {
    return body;
  }
}
