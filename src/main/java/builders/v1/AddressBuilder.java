package builders.v1;

import model.user.Address;

public class AddressBuilder {
    private int id;
    private String streetAddress;
    private String number;
    private String postalCode;
    private String city;
    private String country;

    public AddressBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AddressBuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public AddressBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Address createAddress() {
        return new Address(id, streetAddress, number, postalCode, city, country);
    }

}
