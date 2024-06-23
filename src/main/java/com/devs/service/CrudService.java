package com.devs.service;

import com.devs.domain.Exam;
import com.devs.domain.Student;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, Long> {
    T save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

    T update(Long id);

    boolean deleteById(Long id);
}
