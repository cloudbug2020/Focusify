package com.example.focusify.usecase.user;

import com.example.focusify.domain.user.User;
import com.example.focusify.usecase.user.port.UserRepository;

public class UpdateUser {

  private UserRepository userRepository;

  public UpdateUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User update(User user) {
    return this.userRepository.updateUser(user);
  }

}
