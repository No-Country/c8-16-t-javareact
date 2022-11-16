package com.nocountry.wallet.auth;

import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service@AllArgsConstructor
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).get();
        if(userEntity == null){
            throw new UsernameNotFoundException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList());
    }




}
