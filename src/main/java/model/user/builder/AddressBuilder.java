package model.user.builder;

import model.user.Address;

import java.util.function.Consumer;

public class AddressBuilder {
    public String streetAddress;
    public String number;
    public String postalCode;
    public String city;
    public String country;

    public AddressBuilder with(Consumer<AddressBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Address createAddress() {
        return new Address(streetAddress, number, postalCode, city, country);
    }
}
