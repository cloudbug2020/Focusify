package com.example.focusify.adapter.controller.model;

import com.example.focusify.domain.user.User;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@Builder
public class UserWeb {

  private Long id;
  private String username;
  private String email;

  public static User toUser(UserWeb userWeb) {
    return User.builder().id(userWeb.getId()).username(userWeb.getUsername()).email(userWeb.getEmail()).build();
  }

  public static UserWeb toUserWeb(User user) {
    return UserWeb.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).build();
  }

}
