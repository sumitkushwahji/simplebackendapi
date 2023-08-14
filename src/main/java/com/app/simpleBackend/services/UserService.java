package com.app.simpleBackend.services;

import com.app.simpleBackend.entities.User;

import java.util.List;

public interface UserService {


    List<User> getUsers();

    User createUser(User user);
}
