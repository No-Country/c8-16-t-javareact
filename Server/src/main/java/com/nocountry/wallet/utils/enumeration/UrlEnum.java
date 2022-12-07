package com.nocountry.wallet.utils.enumeration;

public enum UrlEnum {

    MAIN_PATH("https://flux-app.up.railway.app");

    private String path;

    UrlEnum(String path){this.path = path;}
    public String getPathString(){return path;}

}
