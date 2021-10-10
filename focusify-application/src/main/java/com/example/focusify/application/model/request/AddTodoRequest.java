package com.example.focusify.application.model.request;

import com.example.focusify.domain.todo.Status;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AddTodoRequest {

  @Length(min = 1, max = 255)
  private String title;

  @Length(min = 1, max = 255)
  private String description;

  @NotNull private Status status;
}
