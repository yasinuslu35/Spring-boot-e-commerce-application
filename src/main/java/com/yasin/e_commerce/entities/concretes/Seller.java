package com.yasin.e_commerce.entities.concretes;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_id")
	private long sellerId;
	
	@Column(name = "company_name",nullable = false)
	private String companyName;
		
	@Column(name = "company_address")
	private String companyAddress;
	
	@Column(name = "city",nullable = false)
	private String city;
		
	@Column(name = "company_country",nullable = false)
	private String companyCountry;
	
	@Column(name = "company_phone")
	private String companyPhone;
	
	@Column(name = "company_homepage")
	private String companyHomePage;
	
    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private List<SellerProduct> sellerProducts = new ArrayList<>();
	
}
