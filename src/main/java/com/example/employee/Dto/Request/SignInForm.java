package com.example.employee.Dto.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInForm {
    private String username;
    private String password;
}
