package com.hamitmizrak.bean.login;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    UserEntity save(UserRegisterDto registerDto);
}
