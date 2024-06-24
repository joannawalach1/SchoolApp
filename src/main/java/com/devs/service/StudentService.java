package com.devs.service;

import com.devs.domain.Student;
import com.devs.domain.dto.StudentDto;
import com.devs.exceptions.StudentNotFoundException;
import com.devs.mappers.StudentMapper;
import com.devs.repository.StudentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentDto save(StudentDto studentDto) {
        Student student = StudentMapper.toEntity(studentDto);
        Student savedStudent = studentRepo.save(student);
        return StudentMapper.toDto(student);
    }

    public Optional<StudentDto> findById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return Optional.ofNullable(StudentMapper.toDto(student));
    }

    public Optional<StudentDto> findByEmail(String email) {
        Student student = studentRepo.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return Optional.ofNullable(StudentMapper.toDto(student));
    }

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Transactional
    public Optional<StudentDto> update(String email, String newName) {
       Student studentToUpdate = studentRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
       studentToUpdate.setName(newName);
       Student updatedStudent = studentRepo.save(studentToUpdate);
       return Optional.ofNullable(StudentMapper.toDto(updatedStudent));

    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Student> studentToDelete = Optional.ofNullable(studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
            studentRepo.deleteById(id);
    }
}