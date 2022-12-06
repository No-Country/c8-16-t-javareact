package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserVerifyResponse {

    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private Boolean verify;
}
