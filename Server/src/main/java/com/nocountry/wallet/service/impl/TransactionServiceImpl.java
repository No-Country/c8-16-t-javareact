package com.nocountry.wallet.service.impl;


import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.mapper.TransactionMapper;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.response.TransDetailDTO;
import com.nocountry.wallet.models.response.TransPageDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.repository.AccountRepository;
import com.nocountry.wallet.repository.TransactionRepository;
import com.nocountry.wallet.service.IAccountService;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.enumeration.CurrencyEnum;
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
    private final IAccountService accountService;
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

    public TransactionEntity makeTransaction(TransCreateDTO transDTO, Long user_id) {

        log.info("Checking if account exist");
        AccountEntity account = accountRepository.findById(transDTO.getAccount_id()).orElseThrow(
                () -> new TransactionException(ErrorEnum.OBJECT_NOT_FOUND.getMessage()));
        log.info("Create transaction object");
        Transaction transType = TransactionFactory.createTransaction(transDTO.getType());

        checkTransLimit(account,transDTO.getAmount());
        log.info("Check if transaction type is send for create income transaction");
        if(transDTO.getType() == TypeTransaction.SEND){

            transDTO.setType(TypeTransaction.INCOME);
            log.info("Create new transaction with INCOME type");
            this.makeTransaction(transDTO, user_id);//Here user_id value not matter. UserId only use when is a payment transaction
            log.info("Set dto parameters for create SEND transaction");
            account = accountService.findByUserByCurrency(user_id, transDTO.getCurrency());
            transDTO.setAccount_id(account.getId());
            transDTO.setType(TypeTransaction.SEND);
        }
        log.info("Updating account balance");
        transType.updateBalance(account, transDTO.getAmount());
        log.info("Create Transaction entity");
        TransactionEntity newTrans = transactionMapper.convert2Entity(transDTO);

        log.info("Check if transaction type is payment for cashback logic");
        if(transDTO.getType() == TypeTransaction.PAYMENT){
            AccountEntity accountCashback = accountRepository.findByUserByCurrency(user_id, CurrencyEnum.BTC).orElseThrow(
                    () -> new TransactionException(ErrorEnum.OBJECT_NOT_FOUND.getMessage()));
            log.info("Update account cashback with add cashback amount");
            cashback(accountCashback, transDTO.getAmount());
        }
        
        log.info("Persist Transaction");
        transactionRepository.save(newTrans);

        return newTrans;
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

    @Override
    public TransDetailDTO findOne(Long id, Long userId) {
        log.info("Find transaction by ID");
        Optional<TransactionEntity> optTrans = transactionRepository.findById(id);
        if (optTrans.isEmpty()) {
            throw new TransactionException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());
        }
        log.info("Check if user logged is the same user make transaction");
        Long dbUserId = optTrans.get().getAccount().getUser().getId();
        if (!dbUserId.equals(userId)) {
            throw new TransactionException(ErrorEnum.NOT_MATCHING_IDS.getMessage());
        }
        log.info("Get transaction Entity from Optional");
        TransactionEntity entity = optTrans.get();
        log.info("Mapping to DTO");
        TransDetailDTO dto = transactionMapper.convert2DetailDTO(entity);
        log.info("Return DTO");
        return dto;
    }

    private void checkTransLimit(AccountEntity account, Double amount){
        Double limit = account.getTransactionLimit();
        log.info("Check transaction limit");
        if (amount > limit){
            throw new TransactionException(ErrorEnum.TRANSACTION_LIMIT.getMessage());
        }
    }
    private enum CashbackAmount{
        CASHBACK_AMOUNT(0.05);
        private Double amount;
        CashbackAmount(Double percent){
            this.amount= percent;
        }
        public Double getPercent(){return amount;}
    }
    private void cashback(AccountEntity account, Double amount){
        account.setBalance(account.getBalance()+ amount*CashbackAmount.CASHBACK_AMOUNT.getPercent());
    }
}

