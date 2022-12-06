package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String email;
    String photo;
    String dni;
    String firstName;
    String lastName;
    String timestamp;
    String jwt;
}
