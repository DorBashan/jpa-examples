package pl.mjedynak;

import org.junit.Test;
import pl.mjedynak.model.Address;
import pl.mjedynak.model.Person;

import java.util.List;

public class CascadeTest extends AbstractTest
{
    @Test
    public void persistCascadeManyToOneTest() {
        System.out.println("==PERSIST CASCADE MANY TO ONE==");

        Address address = new Address();
        address.setCity("Tel-Aviv");
        address.setStreet("Dizingof");
        address.setHouseNumber(100);

        Person dor = new Person();
        dor.setAge(24);
        dor.setName("Dor");
        dor.setAddress(address);

        Person moshe = new Person();
        moshe.setAge(17);
        moshe.setName("Moshe");
        moshe.setAddress(address);

        daoJpa.insert(dor);
        daoJpa.insert(moshe);

        List<Person> result = daoJpa.findAll(Person.class);

        System.out.println("Many to One: Two persons have the same address");
        System.out.println("Cascade: Address was saved automatically when we saved person with this address");
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}
