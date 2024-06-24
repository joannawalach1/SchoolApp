package com.devs.mappers;

import com.devs.domain.Exam;
import com.devs.domain.Subject;
import com.devs.domain.dto.SubjectDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class SubjectMapper {

    public static Subject toEntity(SubjectDto subjectDto) {
        if (subjectDto == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setExams(subjectDto.getExams());

        if (subjectDto.getExamId() != null) {
            Exam exam = new Exam();
            exam.setId(subjectDto.getExamId());
            subject.setExams(new ArrayList<>());
            subject.getExams().add(exam);
        } else {
            subject.setExams(null);
        }

        return subject;
    }

    public static SubjectDto toDto(Exam subject) {
        if (subject == null) {
            return null;
        }

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setExams(subject.getExams());

        return subjectDto;
    }
}
