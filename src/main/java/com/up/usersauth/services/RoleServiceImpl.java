package com.up.usersauth.services;

import com.up.usersauth.entity.Role;
import com.up.usersauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.findById(id).map(r -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<Role> update(Role role) {
        return repository.findById(role.getId())
                .map(r -> repository.save(role));
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
