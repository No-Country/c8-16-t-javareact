package com.nocountry.wallet.service;

import com.nocountry.wallet.models.request.TransUpdateDTO;

public interface ITransactionService {

    void updateTransaction(TransUpdateDTO transDTO, Long id, Long user_id);
}
