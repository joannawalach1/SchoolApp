package com.devs.mappers;

import com.devs.domain.Exam;
import com.devs.domain.Student;
import com.devs.domain.Subject;
import com.devs.domain.dto.ExamDto;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper {
    public static ExamDto toDto(Exam exam) {
        if (exam == null) {
            return null;
        }

        ExamDto newExamDto = new ExamDto();
        newExamDto.setId(exam.getId());
        newExamDto.setNameOfExam(exam.getNameOfExam());
        newExamDto.setDateOfExam(exam.getDateOfExam());
        if (exam.getStudent() != null) {
            newExamDto.setStudentId((exam.getStudent().getId()));
        }
        if (exam.getSubject() != null) {
            newExamDto.setSubjectId((exam.getSubject().getId()));
        }

        return newExamDto;
    }

    public static Exam toEntity(ExamDto examDto) {
        if (examDto == null) {
            return null;
        }

        Exam newExam = new Exam();
        newExam.setId(examDto.getId());
        newExam.setNameOfExam(examDto.getNameOfExam());
        newExam.setDateOfExam(examDto.getDateOfExam());
        if (examDto.getStudentId() != null) {
            Student student = new Student();
            student.setId(examDto.getStudentId());
            newExam.setStudent(student);
        } else {
            newExam.setStudent(null);
        }

        if (examDto.getSubjectId() != null) {
            Subject subject = new Subject();
            subject.setId(examDto.getStudentId());
            newExam.setSubject(subject);
        } else {
            newExam.setSubject(null);
        }
        return newExam;
    }
}
