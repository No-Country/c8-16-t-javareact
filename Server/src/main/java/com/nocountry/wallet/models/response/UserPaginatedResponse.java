package com.nocountry.wallet.models.response;

import lombok.Data;

import java.util.List;

@Data
public class UserPaginatedResponse {
    List<UserResponseDTO> userPageContent;
    String nextPage;
    String previousPage;
}
