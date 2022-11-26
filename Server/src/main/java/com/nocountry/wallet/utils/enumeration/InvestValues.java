package com.nocountry.wallet.utils.enumeration;

public enum InvestValues {
    BTC(0.02),
    USDT(0.05),
    ARS(0.7),
    ETH(0.04);

    private Double currencyInterest;

    InvestValues(Double interest){
        this.currencyInterest= interest;
    }

    public Double getInterest(){return currencyInterest;}

}
