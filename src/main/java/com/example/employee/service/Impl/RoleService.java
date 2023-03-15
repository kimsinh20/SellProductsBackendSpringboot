package com.example.employee.service.Impl;

import com.example.employee.model.ERole;
import com.example.employee.model.Role;
import com.example.employee.respository.RoleRepository;
import com.example.employee.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
