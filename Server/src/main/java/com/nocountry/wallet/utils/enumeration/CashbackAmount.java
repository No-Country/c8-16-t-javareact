package com.nocountry.wallet.utils.enumeration;

public enum CashbackAmount{
    CASHBACK_AMOUNT(0.05);
    private Double amount;
    CashbackAmount(Double percent){
        this.amount= percent;
    }
    public Double getPercent(){return amount;}
}