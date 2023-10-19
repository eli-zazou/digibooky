package com.switchfully.www.domain;

public class DecodedCredentials {
    private final String email;
    private final String password;

    public DecodedCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
