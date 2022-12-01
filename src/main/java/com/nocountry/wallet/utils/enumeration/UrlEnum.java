package com.nocountry.wallet.utils.enumeration;

public enum UrlEnum {

    MAIN_PATH("http://localhost:8080/");

    private String path;

    UrlEnum(String path){this.path = path;}
    public String getPathString(){return path;}

}
