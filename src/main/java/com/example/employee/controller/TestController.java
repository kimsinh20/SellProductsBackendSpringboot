package com.example.employee.controller;

import com.example.employee.model.ERole;
import com.example.employee.model.Role;
import com.example.employee.service.Impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestController {
    @Autowired
    RoleService roleService;
    @GetMapping("/test")
    public String test() {
        return "test oke";
    }
    @GetMapping("/testrole")
    public Optional<Role> getRole() {
        return roleService.findByName(ERole.ADMIN);
    }
}
