package com.example.focusify.adapter.db.panache.model;

import com.example.focusify.domain.todo.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TODO")
public class TodoEntity extends PanacheEntity {
  public String title;
  public String description;
  public Status status;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoEntity that = (TodoEntity) o;
    return status == that.status
        && Objects.equals(title, that.title)
        && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, status);
  }

  @Override
  public String toString() {
    return "TodoEntity{"
        + "title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", done="
        + status
        + ", id="
        + id
        + '}';
  }
}
