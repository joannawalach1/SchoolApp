package com.devs.domain;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Exam> exams = new ArrayList<>();


    public Subject(Long id, String name, List<Exam> exams) {
        this.id = id;
        this.name = name;
        this.exams = exams;
    }
    public Subject(int subjectId, String subjectName) {
    }

    public Subject() {
    }

    public Subject(String name) {
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Exam> exams() {
        return exams;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(exams, subject.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, exams);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + exams +
                '}';
    }
}

