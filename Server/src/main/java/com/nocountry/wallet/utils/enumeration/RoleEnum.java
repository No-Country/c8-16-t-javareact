package com.nocountry.wallet.utils.enumeration;

public enum RoleEnum {
    USER, ADMIN;
    private static final String PREFIX = "ROLE_";

    public String getFullRoleName() {
        return PREFIX + this.name();
    }

    public String getSimpleRoleName() {
        return this.name();
    }
}