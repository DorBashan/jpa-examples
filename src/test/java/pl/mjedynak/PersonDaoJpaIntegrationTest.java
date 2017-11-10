package pl.mjedynak;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.mjedynak.model.Address;
import pl.mjedynak.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonDaoJpaIntegrationTest {

    private PersonDaoJpa personDaoJpa;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
        personDaoJpa = new PersonDaoJpa(entityManager);
    }

    @After
    public void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
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

    @Test
    public void insert() {
        System.out.println("==INSERT==");

        Person person = new Person();
        person.setName("Dor");
        person.setAge(24);

        personDaoJpa.addPerson(person);

        List<Person> result = personDaoJpa.findAll();

        System.out.println(result.get(0));
    }

    @Test
    public void delete() {
        System.out.println("==DELETE==");

        // insert
        Person person = new Person();
        person.setName("Dor");
        person.setAge(24);
        personDaoJpa.addPerson(person);
        System.out.println("Added: " + person);

        // delete
        personDaoJpa.deletePerson(personDaoJpa.findAll().get(0));
        System.out.println("Deleted: " + person);

        // print if worked
        System.out.println("Deleted " + (personDaoJpa.findAll().isEmpty() ? "Successfully" : "Failed"));
    }
}
