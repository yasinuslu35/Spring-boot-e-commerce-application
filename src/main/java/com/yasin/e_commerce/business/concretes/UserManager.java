package com.yasin.e_commerce.business.concretes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.business.abstracts.UserService;
import com.yasin.e_commerce.core.utilities.mappers.ModelMapperService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.core.utilities.results.SuccessResult;
import com.yasin.e_commerce.dao.abstracts.UserDao;
import com.yasin.e_commerce.entities.concretes.User;
import com.yasin.e_commerce.entities.dto.requestes.UpdateUserRequestDto;


@Service
public class UserManager implements UserService {
	
	private final UserDao userDao;
	private final ModelMapperService modelMapperService;
	
	public UserManager(UserDao userDao,ModelMapperService modelMapperService) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
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
        
        modelMapperService.forRequest().map(updateUserRequestDto,user);
        
        System.out.println("user = "+user);
        userDao.save(user);
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(new SuccessResult(HttpStatus.OK.value(),"Kullanıcı başarıyla güncellendi!"));
	}

}
