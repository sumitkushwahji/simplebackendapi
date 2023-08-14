package com.app.simpleBackend.services.impl;

import com.app.simpleBackend.entities.User;
import com.app.simpleBackend.repositories.UserRepo;
import com.app.simpleBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        return userRepo.save(user);
    }
}
