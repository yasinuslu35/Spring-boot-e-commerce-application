package com.yasin.e_commerce.entities.dto.requestes;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date birthDate;

}
