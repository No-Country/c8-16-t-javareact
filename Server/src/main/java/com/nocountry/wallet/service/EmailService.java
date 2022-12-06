package com.nocountry.wallet.service;

public interface EmailService {
    void sendRegisterMail(String email);
    void sendContactMail(String email);
    String getOtpCode();
}
