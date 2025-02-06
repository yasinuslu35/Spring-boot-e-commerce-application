package com.yasin.e_commerce.entities.concretes;

import java.sql.Date;

import com.yasin.e_commerce.entities.abstracts.Human;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employees")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Human {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private long id;
	
	@Column(name = "title",nullable = false)
	private String title;
	
	@Column(name = "hire_date",nullable = false)
	private Date hireDate;
	

}
