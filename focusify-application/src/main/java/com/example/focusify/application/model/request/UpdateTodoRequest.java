package com.example.focusify.application.model.request;

import com.example.focusify.domain.todo.Status;
import java.util.Objects;

public class UpdateTodoRequest {
  private Long id;
  private String title;
  private String description;
  private Status status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

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
    UpdateTodoRequest that = (UpdateTodoRequest) o;
    return Objects.equals(id, that.id) && Objects.equals(title, that.title)
        && Objects.equals(description, that.description) && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, status);
  }

  @Override
  public String toString() {
    return "UpdateTodoRequest{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", status=" + status +
        '}';
  }
}
