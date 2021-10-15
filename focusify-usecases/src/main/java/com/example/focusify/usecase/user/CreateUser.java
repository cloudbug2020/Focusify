package com.example.focusify.usecase.user;

import com.example.focusify.domain.user.User;
import com.example.focusify.usecase.user.port.UserRepository;

public class CreateUser {

  private UserRepository userRepository;

  public CreateUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return this.userRepository.createUser(user);
  }
}
