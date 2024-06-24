package com.devs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
        private Long id;
        private String name;
        private String email;
        private List<ExamDto> exams;
}
