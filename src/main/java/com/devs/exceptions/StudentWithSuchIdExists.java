package com.devs.exceptions;

public class StudentWithSuchIdExists extends Throwable {
    public StudentWithSuchIdExists(String message) {
        super (message);
    }
}
