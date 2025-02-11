package com.yasin.e_commerce.entities.dto.responses;

import java.util.List;

import com.yasin.e_commerce.entities.concretes.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponseDto {
	private long brandId;
	private String brandName;
	private List<Product> products;

}
