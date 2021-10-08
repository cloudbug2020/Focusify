package com.example.focusify.usecase.todo.port;

import com.example.focusify.domain.todo.Status;
import com.example.focusify.domain.todo.Todo;
import java.util.List;

public interface TodoRepository {

  Todo getTodoById(Long id);

  List<Todo> getTodosByStatus(Status status);

  List<Todo> getAllTodos();

  Long countTodos();

  void deleteTodo(Long id);

  Todo updateTodo(Todo todo);

  Todo createTodo(Todo todo);
}
