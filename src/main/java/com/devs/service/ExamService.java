package com.devs.service;

import com.devs.domain.Exam;
import com.devs.repository.ExamRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ExamService implements CrudService<Exam, Long> {
    private final Scanner scanner;
    private final ExamRepo examRepo;

    public ExamService(Scanner scanner, ExamRepo examRepo) {
        this.scanner = scanner;
        this.examRepo = examRepo;
    }

    @Override
    public Exam save(Exam entity) {
        System.out.println("Podaj nazwę egzaminu");
        String name = scanner.nextLine();;
        System.out.println("Podaj datę egzaminu (YYYY-MM-DD):");
        String newDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime examDate = LocalDateTime.parse(newDate, formatter);
        entity.setNameOfExam(name);
        entity.setDateOfExam(examDate);
        Exam savedExam = examRepo.save(entity);
        System.out.println("Dodano subject: " + savedExam);
        return savedExam;
    }
    @Override
    public Optional<Exam> findById(Long id) {
        Optional<Exam> foundById = examRepo.findById(id);
        if (foundById.isPresent()) {
            System.out.println("Znaleziono subject" + foundById.get());
        } else {
            System.out.println("Nie znaleziono studenta o podanym ID: " + id);
        }
        return foundById;
    }

    @Override
    public List<Exam> findAll() {
        List<Exam> allExams = examRepo.findAll();
        System.out.println("Lista studentów:");
        allExams.forEach(System.out::println);
        return allExams;
    }

    @Override
    public Exam update(Long id) {
        Optional<Exam> examToUpdate = examRepo.findById(id);
        if (examToUpdate.isPresent()) {
            System.out.println("Podaj nazwę egzaminu:");
            String newName = scanner.nextLine();
            System.out.println("Podaj datę egzaminu (YYYY-MM-DD):");
            String newDate = scanner.nextLine();
            LocalDate examDate = LocalDate.parse(newDate);
            Exam exam = examToUpdate.get();
            exam.setNameOfExam(newName);
            Exam savedExam = examRepo.save(exam);
            System.out.println("Zaktualizowano egzamin o ID: " + id);
            return savedExam;
        }
        else {
            System.out.println("Nie znaleziono egzaminu o podanym ID: " + id);
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Exam> examToDelete = examRepo.findById(id);
        if (examToDelete.isPresent()) {
            examRepo.deleteById(id);
            System.out.println("Usunięto egzamin o ID: " + id);
            return true;
        } else {
            System.out.println("Nie znaleziono egzaminu o podanym ID: " + id);
            return  false;
        }
    }
}