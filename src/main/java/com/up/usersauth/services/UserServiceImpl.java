package com.up.usersauth.services;

import com.up.usersauth.entity.User;
import com.up.usersauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public User create(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.findById(id).map(user -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<User> update(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return repository.findById(user.getId())
                .map(u -> repository.save(user));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
