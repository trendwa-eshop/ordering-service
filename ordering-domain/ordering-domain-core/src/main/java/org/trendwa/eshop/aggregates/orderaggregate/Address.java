package org.trendwa.eshop.aggregates.orderaggregate;

import org.trendwa.eshop.seedwork.ValueObject;

import java.util.Collection;
import java.util.Collections;

public class Address extends ValueObject {

    private String street;
    private String houseNumber;
    private String state;
    private String city;
    private String zipCode;
    private String country;

    public Address(String street, String houseNumber, String state, String city, String zipCode, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
    }

    @Override
    protected Collection<Object> getEqualityComponents() {
        return Collections.emptyList();
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
