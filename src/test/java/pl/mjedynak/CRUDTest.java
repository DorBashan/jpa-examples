package pl.mjedynak;

import org.junit.Before;
import org.junit.Test;
import pl.mjedynak.model.Person;

import java.util.List;

public class CRUDTest extends AbstractTest
{
    private PersonDaoJpa personDaoJpa;

    @Before
    public void setUp() {
        super.setUp();
        personDaoJpa = new PersonDaoJpa(entityManager);
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
