package com.switchfully.www.domain;

public class City {
    private String postalCode;
    private String cityName;

    public City(String postalCode, String cityName) {
        setPostalCode(postalCode);
        setCityName(cityName);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setPostalCode(String postalCode) {
        if(postalCode==null) {
            throw new IllegalArgumentException("Please provide a postal code for the member");
        }
        this.postalCode = postalCode;
    }

    public void setCityName(String cityName) {
        if(cityName==null) {
            throw new IllegalArgumentException("Please provide a name of the city for the member");
        }
        this.cityName = cityName;
    }
}
