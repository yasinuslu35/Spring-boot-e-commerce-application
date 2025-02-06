package com.yasin.e_commerce.dao.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.ProductWithCategoryDto;



public interface ProductDao extends JpaRepository<Product, Long> {
	
	
	@Query("Select new com.yasin.e_commerce.entities.dto.ProductWithCategoryDto"
			+ "(p.id, p.productName, p.unitPrice, p.unitsInStock ,c.categoryName, s.companyName)"
			+ " From Category c Inner Join c.products p Inner Join p.suppliers s")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	

}
