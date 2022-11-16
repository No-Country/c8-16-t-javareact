package com.nocountry.wallet.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
public class UserCreateDTO {
    @Max(value= 25)
    private String firstName;
    @Max(value=25)
    private String lastName;
    @Email(message = "Username must be an email")
    private String email;
    @Size(min=8)
    private String password;
}
