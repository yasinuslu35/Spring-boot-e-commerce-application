package com.yasin.e_commerce.business.concretes;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yasin.e_commerce.core.utilities.mappers.ModelMapperService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.SuccessDataResult;
import com.yasin.e_commerce.dao.abstracts.UserDao;
import com.yasin.e_commerce.entities.Role;
import com.yasin.e_commerce.entities.concretes.User;
import com.yasin.e_commerce.entities.dto.requestes.RegisterUserDto;
import com.yasin.e_commerce.entities.dto.responses.LoginResponse;
import com.yasin.e_commerce.entities.dto.responses.LoginUserDto;
import com.yasin.e_commerce.entities.dto.responses.UserResponseDto;

@Service
public class AuthService {
	private final UserDao userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;
	private final ModelMapperService modelMapperService;

	private final JwtService jwtService;

	public AuthService(UserDao userRepository, JwtService jwtService, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, ModelMapperService modelMapperService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapperService = modelMapperService;
		this.jwtService = jwtService;
	}

	public ResponseEntity<DataResult<UserResponseDto>> signup(RegisterUserDto input) {

		if (!input.isPasswordEqual()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<UserResponseDto>(null,
					HttpStatus.BAD_REQUEST.value(), "Şifreler eşleşmiyor!,Tekrar deneyiniz."));
		}
		if (userRepository.existsByUsername(input.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDataResult<UserResponseDto>(null,
					HttpStatus.BAD_REQUEST.value(), "Kullanıcı zaten mevcut"));
		}

		User user = new User();
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setFirstName(input.getFirstName());
		user.setLastName(input.getLastName());
		user.setBirthDate(input.getBirthDate());
		user.setAuthorities(Set.of(Role.ROLE_USER));

		userRepository.save(user);

		UserResponseDto userResponseDto = modelMapperService.forResponse().map(user, UserResponseDto.class);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<UserResponseDto>
				(userResponseDto,HttpStatus.OK.value() ,"Kullanıcı başarıyla eklendi!"));

	}

	public ResponseEntity<DataResult<LoginResponse>> authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
		User authenticatedUser = userRepository.findByUsername(input.getUsername()).orElseThrow();
		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new SuccessDataResult<LoginResponse>
				(loginResponse,HttpStatus.OK.value() ,"Giriş başarılı"));
	}

}
