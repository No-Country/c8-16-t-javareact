package com.nocountry.wallet.service.impl;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.mapper.UserMapper;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.service.IUserService;
import com.nocountry.wallet.auth.JwtUtils;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    @Override
    public void deleteUser(Long id) {
        UserEntity userSelected = userRepository.findById(id).orElseThrow(()-> new BadRequestException(ErrorEnum.OBJECT_NOT_FOUND.getMessage()));
        userSelected.setSoftDelete(true);
        userRepository.save(userSelected);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserResponseDTO getUserById(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty())
            throw new BadRequestException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());

        return userMapper.convert2DTO(userOptional.get());
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
