package com.devs.service;

import com.devs.domain.Student;
import com.devs.domain.Subject;
import com.devs.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class SubjectService implements CrudService<Subject, Long> {

    private final SubjectRepo subjectRepo;
    private final Scanner scanner;

    @Autowired
    public SubjectService(SubjectRepo subjectRepo, Scanner scanner) {
        this.subjectRepo = subjectRepo;
        this.scanner = scanner;
    }
    @Override
    public Subject save(Subject entity) {
        System.out.println("Podaj nazwę przedmiotu");
        String name = scanner.nextLine();;
        entity.setName(name);
        Subject savedSubject = subjectRepo.save(entity);
        System.out.println("Dodano subject: " + savedSubject);
        return savedSubject;
    }

    @Override
    public Optional<Subject> findById(Long id) {
        Optional<Subject> foundById = subjectRepo.findById(id);
        if (foundById.isPresent()) {
            System.out.println("Znaleziono subject" + foundById.get());
        } else {
            System.out.println("Nie znaleziono studenta o podanym ID: " + id);
        }
       return foundById;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> allSubjects = subjectRepo.findAll();
        System.out.println("Lista studentów:");
        allSubjects.forEach(System.out::println);
        return allSubjects;
    }

    @Override
    public Subject update(Long id) {
        Optional<Subject> studentToUpdate = subjectRepo.findById(id);
        if (studentToUpdate.isPresent()) {
            System.out.println("Podaj nową nazwę przedmiotu");
            String newName = scanner.nextLine();
            Subject subject = studentToUpdate.get();
            subject.setName(newName);
            subjectRepo.save(subject);
            System.out.println("Zaktualizowano subject o ID: " + id);
            return subject;
        }
        else {
            System.out.println("Nie znaleziono subject o podanym ID: " + id);
            return null;
        }

    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Subject>subjectToDelete = subjectRepo.findById(id);
        if (subjectToDelete.isPresent()) {
            subjectRepo.deleteById(id);
            System.out.println("Usunięto subject o ID: " + id);
            return true;
        } else {
            System.out.println("Nie znaleziono subject o podanym ID: " + id);
            return  false;
        }
    }
}
