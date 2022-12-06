package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    Long id;
    String email;
    String photo;
    String dni;
    String firstName;
    String lastName;
    String creationDate;
    LocalDate birthDate;
    String jwt;
    String otp;

}
