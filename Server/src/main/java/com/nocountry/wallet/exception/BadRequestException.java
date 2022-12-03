package com.nocountry.wallet.exception;
public class BadRequestException extends RuntimeException{
    public BadRequestException(String error){super(error);}
}