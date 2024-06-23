package com.devs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.HashMap;
import java.util.Objects;


@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private HashMap<Integer, Long> marks = new HashMap<Integer, Long>();


    public Subject(Long id, String name, HashMap<Integer, Long> marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
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
    public HashMap<Integer, Long> getMarks() {
        return marks;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(HashMap<Integer, Long> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(marks, subject.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, marks);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}

