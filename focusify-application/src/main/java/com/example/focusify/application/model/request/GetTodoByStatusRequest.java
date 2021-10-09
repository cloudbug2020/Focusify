package com.example.focusify.application.model.request;

import com.example.focusify.domain.todo.Status;
import java.util.Objects;

public class GetTodoByStatusRequest {

  private Status status;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetTodoByStatusRequest that = (GetTodoByStatusRequest) o;
    return status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(status);
  }

  @Override
  public String toString() {
    return "GetTodoByStatusRequest{" +
        "status=" + status +
        '}';
  }
}
