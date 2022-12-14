package com.nocountry.wallet.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateRequest;
import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.models.response.UserPaginatedResponse;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.models.response.UserUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService{
    boolean save(UserCreateDTO userDTO);
    UserResponseDTO userAuth(AuthRequestDTO authRequest);

    Optional<UserEntity> findById(Long id);
    UserDetailDTO getUserById(Long userId);
    ResponseEntity<Void> deleteUser(Long id, String token);
    List<UserDetailDTO> getAllUsers();

    ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest, String token) throws IOException;

    UserPaginatedResponse findAllPaginated(Integer numberOfPage, Integer quantityOfResults);
  /*
    UserCreateDTO updateUser(UserUpdateDTO userUpdateDTO, Integer id);

   */
}
