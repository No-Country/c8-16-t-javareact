package com.nocountry.wallet.security.config.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateRequest;
import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.models.response.UserUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService{
    boolean save(UserCreateDTO userDTO);
    String userAuth(AuthRequestDTO authRequest);

    Optional<UserEntity> findById(Long id);
    UserDetailDTO getUserById(Long userId);
    ResponseEntity<Void> deleteUser(Long id, String token);
    List<UserDetailDTO> getAllUsers();

    ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest, String token) throws IOException;
  /*
    UserCreateDTO updateUser(UserUpdateDTO userUpdateDTO, Integer id);

   */
}
