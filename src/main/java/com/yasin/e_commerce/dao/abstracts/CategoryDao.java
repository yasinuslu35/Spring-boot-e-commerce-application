package com.yasin.e_commerce.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.Brand;
import com.yasin.e_commerce.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

	Optional<Category> findByCategoryName(String categoryName);
}
