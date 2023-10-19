package com.switchfully.www.domain;

import java.util.Objects;
import java.util.UUID;

public class Author {

    private final String id;
    private String firstName;
    private String lastName;

    public Author(String firstname, String lastname) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstname;
        setLastname(lastname);
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstName;
    }


    public String getLastname() {
        return lastName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public void setLastname(String lastname) {
        if(lastname==null) {
            throw new IllegalArgumentException("Please provide a lastname for the author");
        }
        this.lastName = lastname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
