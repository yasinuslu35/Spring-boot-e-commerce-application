package com.yasin.e_commerce.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
	
	@NotNull
	@NotBlank
	private String productName;
	
	@NotNull
	@NotBlank
	private String quantityPerUnit;
	
	@NotNull
	@NotBlank
	private Double unitPrice;
	
	@NotNull
	@NotBlank
	private Long unitsInStock;
	

}
