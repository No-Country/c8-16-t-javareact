package com.nocountry.wallet.service;

import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.utils.enumeration.TypeTransaction;

public interface ITransactionService {

    void updateTransaction(TransUpdateDTO transDTO, Long id, Long user_id);

    void makeTransaction(TransCreateDTO transDTO, TypeTransaction type);
}
