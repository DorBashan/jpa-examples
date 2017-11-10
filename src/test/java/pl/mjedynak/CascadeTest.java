package pl.mjedynak;

import org.junit.Before;
import org.junit.Test;
import pl.mjedynak.model.Address;
import pl.mjedynak.model.Person;

import java.util.List;

public class CascadeTest extends AbstractTest
{
    private PersonDaoJpa personDaoJpa;

    @Before
    public void setUp() {
        super.setUp();
        personDaoJpa = new PersonDaoJpa(entityManager);
    }

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

        personDaoJpa.addPerson(dor);
        personDaoJpa.addPerson(moshe);

        List<Person> result = personDaoJpa.findAll();

        // Watch how two persons have address but we never saved it - the cascade save it for us.
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}
