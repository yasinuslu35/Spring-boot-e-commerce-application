package com.yasin.e_commerce.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.SellerProduct;

public interface SellerProductDao extends JpaRepository<SellerProduct, Long> {
	Optional<SellerProduct> findByProduct_ProductName(String productName);
	Optional<SellerProduct> findByProduct_ProductId(long productId);
	
}
