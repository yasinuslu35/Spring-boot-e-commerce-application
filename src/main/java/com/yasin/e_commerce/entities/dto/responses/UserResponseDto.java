package com.yasin.e_commerce.entities.dto.responses;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
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
