package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateResponse {
    private String firstName;
    private String lastName;
    private String dni;
    private String password;
    private String photo;
}
