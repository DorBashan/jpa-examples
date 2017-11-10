package pl.mjedynak.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS")
public class Address
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSE_NUMBER")
    private int houseNumber;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Address address = (Address) o;

        return houseNumber == address.houseNumber && id.equals(address.id) && (city != null ? city.equals(address.city)
                : address.city == null) && (street != null ? street.equals(address.street) : address.street == null);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, city, street, houseNumber);
    }
}
