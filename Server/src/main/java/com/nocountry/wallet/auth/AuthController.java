package com.nocountry.wallet.auth;


import com.nocountry.wallet.exception.BadRequestException;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserRegisterDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserRegisterDTO> register(@RequestBody UserCreateDTO userDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login( @RequestBody AuthRequestDTO authRequest)throws BadRequestException {

        return ResponseEntity.ok(userService.userAuth(authRequest));
    }
}