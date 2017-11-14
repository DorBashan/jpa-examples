package pl.mjedynak.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTS")
public class Event
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person person;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "NAME")
    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    @Override
    public String toString()
    {
        return "Event{" +
                "id=" + id +
                ", person=" + person.getName() +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
