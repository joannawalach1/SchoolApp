package com.devs.mappers;

import com.devs.domain.Subject;
import com.devs.domain.dto.SubjectDto;
import org.springframework.stereotype.Component;
@Component
public class SubjectMapper {

    public static Subject toEntity(SubjectDto subjectDto) {
        if (subjectDto == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        return subject;
    }

    public static SubjectDto toDto(Subject subject) {
        if (subject == null) {
            return null;
        }

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subject.getName());

        return subjectDto;
    }
}
