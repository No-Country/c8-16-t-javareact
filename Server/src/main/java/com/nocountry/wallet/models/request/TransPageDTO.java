package com.nocountry.wallet.models.request;

import lombok.Data;

import java.util.List;

@Data
public class TransPageDTO {


    String nextPage;
    String previusPage;
    int totalPages;
    List<TransDetailDTO> transDTOList;
}