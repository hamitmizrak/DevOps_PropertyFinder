package com.hamitmizrak.bean.login;

import com.hamitmizrak.bean.PasswordEncoderBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  IUserService {

    //@Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoderBean  passwordEncoder;

    //parametreli constructor
    //@AutoWired
    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    //  register  ==> implements
    @Override
    public UserEntity save(UserRegisterDto registerDto) {
        List<RoleEntity> list=new ArrayList<>();
        list.add(new RoleEntity("ROLES_USER"));
        UserEntity userEntity= UserEntity.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                //şifreyi maskelemek
               .password(passwordEncoder.passwordEncoder().encode(registerDto.getPassword()))
                .roles(list)
                .build();
        return repository.save(userEntity);
    }

    //UserDetailsServisten geliyor
    //Kullancıınıdan alınan email ve password respositorde karşılaştırmak
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=repository.findByEmail(email);
        if(userEntity==null){
            throw new UsernameNotFoundException("Geçersiz kullanıcı adı veya şifre");
        }
        //roleAuthorities
        //Şifre
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), roleAuthorities(userEntity.getRoles()));
    }

    //Rol belirleme
    private Collection<? extends GrantedAuthority>roleAuthorities  (Collection < RoleEntity > roles) {
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
   }
}
