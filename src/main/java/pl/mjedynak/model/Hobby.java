package pl.mjedynak.model;

import javax.persistence.*;

@Entity
@Table(name = "HOBBY")
public class Hobby
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "HOBBY_NAME")
    private String name;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Hobby hobby = (Hobby) o;

        if (id != null ? !id.equals(hobby.id) : hobby.id != null)
            return false;
        return name != null ? name.equals(hobby.name) : hobby.name == null;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
