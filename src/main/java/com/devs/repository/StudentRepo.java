package com.devs.repository;

import com.devs.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepo extends JpaRepository<Student, Long> {
}
