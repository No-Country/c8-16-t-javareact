package com.nocountry.wallet.service.impl;


import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.repository.TransactionRepository;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository transactionRepository;
    @Override
    public void updateTransaction(TransUpdateDTO transDTO, Long id, Long user_id) {
        Optional<TransactionEntity> optTrans = transactionRepository.findById(id);
        if (optTrans.isEmpty()) {
            throw new TransactionException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());
        }
        Long dbUserId = optTrans.get().getAccount().getUser().getId();
        if (!dbUserId.equals(user_id)) {
            throw new TransactionException(ErrorEnum.NOT_MATCHING_IDS.getMessage());
        }
        String newDescription = transDTO.getDescription();
        transactionRepository.updateDescription(id, newDescription);
    }
}
