package com.example.focusify.usecase.user;

import com.example.focusify.usecase.user.port.UserRepository;

public class DeleteUser {

  private UserRepository userRepository;

  public DeleteUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void delete(Long id) {
    userRepository.deleteUser(id);
  }

}
