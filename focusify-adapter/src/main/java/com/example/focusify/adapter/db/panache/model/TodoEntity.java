package com.example.focusify.adapter.db.panache.model;

import com.example.focusify.domain.todo.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "TODO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TodoEntity extends PanacheEntity {
  private String title;
  private String description;
  private Status status;
}
