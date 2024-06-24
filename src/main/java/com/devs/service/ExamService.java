package com.devs.service;

import com.devs.domain.Exam;
import com.devs.domain.dto.ExamDto;
import com.devs.domain.dto.SubjectDto;
import com.devs.exceptions.StudentNotFoundException;
import com.devs.mappers.ExamMapper;
import com.devs.mappers.SubjectMapper;
import com.devs.repository.ExamRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepo examRepo;
    private final ExamMapper examMapper;


    @Transactional
    public ExamDto save(ExamDto examDto) {
        Exam exam = ExamMapper.toEntity(examDto);
        Exam savedExam = examRepo.save(exam);
        return ExamMapper.toDto(savedExam);
    }

    public Optional<ExamDto> findById(Long id) {
        Exam foundExam = examRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Exam not found"));
        return Optional.ofNullable(ExamMapper.toDto(foundExam));
    }

    public List<Exam> findAll() {
        List<Exam> allExams = examRepo.findAll();
        return allExams;
    }

    @Transactional
    public Optional<SubjectDto> update(Long id, String newExam) {
        Exam examToUpdate = examRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Exam not found"));
        examToUpdate.setNameOfExam(newExam);
        Exam updatedExam = examRepo.save(examToUpdate);
        return Optional.ofNullable(SubjectMapper.toDto(updatedExam));
    }
    @Transactional
    public void deleteById(Long id) {
        Optional<Exam> examToDelete = Optional.ofNullable(examRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Exam not found")));
        examRepo.deleteById(id);
    }
}