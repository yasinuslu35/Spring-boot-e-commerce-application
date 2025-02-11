package com.yasin.e_commerce.entities.dto.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","products"})
public class BrandResponseDto {
	private String brandName;
	private List<ProductResponseDto> products;

}
