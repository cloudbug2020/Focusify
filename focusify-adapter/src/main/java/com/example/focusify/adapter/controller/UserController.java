package com.example.focusify.adapter.controller;

import com.example.focusify.adapter.controller.model.UserWeb;
import com.example.focusify.domain.user.User;
import com.example.focusify.usecase.user.CreateUser;
import com.example.focusify.usecase.user.DeleteUser;
import com.example.focusify.usecase.user.GetUser;
import com.example.focusify.usecase.user.UpdateUser;

public class UserController {

  private CreateUser createUser;
  private DeleteUser deleteUser;
  private GetUser getUser;
  private UpdateUser updateUser;

  public UserController(CreateUser createUser,
      DeleteUser deleteUser, GetUser getUser, UpdateUser updateUser) {
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.getUser = getUser;
    this.updateUser = updateUser;
  }

  public UserWeb createUser(UserWeb userWeb) {
    return UserWeb.toUserWeb(this.createUser.createUser(UserWeb.toUser(userWeb)));
  }

  public void deleteUser(Long id) {
    deleteUser.delete(id);
  }

  public UserWeb getUser(Long id) {
    final User user = getUser.findById(id);

    return UserWeb.toUserWeb(user);
  }

  public UserWeb updateUser(UserWeb userWeb) {
    final User user = updateUser.update(UserWeb.toUser(userWeb));
    return UserWeb.toUserWeb(user);
  }

}
