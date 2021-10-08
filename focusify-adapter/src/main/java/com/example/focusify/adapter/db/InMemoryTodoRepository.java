package com.example.focusify.adapter.db;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.port.TodoRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTodoRepository implements TodoRepository {

  private final Map<Long, Todo> inMemoryDb = new HashMap<>();

  public InMemoryTodoRepository() {
    inMemoryDb.put(123456L, new Todo(213L, "TodoTitle", "TodoDescription", Status.IN_PROGRESS));
  }

  @Override
  public Todo getTodoById(Long id) {
    return new Todo();
  }

  @Override
  public List<Todo> getTodosByStatus(Status status) {
    return new ArrayList<>(inMemoryDb.values());
  }

  @Override
  public List<Todo> getAllTodos() {
    return new ArrayList<>(inMemoryDb.values());
  }

  @Override
  public void deleteTodo(Todo todo) {}

  @Override
  public Todo updateTodo(Todo todo) {
    return null;
  }

  @Override
  public Todo createTodo(Todo todo) {
    return null;
  }
}
