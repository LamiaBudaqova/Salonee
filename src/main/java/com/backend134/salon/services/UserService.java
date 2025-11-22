package com.backend134.salon.services;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.models.User;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);// qeydiyyat uğurlu olub olmadıgını qaytarır
    User findByEmail(String email);// email ile istifadeçini tapır
    User updateProfile(Long id, String name, String surname, String email);

}
