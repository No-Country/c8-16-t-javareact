package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.response.TransDetailDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component@AllArgsConstructor
public class TransactionMapper {

    private final ModelMapper mapper;


    public TransactionEntity convert2Entity(TransCreateDTO dto) {
        return mapper.map(dto, TransactionEntity.class);
    }

    public TransDetailDTO convert2DetailDTO(TransactionEntity entity){return mapper.map(entity, TransDetailDTO.class);}
    public List<TransDetailDTO> transListEntity2ListDTO(List<TransactionEntity> transList) {
        List<TransDetailDTO> result = new ArrayList<>();
        transList.forEach(entity -> result.add(convert2DetailDTO(entity)));
        return result;
    }
}
