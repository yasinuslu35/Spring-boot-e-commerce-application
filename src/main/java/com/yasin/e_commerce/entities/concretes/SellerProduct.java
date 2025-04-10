package com.yasin.e_commerce.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sellerProducts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "units_in_stock")
	private Long unitsInStock;
	
	
	
	
	
	
	
	
	
	
}
