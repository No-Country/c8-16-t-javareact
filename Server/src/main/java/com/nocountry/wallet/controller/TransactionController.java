package com.nocountry.wallet.controller;


import com.nocountry.wallet.models.entity.TransactionEntity;
import com.nocountry.wallet.models.request.TransCreateDTO;
import com.nocountry.wallet.models.response.TransDetailDTO;
import com.nocountry.wallet.models.response.TransPageDTO;
import com.nocountry.wallet.models.request.TransUpdateDTO;
import com.nocountry.wallet.models.response.TransSimpleDTO;
import com.nocountry.wallet.service.IAccountService;
import com.nocountry.wallet.service.ITransactionService;
import com.nocountry.wallet.utils.GetTokenData;
import com.nocountry.wallet.utils.enumeration.UrlEnum;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    @GetMapping("/detail/{id}")
    ResponseEntity<TransDetailDTO> getTransactionDetail(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String bearerToken)
            throws ParseException {
        //Extract token
        String token = bearerToken.substring("Bearer ".length());
        //Call static method
        Long user_id = GetTokenData.getUserIdFromToken(token);
        return ResponseEntity.ok().body(transactionService.findOne(id, user_id));

    }

    @PostMapping("/create")
    ResponseEntity<TransSimpleDTO> createTransaction(@RequestBody @Valid TransCreateDTO transDTO,
                                                     @RequestHeader("Authorization") String bearerToken)
            throws ParseException {
        //Extract token
        String token = bearerToken.substring("Bearer ".length());
        //Call static method
        Long user_id = GetTokenData.getUserIdFromToken(token);

        TransactionEntity trans = transactionService.makeTransaction(transDTO, user_id);
        URI uri = UriComponentsBuilder.fromHttpUrl(UrlEnum.MAIN_PATH.getPathString()).path("/{id}").build(trans.getId());
        return ResponseEntity.created(uri).build();

    }
    //exchange
}