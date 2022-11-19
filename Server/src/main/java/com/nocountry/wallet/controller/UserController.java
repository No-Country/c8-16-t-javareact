package com.nocountry.wallet.controller;

import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/detail/{id}")
    //@PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Long id){

        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
        return userService.deleteUser(id, token);
    }

    @GetMapping()
    public ResponseEntity<List<UserDetailDTO>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
