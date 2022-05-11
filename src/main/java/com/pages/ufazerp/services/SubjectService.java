package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Subject;
import com.pages.ufazerp.repositories.SubjectRepository;
import com.pages.ufazerp.util.dto.subject.CreateSubjectDto;
import com.pages.ufazerp.util.dto.subject.UpdateSubjectDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> readAll() {
        return subjectRepository.findAll();
    }

    public Subject readById(Long id) throws NotFoundException {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no subject(id=%d)", id)));
    }

    public List<Subject> readAllById(List<Long> ids) {
        return subjectRepository.findAllById(ids);
    }

    public Subject createSubject(CreateSubjectDto dto) throws ValidationException {
        if(dto.getName()==null) {
            throw new ValidationException("Name cannot be null");
        }
        if(subjectRepository.findByName(dto.getName()).isPresent()) {
            throw new ValidationException(String.format("subject(name=%s) already exists", dto.getName()));
        }
        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setCredits(dto.getCredits());
        subject.setTotalNumberOfLessons(dto.getTotalNumberOfLessons());
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(long id, UpdateSubjectDto dto) throws NotFoundException, ValidationException {
        Subject subject = readById(id);
        if(dto.getName()!=null) {
            if(subjectRepository.findByName(dto.getName()).isPresent()) {
                throw new ValidationException(String.format("subject(name=%s) already exists", dto.getName()));
            }
            subject.setName(dto.getName());
        }

        if(dto.getCredits()!=null) {
            subject.setCredits(dto.getCredits());
        }
        if(dto.getTotalNumberOfLessons()!=null) {
            subject.setTotalNumberOfLessons(dto.getTotalNumberOfLessons());
        }
        return subjectRepository.save(subject);
    }

    public void deleteSubject(long id) {
        if(!subjectRepository.findById(id).isPresent()) {
            return;
        }
        subjectRepository.deleteById(id);
    }
}
