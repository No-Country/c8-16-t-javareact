package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component@AllArgsConstructor
public class TransactionMapper {

    private final ModelMapper mapper;


    public TransactionEntity convert2Entity(TransCreateDTO dto) {
        return mapper.map(dto, TransactionEntity.class);
    }
}
