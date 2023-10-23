package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.member.Address;
import com.switchfully.www.domain.security.UserRole;

public class CreateMemberDTO {

    private String inss;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Address address;
    private UserRole role;

    public CreateMemberDTO() {
    }

    public String getInss() {
        return inss;
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

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public UserRole getRole() {
        return role;
    }

    public CreateMemberDTO setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public CreateMemberDTO setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public CreateMemberDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateMemberDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateMemberDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateMemberDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateMemberDTO setAddress(Address address) {
        this.address = address;
        return this;
    }
}
