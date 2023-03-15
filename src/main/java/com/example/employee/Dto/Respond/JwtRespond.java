package com.example.employee.Dto.Respond;
import org.springframework.security.core.GrantedAuthority;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRespond {
    private String token;
    private String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public JwtRespond(String token, String name, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.name =  name;
        this.roles = authorities;
    }
}
