package org.linker.repository;

import java.util.List;

public interface Repository<T> {
    public T findById(Integer id);
    public List<T> findAll();
    public T save(T newInstance);
}