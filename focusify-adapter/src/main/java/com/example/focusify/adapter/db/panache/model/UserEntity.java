package com.example.focusify.adapter.db.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserEntity extends PanacheEntity {

  private String username;
  private String email;

}
