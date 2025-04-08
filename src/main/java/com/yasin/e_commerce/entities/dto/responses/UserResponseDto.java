package com.yasin.e_commerce.entities.dto.responses;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private long id;
	private String firstName;

	private String lastName;

	private Date birthDate;

	private String address;

	private String city;

	private String country;

	private String phone;

	private Date createdAt;

	private Date updatedAt;

}
