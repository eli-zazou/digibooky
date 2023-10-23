package com.switchfully.www.domain.member;

public class Address {

    private String streetName;
    private String streetNumber;
    private City city;

    public Address(String streetName, String streetNumber, City city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        setCity(city);
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public City getCity() {
        return city;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("Please provide a city for the member");
        }
        this.city = city;
    }
}
