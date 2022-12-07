package com.nocountry.wallet.service;

import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.response.TransDetailDTO;
import com.nocountry.wallet.models.response.TransPageDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;

public interface ITransactionService {

    void updateTransaction(TransUpdateDTO transDTO, Long id, Long user_id);

    TransactionEntity makeTransaction(TransCreateDTO transDTO, Long user_id);

    TransPageDTO findAllByUserId(Long userId, Integer page);
    TransDetailDTO findOne(Long id, Long userId);
}
