package com.amit.kumar.blogapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @NotBlank(message = "Name Should not blank")
	private String name;
	@Column(unique = true,updatable = false)
	@Email(message = "Email should be Valid")
	@NotBlank(message = "Email should not Blank")
	private String email;
	@NotBlank(message = "Password should not blank")
	private String password;
	private String about;
}
