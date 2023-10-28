package com.amit.kumar.blogapi.payloads;

import lombok.Data;

@Data
public class LoginData {
  private String email;
  private String password;
}
