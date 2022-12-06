package com.nocountry.wallet.auth;


import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateRequest;
import com.nocountry.wallet.models.request.UserVerifyRequest;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.models.response.UserVerifyResponse;
import com.nocountry.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

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

    @PostMapping("/verify")
    public ResponseEntity<UserVerifyResponse> verify(@PathVariable Long id, @Valid @RequestBody UserVerifyRequest userVerifyRequest, @RequestHeader(name = "Authorization") String token) throws BadRequestException, IOException {

        //return ResponseEntity.status(HttpStatus.CREATED).body(userService.verify());
        return  userService.verify(id, userVerifyRequest, token)
    }
}