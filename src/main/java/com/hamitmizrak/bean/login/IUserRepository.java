package com.hamitmizrak.bean.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {

    //login email adress
    UserEntity findByEmail(String email);
}
