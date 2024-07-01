package com.devs.service;

import com.devs.domain.Student;
import com.devs.domain.dto.StudentDto;
import com.devs.exceptions.StudentNotFoundException;
import com.devs.exceptions.StudentWithSuchIdExists;
import com.devs.mappers.StudentMapper;
import com.devs.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    public StudentDto save(StudentDto studentDto) throws StudentWithSuchIdExists {
        Student student = StudentMapper.toEntity(studentDto);
        if (student.getEmail() != null && studentRepo.existsById(student.getId())) {
            throw new StudentWithSuchIdExists("Student with Id" + student.getId() + "already exists");
        }
        Student savedStudent = studentRepo.save(student);
        return StudentMapper.toDto(savedStudent);
    }

    public Optional<StudentDto> findById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return Optional.of(StudentMapper.toDto(student));
    }

    public Optional<StudentDto> findByEmail(String email) {
        Student student = studentRepo.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
        return Optional.of(StudentMapper.toDto(student));
    }

    public List<StudentDto> findAll() {
        List<Student> students = studentRepo.findAll();
        return students.stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentDto> update(Long id, StudentDto studentDto) {
       Student studentToUpdate = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
       studentToUpdate.setName(studentDto.getName());
       Student updatedStudent = studentRepo.save(studentToUpdate);
       return Optional.of(StudentMapper.toDto(updatedStudent));
    }

    public void deleteById(Long id) {
        Optional<Student> studentToDelete = Optional.ofNullable(studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
            studentRepo.deleteById(id);
    }
}