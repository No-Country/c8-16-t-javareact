package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String email;
    String photo;
    String dni;
    String firstName;
    String lastName;
    String creationDate;
    LocalDate birthDate;
    String jwt;
    Boolean verify;
}
