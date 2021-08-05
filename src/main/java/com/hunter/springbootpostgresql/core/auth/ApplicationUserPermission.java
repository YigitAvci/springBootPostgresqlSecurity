package com.hunter.springbootpostgresql.core.auth;

public enum ApplicationUserPermission {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
