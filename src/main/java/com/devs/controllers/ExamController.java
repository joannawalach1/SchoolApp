package com.devs.controllers;


import com.devs.domain.dto.ExamDto;
import com.devs.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService ExamService) {
        this.examService = ExamService;
    }

    @PostMapping
    public ResponseEntity<ExamDto> saveExam(@RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.save(examDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdExam);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ExamDto>> findById(@PathVariable Long id) {
        Optional<ExamDto> examDto = examService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(examDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ExamDto>> updateExam(@PathVariable Long id, @RequestBody String examDto) {
        Optional<ExamDto> updatedExam = examService.update(id, examDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        examService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
