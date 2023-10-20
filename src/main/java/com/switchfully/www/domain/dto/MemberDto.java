package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Address;

public class MemberDto {
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private Address address;

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public MemberDto setId(String id) {
        this.id = id;
        return this;
    }

    public MemberDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public MemberDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public MemberDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public MemberDto setAddress(Address address) {
        this.address = address;
        return this;
    }
}
