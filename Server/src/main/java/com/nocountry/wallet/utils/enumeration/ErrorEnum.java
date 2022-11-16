package com.nocountry.wallet.utils.enumeration;

/**
 * Whit this Enum we improve send message to exceptions, and not hardcode.
 */
public enum ErrorEnum {

    TRANSACTION_LIMIT("You have exceeded the transaction limit"),
    REQUEST_FAILED("The request was not completed"),
    INSUFFICIENT_BALANCE("You need more balance for this payment"),
    OBJECT_NOT_FOUND("The requested object was not found"),
    USER_ALREADY_EXIST("User already exist in Database"),
    EMPTY_PASS("The password is empty"),
    INVALID_PASSWORD("Password provided is a invalid password"),
    BAD_CREDENTIALS("Username or password is invalid");

    private String errorMessage;

    ErrorEnum(String message){
        this.errorMessage= message;
    }

    public String getMessage(){return errorMessage;}


}