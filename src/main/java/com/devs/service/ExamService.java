package com.devs.service;

import com.devs.domain.Exam;
import com.devs.domain.Subject;
import com.devs.domain.dto.ExamDto;
import com.devs.exceptions.ExamNotFoundException;
import com.devs.mappers.ExamMapper;
import com.devs.repository.ExamRepo;
import com.devs.repository.StudentRepo;
import com.devs.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepo examRepo;
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;
    @Transactional
    public ExamDto save(ExamDto examDto) {
        Subject subject = subjectRepo.findById(examDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + examDto.getSubjectId()));


        Exam exam = ExamMapper.toEntity(examDto);
        exam.setSubject(subject);

        Exam savedExam = examRepo.save(exam);

        return ExamMapper.toDto(savedExam);
    }


    @Transactional(readOnly = true)
    public Optional<ExamDto> findById(Long id) {
        Exam foundExam = examRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Exam not found with ID: " + id));
        return Optional.ofNullable(ExamMapper.toDto(foundExam));
    }

    @Transactional
    public Optional<ExamDto> update(Long id, String newExamName) {
        Exam examToUpdate = examRepo.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID: " + id));
        examToUpdate.setNameOfExam(newExamName);
        Exam updatedExam = examRepo.save(examToUpdate);
        return Optional.of(ExamMapper.toDto(updatedExam));
    }

    @Transactional
    public void deleteById(Long id) {
        examRepo.deleteById(id);
    }
}
