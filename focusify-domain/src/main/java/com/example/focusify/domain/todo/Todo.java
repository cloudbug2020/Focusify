package com.example.focusify.domain.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Todo {
  private Long id;
  private String title;
  private String description;
  private Status status;
}
