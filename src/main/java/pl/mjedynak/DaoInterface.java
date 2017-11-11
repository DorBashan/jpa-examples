package pl.mjedynak;

import java.util.List;

public interface DaoInterface
{
    <T> List<T> findAll(Class<T> clazz);

    void insert(Object object);

    void update(Object object);

    void delete(Object object);
}
