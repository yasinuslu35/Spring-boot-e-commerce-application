package com.yasin.e_commerce.dao.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasin.e_commerce.entities.concretes.User;


public interface UserDao extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);

}
