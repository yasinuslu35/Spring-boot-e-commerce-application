package com.yasin.e_commerce.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProductDto {
    private String sellerName;
    private String productName;
    private String quantityPerUnit;
    private Double unitPrice;
    private Long unitsInStock;

}
