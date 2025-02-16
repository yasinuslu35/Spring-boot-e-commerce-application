package com.yasin.e_commerce.entities.dto.requestes;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
	@NotBlank(message = "Kullanıcı adı boş olamaz.")
	private String username;
	
	@Size(min = 6, message = "Şifreniz en az 6 karakterden oluşmalıdır.")
	private String password;
	
	@NotBlank(message = "Şifre onay kısmı boş olamaz")
	private String passwordConfirm;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(passwordConfirm);
    }

}
