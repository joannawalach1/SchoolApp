package com.devs.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, Long> {
    T save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

    T update(Long id);

    boolean deleteById(Long id);
}
