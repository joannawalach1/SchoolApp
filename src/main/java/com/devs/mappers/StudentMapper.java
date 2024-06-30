package com.devs.mappers;

import com.devs.domain.Student;
import com.devs.domain.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public static Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }

        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setExams(studentDto.getExams().stream()
                .map(ExamMapper::toEntity)
                .collect(Collectors.toList()));
        return student;
    }

    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setExams(student.getExams().stream()
                .map(ExamMapper::toDto)
                .collect(Collectors.toList()));
        return studentDto;
    }
}
