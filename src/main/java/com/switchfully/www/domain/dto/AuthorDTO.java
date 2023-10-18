package com.switchfully.www.domain.dto;

public class AuthorDTO {
    private String firstName;
    private String lastName;

    public AuthorDTO(){}

    public AuthorDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

