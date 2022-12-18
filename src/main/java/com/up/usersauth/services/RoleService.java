package com.up.usersauth.services;

import com.up.usersauth.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    Optional<Role> findById(long id);

    Role create(Role role);

    boolean deleteById(long id);

    Optional<Role> update(Role role);

    Optional<Role> findByName(String name);
}
