package com.example.demo.services;

import com.example.demo.model.User;

public interface UserService {
    void createUser(String userName, String password);
    User getUser(long userId);
    User updateUser(User updatedUser);
    User deleteUser(long userId);
}
