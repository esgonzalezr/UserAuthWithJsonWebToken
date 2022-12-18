package com.up.usersauth.services;

import com.up.usersauth.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id);

    User create(User user);

    boolean deleteById(long id);

    Optional<User> update(User user);

    Optional<User> findByUsername(String username);

}
