package com.nocountry.wallet.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.auth.AuthResponseDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;

public interface IAuthService {

    UserResponseDTO saveUser(UserCreateDTO dto);
    AuthResponseDTO login(AuthRequestDTO authRequest);
}
