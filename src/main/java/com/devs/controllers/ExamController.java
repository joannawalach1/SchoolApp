package com.devs.controllers;


import com.devs.domain.Exam;
import com.devs.domain.dto.ExamDto;
import com.devs.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService ExamService) {
        this.examService = ExamService;
    }

    @PostMapping
    public ResponseEntity<ExamDto> saveExam(@RequestBody ExamDto ExamDto) {
        ExamDto createdExam = examService.save(ExamDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdExam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ExamDto>> findById(@PathVariable Long id) {
        Optional<ExamDto> ExamDto = examService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(ExamDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Exam>> findAll() {
        List<Exam> allExams = examService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allExams);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ExamDto>> updateExam(@PathVariable Long id, @RequestBody ExamDto ExamDto) {
        ExamDto.setId(id);
        Optional<ExamDto> updatedExam = examService.update(id, ExamDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExam);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        examService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
