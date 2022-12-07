package com.nocountry.wallet.auth;

import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.mapper.UserMapper;
import com.nocountry.wallet.models.entity.RoleEntity;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserRegisterDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.repository.RoleRepository;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.security.config.PasswordEncoder;
import com.nocountry.wallet.service.EmailService;
import com.nocountry.wallet.service.IAccountService;
import com.nocountry.wallet.service.IAuthService;
import com.nocountry.wallet.utils.enumeration.CurrencyEnum;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final UserDetailsCustomService userCustomService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    private final IAccountService accountService;


    @Override
    public UserResponseDTO updateVerify(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Not found User"));
        user.setVerify(true);
        userRepository.save(user);
        return userMapper.convert2DTO(user);
    }
    @Override
    public UserRegisterDTO saveUser(UserCreateDTO dto){
        Optional<UserEntity> user = userRepository.findByEmail(dto.getEmail());
        if (!user.isPresent()) {
            if(dto.getPassword().isEmpty())
                throw new BadRequestException(ErrorEnum.EMPTY_PASS.getMessage());

            UserEntity entity = userMapper.convert2Entity(dto);
            //entity.setBirthDate(LocalDate.parse(dto.getBirthDate(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
            entity.setBirthDate(dto.getBirthDate());
            entity.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword()));
            Collection<RoleEntity> userRole = roleRepository.findByName("ROLE_USER");
            entity.setRoles((Set<RoleEntity>) userRole);
            UserEntity entitySaved = userRepository.save(entity);
            accountService.createAccount(entitySaved.getId().intValue(), CurrencyEnum.ARS.name());
            accountService.createAccount(entitySaved.getId().intValue(), CurrencyEnum.USDT.name());
            accountService.createAccount(entitySaved.getId().intValue(), CurrencyEnum.BTC.name());
            accountService.createAccount(entitySaved.getId().intValue(), CurrencyEnum.ETH.name());
            if (!entitySaved.getEmail().contains("test"))
                emailService.sendRegisterMail(entitySaved.getEmail());
            UserRegisterDTO responseDto = userMapper.convert2RegDTO(entitySaved);
            AuthRequestDTO authDTO = new AuthRequestDTO(dto.getEmail(), dto.getPassword());
            AuthResponseDTO login = login(authDTO);
            responseDto.setJwt(login.getJwt());
            responseDto.setOtp(emailService.getOtpCode());
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

    @Override
    public boolean roleValidator(Long id, String token) {
        UserEntity entity = getUserEntityByToken(token);
        Long tokenId = entity.getId();
        boolean tokenIsAdmin = false;
        Set<RoleEntity> tokenRoles = entity.getRoles();
        for (RoleEntity role : tokenRoles) {
            if (role.getName().equals("ROLE_ADMIN")) {
                tokenIsAdmin = true;
            }
        }
        return (id.equals(tokenId) || tokenIsAdmin);
    }

    @Override
    public UserEntity getUserEntityByToken(String token) {
        token = token.replace("Bearer ", "");
        String email = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the searched user does not exist"));
        return userEntity;
    }

}