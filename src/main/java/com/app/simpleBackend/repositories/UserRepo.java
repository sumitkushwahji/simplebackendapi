package com.app.simpleBackend.repositories;

import com.app.simpleBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Long> {

//    implimentation will be created by JPA only
    public Optional<User> findByEmail(String email);
}
