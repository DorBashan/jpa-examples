package pl.mjedynak;

import javax.persistence.EntityManager;
import java.util.List;

public class DaoJpa implements DaoInterface
{

    private EntityManager entityManager;

    public DaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz)
    {
        return entityManager.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    @Override
    public void insert(Object object)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Object object)
    {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Object object)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }
}
