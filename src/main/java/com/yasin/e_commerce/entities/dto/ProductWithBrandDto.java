package com.yasin.e_commerce.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithBrandDto {
	private String ProductName;
	private String BrandName;
	//private double unitPrice;
	

}
