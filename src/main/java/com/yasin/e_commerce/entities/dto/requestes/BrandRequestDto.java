package com.yasin.e_commerce.entities.dto.requestes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequestDto {
	
	private String brandName;
	private List<String> products;

}
