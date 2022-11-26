package com.nocountry.wallet.models.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class TransCreateDTO {

    @Min(value= 0)
    Double amount;
    @Size(max=30, message = "The description is too long, write one with 30 chars length")
    String description;

    @NotNull
    Integer account_id;
}
