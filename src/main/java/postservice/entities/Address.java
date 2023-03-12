package postservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address  {
    @Column
    private String additionalAddress;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String country;

    @Column
    private String region;
    @Column
    private Long zip;


    public Address() {}
    public Address(String address, String additionalAddress, String city, Long zip, String region, String country) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.address = address;
        this.additionalAddress = additionalAddress;
        this.region = region;
    }



    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Long getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return    address + " "
                + additionalAddress + " "
                + city  + " "
                + zip + " "
                + country;
    }


}
