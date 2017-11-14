package pl.mjedynak;

import org.junit.Test;
import pl.mjedynak.model.Address;
import pl.mjedynak.model.Event;
import pl.mjedynak.model.Person;

import java.util.GregorianCalendar;
import java.util.List;

public class CascadeTest extends AbstractTest
{
    @Test
    public void persistCascadeTest() {
        System.out.println("==PERSIST CASCADE==");

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

    @Test
    public void removeCascadeTest() {
        System.out.println("==REMOVE CASCADE==");

        Person person = new Person();
        person.setAge(24);
        person.setName("Dor");

        Event event = new Event();
        event.setName("Birthday");
        event.setDate(new GregorianCalendar(1993, 1, 5).getTime());
        event.setPerson(person);

        person.getEvents().add(event);

        daoJpa.insert(person);
        System.out.println("Person Saved: " + daoJpa.findAll(Person.class));
        System.out.println("Event Saved: " + daoJpa.findAll(Event.class));

        daoJpa.delete(person);
        System.out.println("Cascade: Person and Event are gone: " +
                ((daoJpa.findAll(Person.class).isEmpty() && daoJpa.findAll(Event.class).isEmpty())
                        ? "Successfully" : "Failed"));
    }
}
