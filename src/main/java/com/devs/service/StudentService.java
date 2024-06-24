package com.devs.service;

import com.devs.domain.Student;
import com.devs.exceptions.StudentNotFoundException;
import com.devs.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class StudentService implements CrudService<Student, Long> {

    private final StudentRepo studentRepo;
    private final Scanner scanner;

    public StudentService(StudentRepo studentRepo, Scanner scanner) {
        this.studentRepo = studentRepo;
        this.scanner = scanner;
    }

    @Override
    public Student save(Student entity) {
        System.out.println("Podaj imię studenta");
        String name = scanner.nextLine();
        System.out.println("Podaj email studenta");
        String email = scanner.nextLine();
        entity.setName(name);
        entity.setEmail(email);
        Student savedStudent = studentRepo.save(entity);
        System.out.println("Dodano studenta: " + savedStudent);
        return savedStudent;
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<Student> foundById = Optional.ofNullable(studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found")));
//        if (foundById.isPresent()) {
//            System.out.println("Znaleziono studenta" + foundById.get());
//        } else {
//            System.out.println("Nie znaleziono studenta o podanym ID: " + id);
//        }
        return foundById;
    }

    @Override
    public List<Student> findAll() {
        List<Student> allStudents = studentRepo.findAll();
        System.out.println("Lista studentów:");
        allStudents.forEach(System.out::println);
        return allStudents;
    }

    @Override
    public Student update(Long id) {
        Optional<Student> studentToUpdate = studentRepo.findById(id);
        if (studentToUpdate.isPresent()) {
            System.out.println("Podaj nowe imię studenta");
            String newName = scanner.nextLine();
            System.out.println("Podaj nowy email studenta");
            String newEmail = scanner.nextLine();
            Student student = studentToUpdate.get();
            student.setName(newName);
            student.setEmail(newEmail);
            studentRepo.save(student);
            System.out.println("Zaktualizowano studenta o ID: " + id);
            return student;
        } else {
            System.out.println("Nie znaleziono studenta o podanym ID: " + id);
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Student> studentToDelete = studentRepo.findById(id);
        if (studentToDelete.isPresent()) {
            studentRepo.deleteById(id);
            System.out.println("Usunięto studenta o ID: " + id);
            return true;
        } else {
            System.out.println("Nie znaleziono studenta o podanym ID: " + id);
            return false;
        }
    }
}