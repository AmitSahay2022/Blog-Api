package com.amit.kumar.blogapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
	private long id;
	@NotBlank(message = "Name Should not blank")
	private String name;
	@Email(message = "Email should be Valid")
	@NotBlank(message = "Email should not Blank")
	private String email;
	@NotBlank(message = "Password should not blank")
	private String password;
	private String about;
}
