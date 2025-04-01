package com.yasin.e_commerce.entities.dto.responses;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {
    @Pattern(
            regexp = "^[a-zA-Z0-9](?!.*[_.]{2})[a-zA-Z0-9._]{1,14}[a-zA-Z0-9]$",
            message = "Kullanıcı adı 2-16 karakter olmalı, harf, rakam, '_' ve '.' içerebilir, ancak '__' veya '..' olamaz."
        )
	private String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Şifre en az bir büyük harf, bir küçük harf ve bir rakam içermelidir."
        )
	private String password;

}
