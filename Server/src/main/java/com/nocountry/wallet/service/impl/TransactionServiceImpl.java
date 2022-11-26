package com.nocountry.wallet.service.impl;


import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.mapper.TransactionMapper;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.repository.AccountRepository;
import com.nocountry.wallet.repository.TransactionRepository;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import com.nocountry.wallet.utils.transaction.Transaction;
import com.nocountry.wallet.utils.transaction.TransactionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@RequiredArgsConstructor@Slf4j
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
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

    public void makeTransaction(TransCreateDTO transDTO, TypeTransaction type) {

        log.info("Checking if account exist");
        AccountEntity account = accountRepository.findById(transDTO.getAccount_id()).orElseThrow(
                () -> new TransactionException(ErrorEnum.OBJECT_NOT_FOUND.getMessage()));
        log.info("Create transaction object");
        Transaction transType = TransactionFactory.createTransaction(type);
        log.info("Check transaction limit");
        checkTransLimit(account,transDTO.getAmount());
        log.info("Updating account balance");
        transType.updateBalance(account, transDTO.getAmount());
        log.info("Create Transaction entity");
        TransactionEntity newTrans = transactionMapper.convert2Entity(transDTO);
        log.info("Persist Transaction");
        newTrans.setType(type);
        transactionRepository.save(newTrans);
    }
    private void checkTransLimit(AccountEntity account, Double amount){
        Double limit = account.getTransactionLimit();
        if (amount > limit){
            throw new TransactionException(ErrorEnum.TRANSACTION_LIMIT.getMessage());
        }
    }
}

