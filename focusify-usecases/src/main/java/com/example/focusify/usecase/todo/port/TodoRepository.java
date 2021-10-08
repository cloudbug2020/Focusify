package com.example.focusify.usecase.todo.port;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {

  Todo getTodoById(Long id);

  List<Todo> getTodosByStatus(Status status);

  List<Todo> getAllTodos();

  void deleteTodo(Todo todo);

  Todo updateTodo(Todo todo);

  Todo createTodo(Todo todo);
}
