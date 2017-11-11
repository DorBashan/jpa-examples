package pl.mjedynak;

import org.junit.Test;
import pl.mjedynak.model.Person;

public class CRUDTest extends AbstractTest
{
    @Test
    public void insert() {
        System.out.println("==INSERT==");

        Person person = new Person();
        person.setName("Dor");
        person.setAge(24);

        daoJpa.insert(person);

        System.out.println(daoJpa.findOne(Person.class));
    }

    @Test
    public void delete() {
        System.out.println("==DELETE==");

        // insert
        Person person = new Person();
        person.setName("Dor");
        person.setAge(24);
        daoJpa.insert(person);
        System.out.println("Added: " + person);

        // delete
        daoJpa.delete(daoJpa.findOne(Person.class));
        System.out.println("Deleted: " + person);

        // print if worked
        System.out.println("Deleted " + (daoJpa.findAll(Person.class).isEmpty() ? "Successfully" : "Failed"));
    }

    @Test
    public void update() {
        System.out.println("==UPDATE==");

        // insert
        Person person = new Person();
        person.setName("Dor");
        person.setAge(24);
        daoJpa.insert(person);
        System.out.println("Added: " + person);

        // update
        Person dor = daoJpa.findOne(Person.class);
        dor.setAge(17);
        daoJpa.update(dor);

        System.out.println("Updated: " + daoJpa.findOne(Person.class));
    }
}
