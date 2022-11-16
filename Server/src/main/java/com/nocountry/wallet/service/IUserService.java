package com.nocountry.wallet.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserService{
    boolean save(UserCreateDTO userDTO);
    String userAuth(AuthRequestDTO authRequest);

    Optional<UserEntity> findById(Long id);
    UserResponseDTO getUserById(Long userId);
    void deleteUser(Long id);

}
