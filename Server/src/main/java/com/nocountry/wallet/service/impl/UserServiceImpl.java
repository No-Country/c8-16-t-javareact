package com.nocountry.wallet.service.impl;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.auth.JwtUtils;
import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.mapper.UserMapper;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateRequest;
import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.models.response.UserUpdateResponse;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.service.IAuthService;
import com.nocountry.wallet.service.IUserService;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final DaoAuthenticationProvider authProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    IAuthService authService;

/*
    @Override
    public UserCreateDTO updateUser(UserUpdateDTO userUpdateDTO, Integer id) {
        Optional<UserEntity> result = userRepository.findById(Long.valueOf(id));
        if(result.isEmpty()) {
            throw new NotFoundException("Invalid ID");
        }
        UserEntity res = result.get();
        userUpdateDTO.setId(id);
        UserEntity user = userMapper.userUpdateDTO2Entity(userUpdateDTO);

        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setFirstName(res.getFirstName());
        user.setLastName(res.getLastName());
        user.setDni(res.getDni());
        user.setPassword(encodedPassword);
        user.setTimestamp(res.getTimestamp());


        UserEntity response = userRepository.save(user);
        return userMapper.userEntity2DTO(response);
    }
*/

    @Override
    public ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest, String token) throws IOException {
        ResponseEntity<UserUpdateResponse> response;
        if (authService.roleValidator(id, token)){
            UserEntity entity = userRepository.findById(id).orElseThrow();
            if (validInput(userUpdateRequest.getFirstName())) entity.setFirstName(userUpdateRequest.getFirstName());
            if (validInput(userUpdateRequest.getLastName())) entity.setLastName(userUpdateRequest.getLastName());
            if(validInput(userUpdateRequest.getDni())) entity.setDni(userUpdateRequest.getDni());
            if (validInput(userUpdateRequest.getPassword())) entity.setPassword(userUpdateRequest.getPassword());
            if (validInput(userUpdateRequest.getPhoto())) entity.setPhoto(userUpdateRequest.getPhoto());
            response = ResponseEntity.status(HttpStatus.OK).body(userMapper.userEntity2UserUpdateResponse(userRepository.save(entity)));
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

    public boolean validInput(String input){
        return (input != null && !input.isEmpty() && !input.isBlank());
    }
@Override
public ResponseEntity<Void> deleteUser(Long id, String token) {
    ResponseEntity<Void> response;
    if (authService.roleValidator(id, token)){
        userRepository.deleteById(id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
        response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    return response;
}

    @Override
    public List<UserDetailDTO> getAllUsers() {
        log.info("Search all users in database with findAll and mapping to List<UserDetailDTO>");
        return userMapper.convertEntities2ListDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserDetailDTO getUserById(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty())
            throw new BadRequestException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());

        return userMapper.convert2DetailDTO(userOptional.get());
    }
    @Override
    public boolean save(UserCreateDTO userDTO){
        this.userExist(userDTO);
        UserEntity userEntity = userMapper.convert2Entity(userDTO);
        String password = passwordEncoder.encode(userDTO.getPassword());
        userEntity.setPassword(password);
        userRepository.save(userEntity);

        return userEntity != null;
    }
    public boolean userExist(UserCreateDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new BadRequestException(ErrorEnum.USER_ALREADY_EXIST.getMessage());
        }
        return false;
    }
    @Override
    public String userAuth(AuthRequestDTO authRequest){
        UserDetails userDetails;
        log.info("Try authenticate");
        try {
            Authentication auth = authProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new BadRequestException(ErrorEnum.BAD_CREDENTIALS.getMessage());
        }
        log.info("Generate Token for {}", authRequest.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);
        return jwt;
    }
}
