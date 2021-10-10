package com.example.focusify.adapter.controller.model;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Objects;

@RegisterForReflection
public class TodoWeb {

  private Long id;
  private String title;
  private String description;
  private Status status;

  public TodoWeb() {}

  public TodoWeb(String title, String description, Status done) {
    this.title = title;
    this.description = description;
    this.status = done;
  }

  public TodoWeb(Long id, String title, String description,
      Status status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
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
    TodoWeb todoWeb = (TodoWeb) o;
    return Objects.equals(title, todoWeb.title) && Objects.equals(description,
        todoWeb.description) && status == todoWeb.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, status);
  }

  @Override
  public String toString() {
    return "TodoWeb{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", done=" + status +
        '}';
  }

  public static Todo toTodo(TodoWeb todoWeb) {
    return Todo.builder()
        .id(todoWeb.getId())
        .title(todoWeb.getTitle())
        .description(todoWeb.getDescription())
        .done(todoWeb.getStatus())
        .build();
  }

  public static TodoWeb toTodoWeb(Todo todo) {
    var todoWeb = new TodoWeb();
    todoWeb.setId(todo.getId());
    todoWeb.setTitle(todo.getTitle());
    todoWeb.setDescription(todo.getDescription());
    todoWeb.setStatus(todo.getStatus());
    return todoWeb;
  }
}
