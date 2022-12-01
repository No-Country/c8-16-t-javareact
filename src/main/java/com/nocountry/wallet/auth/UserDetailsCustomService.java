package com.nocountry.wallet.auth;

import com.nocountry.wallet.models.entity.RoleEntity;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service@AllArgsConstructor@Slf4j
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Find user {} in database", username);
        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(ErrorEnum.OBJECT_NOT_FOUND.getMessage()));
        log.info("Collect user {} authorities", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(userEntity.getRoles() ==null){
            throw new BadCredentialsException(ErrorEnum.USER_NOT_AUTHORITIES.getMessage());
        }
        userEntity.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }




}
