package com.srm.authentication.Entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "user_login")
@Data
public class User {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id)
        && Objects.equals(name, user.name)
        && Objects.equals(email, user.email)
        && Objects.equals(password, user.password)
        && Objects.equals(roleType, user.roleType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, password, roleType);
  }

  @Column(name = "name")
  String name;

  @Column(name = "email")
  String email;

  @Column(name = "password")
  String password;

  @Column(name = "role_type")
  Long roleType;
}
