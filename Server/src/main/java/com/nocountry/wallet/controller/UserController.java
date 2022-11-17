package com.nocountry.wallet.controller;

import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/detail/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){

        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
        return userService.deleteUser(id, token);
    }
}
