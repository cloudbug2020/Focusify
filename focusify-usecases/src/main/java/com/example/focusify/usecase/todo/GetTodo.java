package com.example.focusify.usecase.todo;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.port.TodoRepository;
import java.util.List;

public class GetTodo {

  private final TodoRepository todoRepository;

  public GetTodo(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo getTodoById(Long id) {
    return todoRepository.getTodoById(id);
  }

  public List<Todo> getTodosByStatus(Status status) {
    return todoRepository.getTodosByStatus(status);
  }

  public Long countTodos() {
    return todoRepository.countTodos();
  }
}
