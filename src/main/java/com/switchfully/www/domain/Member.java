package com.switchfully.www.domain;

import java.rmi.server.UID;
import java.util.UUID;

public class Member {
    private String id;
    private String inss;
    private String lastName;
    private String firstName;
    private String email;
    private String streetName;
    private String streetNumber;
    private City city;

    public Member(String inss, String lastName, String firstName, String email, String streetName, String streetNumber, City city) {
        this.id = UUID.randomUUID().toString();
        // todo validate INSS
        this.inss = inss;
        this.lastName = lastName;
        this.firstName = firstName;
        // todo validate email
        this.email = email;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
    }


}
