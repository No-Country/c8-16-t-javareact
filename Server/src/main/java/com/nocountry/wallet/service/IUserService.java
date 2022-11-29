package com.nocountry.wallet.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateDTO;
import com.nocountry.wallet.models.response.UserDetailDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService{
    boolean save(UserCreateDTO userDTO);
    String userAuth(AuthRequestDTO authRequest);

    Optional<UserEntity> findById(Long id);
    UserDetailDTO getUserById(Long userId);
    ResponseEntity<Void> deleteUser(Long id, String token);
    List<UserDetailDTO> getAllUsers();

    UserCreateDTO updateUser(UserUpdateDTO userUpdateDTO, Integer id);
}
