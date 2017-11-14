package pl.mjedynak;

import org.junit.Before;
import org.junit.Test;
import pl.mjedynak.model.Event;
import pl.mjedynak.model.Hobby;
import pl.mjedynak.model.Person;

import java.util.GregorianCalendar;
import java.util.List;

public class HQLTest extends AbstractTest
{
    @Before
    public void setUp() {
        super.setUp();

        daoJpa.insert(createDor());
        daoJpa.insert(createMoshe());
        daoJpa.insert(createTomer());
    }

    private Person createTomer()
    {
        Person tomer = new Person();
        tomer.setName("Tomer");
        tomer.setAge(23);
        tomer.getHobbies().add(new Hobby("Parties"));
        tomer.getHobbies().add(new Hobby("Math"));

        Event tomerBirthday = new Event();
        tomerBirthday.setDate(new GregorianCalendar(2001, 7, 9).getTime());
        tomerBirthday.setName("Birthday");
        tomerBirthday.setPerson(tomer);
        tomer.getEvents().add(tomerBirthday);

        Event tomerGraduation = new Event();
        tomerGraduation.setDate(new GregorianCalendar(1994, 4, 8).getTime());
        tomerGraduation.setName("Graduation");
        tomerGraduation.setPerson(tomer);
        tomer.getEvents().add(tomerGraduation);

        return tomer;
    }

    private Person createMoshe()
    {
        Person moshe = new Person();
        moshe.setName("Moshe");
        moshe.setAge(17);
        moshe.getHobbies().add(new Hobby("Soccer"));
        moshe.getHobbies().add(new Hobby("Basketball"));

        Event mosheBirthday = new Event();
        mosheBirthday.setDate(new GregorianCalendar(2001, 7, 9).getTime());
        mosheBirthday.setName("Birthday");
        mosheBirthday.setPerson(moshe);
        moshe.getEvents().add(mosheBirthday);

        Event mosheBarMitza = new Event();
        mosheBarMitza.setDate(new GregorianCalendar(1997, 7, 9).getTime());
        mosheBarMitza.setName("Bar Mitza");
        mosheBarMitza.setPerson(moshe);
        moshe.getEvents().add(mosheBarMitza);

        return moshe;
    }

    private Person createDor()
    {
        Person dor = new Person();
        dor.setName("Dor");
        dor.setAge(24);
        dor.getHobbies().add(new Hobby("Soccer"));
        dor.getHobbies().add(new Hobby("Movies"));

        Event dorBirthday = new Event();
        dorBirthday.setDate(new GregorianCalendar(1993, 1, 5).getTime());
        dorBirthday.setName("Birthday");
        dorBirthday.setPerson(dor);
        dor.getEvents().add(dorBirthday);

        Event dorGraduation = new Event();
        dorBirthday.setDate(new GregorianCalendar(2018, 10, 10).getTime());
        dorBirthday.setName("Graduation");
        dorGraduation.setPerson(dor);
        dor.getEvents().add(dorGraduation);

        return dor;
    }

    @Test
    public void getAllPersonsWithSoccerHobby() {
        System.out.println("==PERSONS WITH SOCCER==");
        List<Person> persons = daoJpa.getEntityManager().createQuery(
                "SELECT p FROM Person p join p.hobbies hobby " +
                    "WHERE hobby.name = :hobbyName", Person.class)
                .setParameter("hobbyName", "Soccer").getResultList();

        persons.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    public void getAllPersonsAbove20WithSoccerHobby() {
        System.out.println("==PERSONS WITH SOCCER ABOVE 20==");
        List<Person> persons = daoJpa.getEntityManager().createQuery(
                "SELECT p FROM Person p join p.hobbies hobby " +
                    "WHERE hobby.name = :hobbyName AND p.age > :age", Person.class)
                .setParameter("hobbyName", "Soccer")
                .setParameter("age", 20).getResultList();

        persons.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    public void getAllPersonsWhoLikeMathWithBirthDayEvent() {
        System.out.println("==PERSONS MATH AND EVENT BIRTHDAY==");
        List<Person> persons = daoJpa.getEntityManager().createQuery(
                "SELECT p FROM Person p join p.hobbies hobby join p.events event " +
                    "WHERE hobby.name = :hobbyName AND event.name = :eventName", Person.class)
                .setParameter("hobbyName", "Math")
                .setParameter("eventName", "Birthday").getResultList();

        persons.forEach(p-> System.out.println(p.getName()));
    }
}
