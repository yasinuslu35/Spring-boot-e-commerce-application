package com.yasin.e_commerce.entities.dto.requestes;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;
	
	private String address;
	
	private String city;
	
	private String country;
	
	private String phone;
	
}
