package com.example.focusify.application.model.request;

import javax.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateUserRequest {
  @Length(min=1, max=100)
  private String username;

  @Email
  private String email;
}
