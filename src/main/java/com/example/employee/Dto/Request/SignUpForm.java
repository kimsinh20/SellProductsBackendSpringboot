package com.example.employee.Dto.Request;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
//    private String avatar;
    private Set<String> roles;


}
