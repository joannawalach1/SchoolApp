package com.devs.service;

import com.devs.domain.Subject;
import com.devs.domain.dto.SubjectDto;
import com.devs.exceptions.StudentNotFoundException;
import com.devs.mappers.SubjectMapper;
import com.devs.repository.SubjectRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;
    private final SubjectMapper subjectMapper;

    @Transactional
    public SubjectDto save(SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        Subject savedSubject= subjectRepo.save(subject);
        return subjectMapper.toDto(savedSubject);
    }

    public Optional<SubjectDto> findById(Long id) {
        Subject foundSubject = subjectRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Subject not found"));
        return Optional.ofNullable(SubjectMapper.toDto(foundSubject));
    }

    public List<Subject> findAll() {
        List<Subject> allSubjects = subjectRepo.findAll();
        return allSubjects;
    }

    @Transactional
    public Optional<SubjectDto> update(Long id, String newSubject) {
        Subject subjectToUpdate = subjectRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Subject not found"));
        subjectToUpdate.setName(newSubject);
        Subject updatedSubject = subjectRepo.save(subjectToUpdate);
        return Optional.ofNullable(SubjectMapper.toDto(updatedSubject));
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Subject> subjectToDelete = Optional.ofNullable(subjectRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Subject not found")));
        subjectRepo.deleteById(id);
    }
}
