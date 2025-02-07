package com.yasin.e_commerce.dao.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Long> {
	

}
