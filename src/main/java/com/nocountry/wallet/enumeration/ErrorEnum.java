package com.nocountry.wallet.enumeration;

/**
 * Whit this Enum we improve send message to exceptions, and not hardcode.
 */
public enum ErrorEnum {

    TRANSACTION_LIMIT("You have exceeded the transaction limit"),
    REQUEST_FAILED("The request was not completed"),
    INSUFFICIENT_BALANCE("You need more balance for this payment"),
    OBJECT_NOT_FOUND("The requested object was not found");

    private String errorMessage;

    ErrorEnum(String message){
        this.errorMessage= message;
    }

    public String getMessage(){return errorMessage;}


}