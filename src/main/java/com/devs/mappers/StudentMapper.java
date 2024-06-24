package com.devs.mappers;

import com.devs.domain.Exam;
import com.devs.domain.Student;
import com.devs.domain.dto.ExamDto;
import com.devs.domain.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudentMapper {

    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDto newStudentDto = new StudentDto();
        newStudentDto.setId(student.getId());
        newStudentDto.setName(student.getName());
        newStudentDto.setEmail(student.getEmail());

        List<ExamDto> examDtos = new ArrayList<>();
        for (Exam exam : student.getExams()) {
            ExamDto examDto = new ExamDto();
            examDto.setId(exam.getId());
            examDto.setNameOfExam(exam.getNameOfExam());
            examDto.setDateOfExam(exam.getDateOfExam());
            examDtos.add(examDto);
        }
        newStudentDto.setExams(examDtos);
        return newStudentDto;
    }

    public static Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }

        Student newStudent = new Student();
        newStudent.setId(studentDto.getId());
        newStudent.setName(studentDto.getName());
        newStudent.setEmail(studentDto.getEmail());

        List<Exam> exams = new ArrayList<>();
        for (ExamDto examDto : studentDto.getExams()) {
            Exam exam = new Exam();
            exam.setId(examDto.getId());
            exam.setNameOfExam(examDto.getNameOfExam());
            exam.setDateOfExam(examDto.getDateOfExam());
            exam.setStudent(newStudent);
            exams.add(exam);
        }
        newStudent.setExams(exams);
        return newStudent;
    }
}
