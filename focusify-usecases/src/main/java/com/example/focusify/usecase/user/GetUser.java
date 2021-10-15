package com.example.focusify.usecase.user;

import com.example.focusify.domain.user.User;
import com.example.focusify.usecase.user.port.UserRepository;

public class GetUser {

  private UserRepository userRepository;

  public GetUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findById(Long id) {
    return this.userRepository.findUserById(id);
  }

}
