package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserResponseDTO {
    String email;
    String firstName;
    String timestamp;
    String jwt;
}
