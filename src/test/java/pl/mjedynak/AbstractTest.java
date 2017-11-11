package pl.mjedynak;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractTest
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    protected DaoJpa daoJpa;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
        daoJpa = new DaoJpa(entityManager);
    }

    @After
    public void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
