package com.yasin.e_commerce.dao.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {


}
