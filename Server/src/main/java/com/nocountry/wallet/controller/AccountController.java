package com.nocountry.wallet.controller;

import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(required = false) String currency) throws Exception {

        AccountDTO accountDTO = accountService.createAccount(token, currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }


}