package com.example.bookmyshow.services;

import com.example.bookmyshow.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(String name , String email , String password);
}
