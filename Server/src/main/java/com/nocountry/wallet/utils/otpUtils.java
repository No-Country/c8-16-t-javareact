package com.nocountry.wallet.utils;



import org.springframework.stereotype.Service;

@Service
public class otpUtils {

    public String otpStarter() {

        int codigo = (int) (Math.random() * (999999 - 100000)) + 100000;

        //System.out.println(codigo);

        String otp =  String.valueOf(codigo);

        //System.out.println("El codigo en string es: "+otp);

        return otp;
    }
}
