package com.devs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nameOfExam;
    private LocalDateTime dateOfExam;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameOfExam(String nameOfExam) {
        this.nameOfExam = nameOfExam;
    }

    public void setDateOfExam(LocalDateTime dateOfExam) {
        this.dateOfExam = dateOfExam;
    }



    public Long getId() {
        return id;
    }


    public String getNameOfExam() {
        return nameOfExam;
    }

    public LocalDateTime getDateOfExam() {
        return dateOfExam;
    }

    public Exam() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) && Objects.equals(nameOfExam, exam.nameOfExam) && Objects.equals(dateOfExam, exam.dateOfExam);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfExam, dateOfExam);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", nameOfExam='" + nameOfExam + '\'' +
                ", dateOfExam=" + dateOfExam +
                '}';
    }
}
