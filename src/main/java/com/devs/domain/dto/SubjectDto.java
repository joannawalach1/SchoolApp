package com.devs.domain.dto;

import com.devs.domain.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
        private Long id;
        private String name;
        private List<Exam> exams = new ArrayList<>();
        private Long examId;

}
