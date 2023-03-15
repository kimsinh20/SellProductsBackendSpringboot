package com.example.employee.service;

import com.example.employee.model.ERole;
import com.example.employee.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ERole name);
}
