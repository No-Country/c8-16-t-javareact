package com.nocountry.wallet.models.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class UserCreateDTO {
    @Max(value= 25)
    private String firstName;
    @Max(value=25)
    private String lastName;
    @Email(message = "Username must be an email")
    private String email;
    private String photo;
    @Max(value= 10)
    private String dni;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthDate;
    @Size(min=8)
    private String password;
}
