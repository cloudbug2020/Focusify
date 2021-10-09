package com.example.focusify.application.model.request;

import com.example.focusify.domain.todo.Status;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AddTodoRequest {

  @Length(min = 1, max = 255)
  private String title;

  @Length(min = 1, max = 255)
  private String description;

  @NotNull
  private Status status;

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
    AddTodoRequest that = (AddTodoRequest) o;
    return Objects.equals(title, that.title) && Objects.equals(description,
        that.description) && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, status);
  }

  @Override
  public String toString() {
    return "AddTodoRequest{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", status=" + status +
        '}';
  }
}
