package com.nocountry.wallet.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserResponseDTO {
    //Agregar el id para que lo devuelva. tambien en el login
    String email;
    String photo;
    String dni;
    String firstName;
    String timestamp;
    String jwt;
}
