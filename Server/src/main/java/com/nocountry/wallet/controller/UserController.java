package com.nocountry.wallet.controller;

import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateDTO;
import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.service.IUserService;
import com.nocountry.wallet.utils.GetTokenData;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/detail/{id}") //Only ADMIN access
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
    @GetMapping("/detail")
    public ResponseEntity<UserDetailDTO> getUserDetail(@RequestHeader(name= "Authorization") String bearerToken)
        throws ParseException {
        //Extract token
        String token = bearerToken.substring("Bearer ".length());
        //Call static method
        Long user_id = GetTokenData.getUserIdFromToken(token);
        return ResponseEntity.ok().body(userService.getUserById(user_id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCreateDTO> updateUser(@RequestBody UserUpdateDTO user, @PathVariable Integer id){
        UserCreateDTO userDTO =  userService.updateUser(user,id);
        return ResponseEntity.ok().body(userDTO);
    }
}
