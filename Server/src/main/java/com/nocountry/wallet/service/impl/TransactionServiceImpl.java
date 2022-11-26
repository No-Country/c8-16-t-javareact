package com.nocountry.wallet.service.impl;


import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.mapper.TransactionMapper;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.request.TransPageDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.repository.AccountRepository;
import com.nocountry.wallet.repository.TransactionRepository;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import com.nocountry.wallet.utils.enumeration.UrlEnum;
import com.nocountry.wallet.utils.transaction.Transaction;
import com.nocountry.wallet.utils.transaction.TransactionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        checkTransLimit(account,transDTO.getAmount());
        log.info("Updating account balance");
        transType.updateBalance(account, transDTO.getAmount());
        log.info("Create Transaction entity");
        TransactionEntity newTrans = transactionMapper.convert2Entity(transDTO);
        log.info("Persist Transaction");
        newTrans.setType(type);
        transactionRepository.save(newTrans);
    }
    @Override
    public TransPageDTO findAllByUserId(Long userId, Integer page) {

        log.info("Retrieve all account by user");
        List<AccountEntity> accounts = accountRepository.findAccountsByUserId(userId);
        log.info("Retrieve accounts ids");
        List<Integer> accIds = new ArrayList<>();
        accounts.forEach((AccountEntity acc) -> accIds.add(acc.getId()));
        log.info("Pagination for transactions by account by user");
        Pageable pageWithTenElements = PageRequest.of(page - 1, 10);
        Page<TransactionEntity> transPage = transactionRepository.findByAccountIds(accIds, pageWithTenElements);
        List<TransactionEntity> transList = transPage.getContent();

        log.info("Creating DTO");
        TransPageDTO transPageDTO = new TransPageDTO();
        int totalPages = transPage.getTotalPages();
        transPageDTO.setTotalPages(totalPages);
        log.info("Check number of page, if greater than 0 and less than total page");
        if (page > transPage.getTotalPages() || page <= 0) {
            throw new TransactionException(ErrorEnum.PAGE_ERROR.getMessage());
        }
        log.info("Creating URL to show");
        StringBuilder url = new StringBuilder(
                UrlEnum.MAIN_PATH.getPathString()+ "transactions/" + userId.toString() + "?page=");
        log.info("Setting Next page and Previous page values");
        transPageDTO.setNextPage(totalPages == page ? "Not have next page" : url + String.valueOf(page + 1));
        transPageDTO.setPreviusPage(page == 1 ? "Not have previus page" : url + String.valueOf(page - 1));
        log.info("Setting list of transactions to show in page DTO");
        transPageDTO.setTransDTOList(transactionMapper.transListEntity2ListDTO(transList));

        return transPageDTO;
    }
    private void checkTransLimit(AccountEntity account, Double amount){
        Double limit = account.getTransactionLimit();
        log.info("Check transaction limit");
        if (amount > limit){
            throw new TransactionException(ErrorEnum.TRANSACTION_LIMIT.getMessage());
        }
    }
}

