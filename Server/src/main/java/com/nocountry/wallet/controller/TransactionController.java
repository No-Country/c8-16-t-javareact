package com.nocountry.wallet.controller;


import com.nocountry.wallet.models.request.TransCreateDTO;
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

    @PostMapping("/deposit")
    ResponseEntity<?> makeDeposit(@RequestBody @Valid TransCreateDTO transDTO) {

        transactionService.makeTransaction(transDTO, TypeTransaction.DEPOSIT);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}