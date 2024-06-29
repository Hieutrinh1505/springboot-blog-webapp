package net.javaguide.springboot.service;

import net.javaguide.springboot.dto.RegistrationDto;
import net.javaguide.springboot.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
