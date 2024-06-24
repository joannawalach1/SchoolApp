package com.devs.controllers;

import com.devs.domain.Student;
import com.devs.domain.dto.StudentDto;
import com.devs.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.save(studentDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentDto>> findById(@PathVariable Long id) {
        Optional<StudentDto> studentDto = studentService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<StudentDto>> findById(@PathVariable String email) {
        Optional<StudentDto> studentDto = studentService.findByEmail(email);
        return  ResponseEntity.status(HttpStatus.OK).body(studentDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> allStudents = studentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allStudents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<StudentDto>> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentDto.setId(id);
        Optional<StudentDto> updatedStudent = studentService.update(id, studentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
