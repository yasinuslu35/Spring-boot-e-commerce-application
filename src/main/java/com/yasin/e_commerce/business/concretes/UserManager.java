package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.UserService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.dao.abstracts.UserDao;
import com.yasin.e_commerce.entities.concretes.User;
import com.yasin.e_commerce.entities.dto.requestes.UpdateUserRequestDto;


@Service
public class UserManager implements UserService {
	
	private final UserDao userDao;
	
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public ResponseEntity<DataResult<List<User>>> getAllUser() {

		return null;
	}

	@Override
	public ResponseEntity<Result> update(UpdateUserRequestDto updateUserRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username).orElseThrow(() -> new RuntimeException
        		("Kullanıcı Bulunamadı"));
        
        System.out.println("user = "+user);
		return null;
	}

}
