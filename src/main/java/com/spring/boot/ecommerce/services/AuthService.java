package com.spring.boot.ecommerce.services;

import com.spring.boot.ecommerce.entities.User;
import com.spring.boot.ecommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId){
        User user = userService.authenticated();
        if(!user.hasRole("ROLE_ADMIN") && !user.getId().equals(userId)){
            throw new ForbiddenException("Access denied");
        }
    }
}
