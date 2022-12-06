package com.example.practica8.repo;

import com.example.practica8.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String username);

    User findUserById(Long id);
}