package com.nocountry.wallet.auth;


import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO userDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login( @RequestBody AuthRequestDTO authRequest)throws BadRequestException {
        String jwt = userService.userAuth(authRequest);
        return ResponseEntity.ok(new AuthResponseDTO(authRequest.getEmail(),jwt));
    }
}