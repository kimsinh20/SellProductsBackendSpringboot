package com.example.employee.controller;
import com.example.employee.Dto.Request.SignInForm;
import com.example.employee.Dto.Request.SignUpForm;
import com.example.employee.Dto.Respond.JwtRespond;
import com.example.employee.Dto.Respond.ResponMessage;
import com.example.employee.model.ERole;
import com.example.employee.model.Role;
import com.example.employee.model.User;
import com.example.employee.security.UserDetail.UserDetailService;
import com.example.employee.security.UserDetail.UserPrinciple;
import com.example.employee.security.jwt.JwtProvider;
import com.example.employee.service.Impl.RoleService;
import com.example.employee.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailService userDetailServices;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if(userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }
        User user = new User(signUpForm.getUsername(),signUpForm.getName(), signUpForm.getEmail(),passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(ERole.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(ERole.PM).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(ERole.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        JwtRespond newRespond = new JwtRespond(token, userPrinciple.getName(), userPrinciple.getAuthorities());
        return ResponseEntity.ok().body(newRespond);
    }
}
