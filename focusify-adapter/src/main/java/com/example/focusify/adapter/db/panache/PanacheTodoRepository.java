package com.example.focusify.adapter.db.panache;

import com.example.focusify.adapter.db.panache.model.TodoEntity;
import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.exception.TodoNotFoundException;
import com.example.focusify.usecase.todo.port.TodoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import java.util.stream.Collectors;

public class PanacheTodoRepository implements TodoRepository, PanacheRepository<TodoEntity> {

  @Override
  public Todo getTodoById(Long id) {
    long dbId = id;
    final var foundTodo = findById(dbId);

    if (foundTodo != null) {
      return new Todo(id, foundTodo.getTitle(), foundTodo.getDescription(), foundTodo.getStatus());
    } else {
      throw new TodoNotFoundException("Todo with id=" + id + " not found");
    }
  }

  @Override
  public List<Todo> getTodosByStatus(Status status) {
    return find("status", status).stream()
        .map(dataset -> new Todo(dataset.id, dataset.getTitle(), dataset.getDescription(), dataset.getStatus()))
        .collect(Collectors.toList());
  }

  @Override
  public Long countTodos() {
    return count();
  }

  @Override
  public void deleteTodo(Long id) {
    final var entityToDelete = findById(id);
    delete(entityToDelete);
  }

  @Override
  public Todo updateTodo(Todo todo) {
    final var byId = findById(todo.getId());
    if (byId == null) {
      throw new TodoNotFoundException("Todo with id=" + todo.getId() + " not found");
    }
    byId.setTitle(todo.getTitle());
    byId.setDescription(todo.getDescription());
    byId.setStatus(todo.getStatus());
    return new Todo(byId.id, byId.getTitle(), byId.getDescription(), byId.getStatus());
  }

  @Override
  public Todo createTodo(Todo todo) {
    final TodoEntity todoEntity = new TodoEntity();
    todoEntity.setTitle(todo.getTitle());
    todoEntity.setDescription(todo.getDescription());
    todoEntity.setStatus(todo.getStatus());
    todoEntity.persist();

    return new Todo(todoEntity.id, todoEntity.getTitle(), todoEntity.getDescription(), todoEntity.getStatus());
  }
}
