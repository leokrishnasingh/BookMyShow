package com.example.bookmyshow.services;

import com.example.bookmyshow.models.User;

public interface UserService {
    User saveUser(String name , String email , String password);
}
