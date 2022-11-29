package com.nocountry.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    /*
    @Autowired(required = true)
    private IAccountService accountService;


    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(required = false) String currency) throws Exception {

        AccountDTO accountDTO = accountService.createAccount(token, currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDTO);
    }
*/

}