package com.example.focusify.adapter.controller.model;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class TodoWeb {

  private Long id;
  private String title;
  private String description;
  private Status status;

  public static Todo toTodo(TodoWeb todoWeb) {
    return Todo.builder()
        .id(todoWeb.getId())
        .title(todoWeb.getTitle())
        .description(todoWeb.getDescription())
        .status(todoWeb.getStatus())
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
