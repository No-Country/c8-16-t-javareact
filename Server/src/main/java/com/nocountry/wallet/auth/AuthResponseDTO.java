package com.nocountry.wallet.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class AuthResponseDTO {


    String email;
    String jwt;


}
