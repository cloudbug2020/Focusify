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

  public static final String TODO_WITH_PROVIDED_ID_NOT_FOUND = "Todo with provided id not found.";

  @Override
  public Todo getTodoById(Long id) {
    final TodoEntity todoEntity = findByIdOptional(id).orElseThrow(() -> new TodoNotFoundException(
        TODO_WITH_PROVIDED_ID_NOT_FOUND));

    return Todo.builder().id(todoEntity.id).title(todoEntity.getTitle()).description(todoEntity.getDescription()).status(todoEntity.getStatus()).build();
  }

  @Override
  public List<Todo> getTodosByStatus(Status status) {
    return find("status", status).stream()
        .map(dataset -> Todo.builder().id(dataset.id).title(dataset.getTitle()).description(dataset.getDescription()).status(dataset.getStatus()).build())
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
    final var todoEntity = findByIdOptional(todo.getId()).orElseThrow(() -> new TodoNotFoundException(TODO_WITH_PROVIDED_ID_NOT_FOUND));
    todoEntity.setTitle(todo.getTitle());
    todoEntity.setDescription(todo.getDescription());
    todoEntity.setStatus(todo.getStatus());

    return Todo.builder().id(todoEntity.id).title(todoEntity.getTitle()).description(todoEntity.getDescription()).status(todoEntity.getStatus()).build();
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
