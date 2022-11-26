package com.nocountry.wallet.controller;


import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.request.TransDetailDTO;
import com.nocountry.wallet.models.request.TransPageDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.service.IAccountService;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.GetTokenData;
import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    private final IAccountService accountService;


    @PatchMapping("/{id}")
    ResponseEntity<?> updateTransaction(@PathVariable Long id,
                                        @RequestHeader("Authorization") String bearerToken,
                                        @Valid @RequestBody TransUpdateDTO transactionUpdateDTO)
            throws ParseException {
        //Extract token
        String token = bearerToken.substring("Bearer ".length());
        //Call static method
        Long user_id = GetTokenData.getUserIdFromToken(token);
        transactionService.updateTransaction(transactionUpdateDTO, id, user_id);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/{page}")
    ResponseEntity<TransPageDTO> findAllMyTransactions(@PathVariable Integer page,
                                                       @RequestHeader("Authorization") String bearerToken)
            throws ParseException {
        //Extract token
        String token = bearerToken.substring("Bearer ".length());
        //Call static method
        Long user_id = GetTokenData.getUserIdFromToken(token);
        return ResponseEntity.ok().body(transactionService.findAllByUserId(user_id, page));

    }

    @PostMapping("/deposit")
    ResponseEntity<?> makeDeposit(@RequestBody @Valid TransCreateDTO transDTO) {

        transactionService.makeTransaction(transDTO, TypeTransaction.DEPOSIT);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}