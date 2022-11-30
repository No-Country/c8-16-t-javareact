package com.nocountry.wallet.security.config.service;

public interface EmailService {
    void sendRegisterMail(String email);
    void sendContactMail(String email);

}
