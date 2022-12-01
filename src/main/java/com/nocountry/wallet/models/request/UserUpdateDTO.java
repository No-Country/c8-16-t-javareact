package com.nocountry.wallet.models.request;

import lombok.Data;

@Data
public class UserUpdateDTO {
    Integer id;
    String firstName;
    String dni;
    String lastName;
    String password;
}
