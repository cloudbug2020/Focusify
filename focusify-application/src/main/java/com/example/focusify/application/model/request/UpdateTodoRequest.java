package com.example.focusify.application.model.request;

import com.example.focusify.domain.todo.Status;
import lombok.Data;

@Data
public class UpdateTodoRequest {
  private String title;
  private String description;
  private Status status;
}
