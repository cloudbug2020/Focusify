package com.example.focusify.adapter.db.panache;

import com.example.focusify.adapter.controller.model.TodoWeb;
import com.example.focusify.adapter.db.panache.model.TodoEntity;
import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import com.example.focusify.usecase.todo.exception.TodoNotFoundException;
import com.example.focusify.usecase.todo.port.TodoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;

public class PanacheTodoRepository implements TodoRepository, PanacheRepository<TodoEntity> {

  @Override
  public Todo getTodoById(Long id) {
    long dbId = id;
    final var foundTodo = findById(dbId);

    if (foundTodo != null) {
      return new Todo(foundTodo.id, foundTodo.title, foundTodo.description, foundTodo.status);
    } else {
      throw new TodoNotFoundException("Todo with id=" + id + " not found");
    }


  }

  @Override
  public List<Todo> getTodosByStatus(Status status) {
    return find("status", status).stream()
        .map(a -> new Todo(a.id, a.title, a.description, a.status))
        .toList();
  }

  @Override
  public List<Todo> getAllTodos() {
    return listAll().stream().map(a -> new Todo(a.id, a.title, a.description, a.status)).toList();
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
    if(byId == null) {
      throw new TodoNotFoundException("Todo with id=" + todo.getId() + " not found");
    }
    byId.title = todo.getTitle();
    byId.description = todo.getDescription();
    byId.status = todo.getStatus();
    return new Todo(byId.id, byId.title, byId.description, byId.status);
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
