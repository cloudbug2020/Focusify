package com.example.focusify.adapter.db.panache;

import com.example.focusify.adapter.db.panache.model.TodoEntity;
import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.exception.TodoNotFoundException;
import com.example.focusify.usecase.todo.port.TodoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import java.util.Optional;

public class PanacheTodoRepository implements TodoRepository, PanacheRepository<TodoEntity> {

  @Override
  public Todo getTodoById(Long id) {
    final var foundTodo = find("id", id)
        .stream()
        .findFirst()
        .orElseThrow(TodoNotFoundException::new);

    return new Todo(foundTodo.id, foundTodo.title, foundTodo.description, foundTodo.status);
  }

  @Override
  public List<Todo> getTodosByStatus(Status status) {
    return find("status", status)
        .stream()
        .map(a -> new Todo(a.id, a.title, a.description, a.status))
        .toList()
        ;
  }

  @Override
  public List<Todo> getAllTodos() {
    return listAll()
        .stream()
        .map(a -> new Todo(a.id, a.title, a.description, a.status))
        .toList()
        ;
  }

  @Override
  public void deleteTodo(Todo todo) {}

  @Override
  public Todo updateTodo(Todo todo) {
    return null;
  }

  @Override
  public Todo createTodo(Todo todo) {
    final TodoEntity todoEntity = new TodoEntity();
    todoEntity.title = todo.getTitle();
    todoEntity.description = todo.getDescription();
    todoEntity.status = todo.getStatus();
    todoEntity.persist();

    return new Todo(todoEntity.id, todoEntity.title, todoEntity.description, todoEntity.status);
  }
}
