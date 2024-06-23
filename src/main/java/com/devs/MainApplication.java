package com.devs;

import com.devs.domain.Exam;
import com.devs.domain.Student;
import com.devs.domain.Subject;
import com.devs.service.ExamService;
import com.devs.service.StudentService;
import com.devs.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamService examService;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            displayMenu();
        };
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Student");
            System.out.println("2. Subject");
            System.out.println("3. Exam");
            System.out.println("4. Wyjdź");

            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentSubMenu();
                    break;
                case 2:
                    subjectSubMenu();
                    break;
                case 3:
                    examSubMenu();
                    break;
                case 4:
                    System.out.println("Wybrano opcję wyjścia. Do widzenia!");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    break;
            }
            System.out.println();
        } while (choice != 4);
    }

    public void studentSubMenu() {
        int choice;

        do {
            System.out.println("Student:");
            System.out.println("1. Dodaj studenta");
            System.out.println("2. Usuń studenta");
            System.out.println("3. Aktualizuj studenta");
            System.out.println("4. Wyświetl wszystkich studentów");
            System.out.println("5. Wróć do menu głównego");

            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Student newStudent = new Student();
                    studentService.save(newStudent);
                    break;
                case 2:
                    System.out.println("Podaj ID studenta do usunięcia");
                    Long idToDelete = scanner.nextLong();
                    scanner.nextLine();
                    studentService.deleteById(idToDelete);
                    break;
                case 3:
                    System.out.println("Podaj ID studenta do aktualizacji");
                    Long idToUpdate = scanner.nextLong();
                    scanner.nextLine();
                    studentService.update(idToUpdate);
                    break;
                case 4:
                    studentService.findAll().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Powrót do menu głownego");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    break;
            }
            System.out.println();
        } while (choice != 5);
    }

    public void subjectSubMenu() {
        int choice;

        do {
            System.out.println("Subject:");
            System.out.println("1. Dodaj przedmiot");
            System.out.println("2. Usuń przedmiot");
            System.out.println("3. Aktualizuj przedmiot");
            System.out.println("4. Wyświetl wszystkie przedmioty");
            System.out.println("5. Wróć do menu głównego");

            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Subject subject = new Subject();
                    subjectService.save(subject);
                    break;
                case 2:
                    System.out.println("Podaj ID przedmiotu do usunięcia");
                    Long idToDelete = scanner.nextLong();
                    scanner.nextLine();
                    subjectService.deleteById(idToDelete);
                    break;
                case 3:
                    System.out.println("Podaj ID przedmiotu do aktualizacji");
                    Long idToUpdate = scanner.nextLong();
                    scanner.nextLine();
                    subjectService.update(idToUpdate);
                    break;
                case 4:
                    subjectService.findAll().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Powrót do menu głownego");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    break;
            }
            System.out.println();
        } while (choice != 5);
    }

    public void examSubMenu() {
        int choice;

        do {
            System.out.println("Exams:");
            System.out.println("1. Dodaj egzamin");
            System.out.println("2. Usuń egzamin");
            System.out.println("3. Aktualizuj egzamin");
            System.out.println("4. Wyświetl wszystkie egzaminy");
            System.out.println("5. Wróć do menu głównego");

            System.out.print("Wybierz opcję: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Exam exam = new Exam();
                    examService.save(exam);
                    break;
                case 2:
                    System.out.println("Podaj ID egzaminu do usunięcia");
                    Long idToDelete = scanner.nextLong();
                    scanner.nextLine();
                    examService.deleteById(idToDelete);
                    break;
                case 3:
                    System.out.println("Podaj ID egzaminu do aktualizacji");
                    Long idToUpdate = scanner.nextLong();
                    scanner.nextLine();
                    examService.update(idToUpdate);
                    break;
                case 4:
                    examService.findAll().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Powrót do menu głownego");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                    break;
            }
            System.out.println();
        } while (choice != 5);
    }
}
