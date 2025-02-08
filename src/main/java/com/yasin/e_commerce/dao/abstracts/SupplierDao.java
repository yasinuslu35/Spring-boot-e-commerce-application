package com.yasin.e_commerce.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Long> {

	Optional<Supplier> findByCompanyName(String companyName);
}
