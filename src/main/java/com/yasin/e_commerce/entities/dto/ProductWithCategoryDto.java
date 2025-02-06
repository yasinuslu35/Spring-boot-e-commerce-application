package com.yasin.e_commerce.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDto {
	private long id;
	private String productName;
	private double unitPrice;
	private long unitsInStock;
	private String categoryName;
	private String supplierName;
	
	
}
