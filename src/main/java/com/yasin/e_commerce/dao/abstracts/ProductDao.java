package com.yasin.e_commerce.dao.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.responses.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.responses.ProductWithCategoryDto;



public interface ProductDao extends JpaRepository<Product, Long> {
	
	Optional<Product> findByProductName(String productName);
	
	//Optional<List<Product>> findBySuppliers_CompanyName(String companyName);
	
	
	@Query("Select new com.yasin.e_commerce.entities.dto.responses.ProductWithCategoryDto"
			+ "(p.id, p.productName, c.categoryName)"
			+ " From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	@Query("SELECT new com.yasin.e_commerce.entities.dto.responses.ProductWithBrandDto "
			+ "(p.productName, p.brand.brandName) "+
			"FROM Product p WHERE p.brand.brandName = :brandName")
	List<ProductWithBrandDto> findProductsByBrandName(@Param("brandName") String brandName);

	

}
