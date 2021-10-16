package com.example.focusify.usecase.user.port;

import com.example.focusify.domain.user.User;

public interface UserRepository {

  User findUserById(Long id);

  User createUser(User user);

  User updateUser(User user);

  void deleteUser(Long id);

}
