package com.nocountry.wallet.auth;

import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.mapper.UserMapper;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.security.config.PasswordEncoder;
import com.nocountry.wallet.service.IAuthService;
import com.nocountry.wallet.service.IUserService;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
//    private final IAccountService iAccountService;
    private final UserDetailsCustomService userCustomService;
    private final IUserService userService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDTO saveUser(UserCreateDTO dto){
        Optional<UserEntity> user = userRepository.findByEmail(dto.getEmail());
        if (!user.isPresent()) {
            if(dto.getPassword().isEmpty())
                throw new BadRequestException(ErrorEnum.EMPTY_PASS.getMessage());
            UserEntity entity = userMapper.convert2Entity(dto);
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword()));
            UserEntity entitySaved = userRepository.save(entity);
            //accountService.addAccount(entitySaved.getEmail(), new CurrencyDto(ECurrency.ARS));
            //accountService.addAccount(entitySaved.getEmail(), new CurrencyDto(ECurrency.USD));
            UserResponseDTO responseDto = userMapper.convert2DTO(entitySaved);
            AuthRequestDTO authDTO = new AuthRequestDTO(dto.getEmail(), dto.getPassword());
            AuthResponseDTO login = login(authDTO);
            responseDto.setJwt(login.getJwt());
            return responseDto;
        }else {
            throw new BadRequestException(ErrorEnum.USER_ALREADY_EXIST.getMessage());
        }
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequest){
        final String username = authRequest.getEmail();
        final String password = authRequest.getPassword();
        if (username.isEmpty() && password.isEmpty())
            throw new BadRequestException("Insert email and password");
        if (!userRepository.findByEmail(username).isPresent())
            throw new BadRequestException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());
        if(passwordEncoder.bCryptPasswordEncoder().matches(password, userRepository.findByEmail(username).get().getPassword())){
            final UserDetails userDetails = userCustomService.loadUserByUsername(username);
            final String jwt = jwtUtils.generateToken(userDetails);
            return new AuthResponseDTO(username,jwt);
        }else{
            throw new BadRequestException(ErrorEnum.INVALID_PASSWORD.getMessage());
        }
    }
}