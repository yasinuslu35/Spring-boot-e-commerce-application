package com.yasin.e_commerce.entities.concretes;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","products"})
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private long supplierId;
	
	@Column(name = "company_name",nullable = false)
	private String companyName;
	
	@Column(name = "contact_name",nullable = false)
	private String contactName;
	
	@Column(name = "contact_title",nullable = false)
	private String contactTitle;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "city",nullable = false)
	private String city;
	
	@Column(name = "company_region")
	private String companyRegion;
	
	@Column(name = "company_country",nullable = false)
	private String companyCountry;
	
	@Column(name = "company_phone")
	private String companyPhone;
	
	@Column(name = "company_homepage")
	private String companyHomePage;
	
	
    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products = new HashSet<>();
	
}
