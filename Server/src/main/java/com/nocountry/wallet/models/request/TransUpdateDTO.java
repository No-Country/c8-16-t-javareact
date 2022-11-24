package com.nocountry.wallet.models.request;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class TransUpdateDTO {
    @Size(max=30, message = "The description is too long, write one with 30 chars length")
    String description;
}
