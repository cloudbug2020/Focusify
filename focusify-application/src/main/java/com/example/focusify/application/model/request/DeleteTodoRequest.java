package com.example.focusify.application.model.request;

import java.util.Objects;

public class DeleteTodoRequest {
  Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteTodoRequest that = (DeleteTodoRequest) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "DeleteTodoRequest{" +
        "id=" + id +
        '}';
  }
}
