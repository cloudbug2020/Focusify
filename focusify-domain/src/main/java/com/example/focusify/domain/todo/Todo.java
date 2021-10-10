package com.example.focusify.domain.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Todo {

  private Long id;
  private String title;
  private String description;
  private Status status;

  public static TodoBuilder builder() {
    return new TodoBuilder();
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
