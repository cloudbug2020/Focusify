package com.example.focusify.domain.todo;

import java.util.Objects;

public class Todo {

  private Long id;
  private String title;
  private String description;
  private Status status;

  public Todo() {}

  public Todo(Long id, String title, String description,
      Status status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
  }

  public static TodoBuilder builder() {
    return new TodoBuilder();
  }

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
    Todo todo = (Todo) o;
    return status == todo.status && Objects.equals(id, todo.id) && Objects.equals(
        title, todo.title) && Objects.equals(description, todo.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, status);
  }

  @Override
  public String toString() {
    return "Todo{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", done=" + status +
        '}';
  }

  public static class TodoBuilder {
    private Long id;
    private String title;
    private String description;
    private Status done;

    public TodoBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public TodoBuilder title(String title) {
      this.title = title;
      return this;
    }

    public TodoBuilder description(String description) {
      this.description = description;
      return this;
    }

    public TodoBuilder done(Status done) {
      this.done = done;
      return this;
    }

    public Todo build() {
      return new Todo(id, title, description, done);
    }
  }
}
