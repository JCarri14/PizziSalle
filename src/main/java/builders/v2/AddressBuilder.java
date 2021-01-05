package builders.v2;

import model.user.Address;

import java.util.function.Consumer;

public class AddressBuilder {
    public String streetName;
    public String number;
    public String postalCode;
    public String city;
    public String country;

    public AddressBuilder with(Consumer<AddressBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Address createAddress() {
        return new Address(streetName, number, postalCode, city, country);
    }
}