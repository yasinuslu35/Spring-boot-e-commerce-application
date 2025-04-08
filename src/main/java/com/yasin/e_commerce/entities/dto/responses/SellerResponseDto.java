package com.yasin.e_commerce.entities.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDto {
	private long id;
	private String companyName;
	private String companyAddress;
	private String city;
	private String companyCountry;
	private String companyPhone;
	private String companyHomePage;
	//private List<ProductResponseDto> products;
	private String quantityPerUnit;
	private Double unitPrice;
	private Long unitsInStock;
	
}
