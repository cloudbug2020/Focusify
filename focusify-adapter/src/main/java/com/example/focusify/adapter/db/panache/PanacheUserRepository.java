package com.example.focusify.adapter.db.panache;

import com.example.focusify.adapter.db.panache.model.UserEntity;
import com.example.focusify.domain.user.User;
import com.example.focusify.usecase.user.exception.UserNotFoundException;
import com.example.focusify.usecase.user.port.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class PanacheUserRepository implements UserRepository, PanacheRepository<UserEntity> {

  public static final String USER_WITH_PROVIDED_EMAIL_NOT_FOUND = "User with provided email not found.";
  public static final String USER_WITH_PROVIDED_ID_NOT_FOUND = "User with provided id not found.";

  @Override
  public User findUserById(Long id) {
    final UserEntity userEntity =
        findByIdOptional(id)
            .orElseThrow(() -> new UserNotFoundException(USER_WITH_PROVIDED_ID_NOT_FOUND));
    return User.builder()
        .id(userEntity.id)
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .build();
  }

  @Override
  public User createUser(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(user.getUsername());
    userEntity.setEmail(user.getEmail());

    userEntity.persist();

    return User.builder()
        .id(userEntity.id)
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .build();
  }

  @Override
  public User updateUser(User user) {
    final UserEntity userEntity =
        findByIdOptional(user.getId())
            .orElseThrow(() -> new UserNotFoundException(USER_WITH_PROVIDED_ID_NOT_FOUND));
    userEntity.setEmail(user.getEmail());
    userEntity.setUsername(user.getUsername());

    return User.builder()
        .id(userEntity.id)
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .build();
  }

  @Override
  public void deleteUser(Long id) {
    final UserEntity userEntity =
        findByIdOptional(id)
            .orElseThrow(() -> new UserNotFoundException(USER_WITH_PROVIDED_ID_NOT_FOUND));
    userEntity.delete();
  }
}
