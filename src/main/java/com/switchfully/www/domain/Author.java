package com.switchfully.www.domain;

import java.util.Objects;
import java.util.UUID;

public class Author {

    private String id;
    private String firstName;
    private String lastName;

    private Author() {
        this.id = UUID.randomUUID().toString();
    }


    public Author(String firstname, String lastname) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstname;
        setLastName(lastname);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        if (lastname == null) {
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
