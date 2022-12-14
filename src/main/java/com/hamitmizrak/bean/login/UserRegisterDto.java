package com.hamitmizrak.bean.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRegisterDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
