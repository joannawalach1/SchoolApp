package com.devs.controllers;

import com.devs.domain.Subject;
import com.devs.domain.dto.SubjectDto;
import com.devs.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/subject")
    public class SubjectController {
        private final SubjectService subjectService;

        public SubjectController(SubjectService subjectService) {
            this.subjectService = subjectService;
        }

        @PostMapping
        public ResponseEntity<SubjectDto> savesubject(@RequestBody SubjectDto subjectDto) {
            SubjectDto createdSubject = subjectService.save(subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body(createdSubject);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<SubjectDto>> findById(@PathVariable Long id) {
            Optional<SubjectDto> subjectDto = subjectService.findById(id);
            return  ResponseEntity.status(HttpStatus.OK).body(subjectDto);
        }

        @GetMapping("/products")
        public ResponseEntity<List<Subject>> findAll() {
            List<Subject> allsubjects = subjectService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allsubjects);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Optional<SubjectDto>> updateSubject(@PathVariable Long id, @RequestBody String subjectDto) {
            Optional<SubjectDto> updatedSubject = subjectService.update(id, subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedSubject);
        }

        @DeleteMapping("/{id")
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            subjectService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
