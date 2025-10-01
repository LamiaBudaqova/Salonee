package com.backend134.salon.services;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.models.User;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);// qeydiyyat uğurlu olub olmadığını qaytarır
    User findByEmail(String email);// email ilə istifadəçini tapır
}
