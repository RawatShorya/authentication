package com.srm.authentication.Entity;

import lombok.Data;

@Data
public class LoginResponseDTO {
  public boolean acceptance;
  public int role_type;
}
