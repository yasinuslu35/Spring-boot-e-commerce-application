package com.yasin.e_commerce.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.Seller;

public interface SellerDao extends JpaRepository<Seller, Long> {

	Optional<Seller> findByCompanyName(String companyName);
	
}
