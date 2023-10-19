package com.switchfully.www.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class Member {
    private String id;
    private String inss;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Address address;
    private UserRole role;
    private LocalDateTime dateCreated;
    private LocalDateTime dateDeleted;
    private LocalDateTime dateUpdated;

    public Member(String inss, String lastName, String firstName, String email, String password, Address address, UserRole role)  throws IllegalArgumentException {
        this.id = UUID.randomUUID().toString();
        this.inss = inss;
        this.lastName = lastName;
        this.firstName = firstName;
        setEmail(email);
        this.password = password;
        this.address = address;
        this.role = role;
        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = null;
        this.dateDeleted = null;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setInss(String inss) {
        this.inss = inss;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if(!isValidEmail(email)){
            throw new IllegalArgumentException("Please provide a valid e-mail address");
        }
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private boolean isValidEmail(String email){
      String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailRegexPattern)
                .matcher(email)
                .matches();
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(LocalDateTime dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }



}
