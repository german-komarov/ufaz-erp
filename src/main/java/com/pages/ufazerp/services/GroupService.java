package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.repositories.GroupRepository;
import com.pages.ufazerp.util.dto.group.CreateGroupDto;
import com.pages.ufazerp.util.dto.group.UpdateGroupDto;
import com.pages.ufazerp.util.exceptions.NotFoundException;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupService {

    private final GroupRepository groupRepository;
    private final SubjectService subjectService;

    public GroupService(GroupRepository groupRepository, SubjectService subjectService) {
        this.groupRepository = groupRepository;
        this.subjectService = subjectService;
    }

    public Group readById(Long id) throws NotFoundException {
        return groupRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("There is no room(id=%d)", id)));
    }

    public List<Group> readAll() {
        return groupRepository.findAll();
    }

    public Group createGroup(CreateGroupDto dto) throws ValidationException {
        if(dto.getName() == null) {
            throw new ValidationException("Name cannot be null");
        }
        if(groupRepository.findByName(dto.getName()).isPresent()) {
            throw new ValidationException(String.format("group(name=%s) already exists", dto.getName()));
        }
        if(dto.getLevel() == null) {
            throw new ValidationException("Level cannot be null");
        }
        if(dto.getSubgroup() == null) {
            throw new ValidationException("Subgroup cannot be null");
        }
        Group group = new Group();
        group.setName(dto.getName());
        group.setSubgroup(dto.getSubgroup());
        group.setLevel(dto.getLevel());
        if(dto.getSubjects() != null && !dto.getSubjects().isEmpty()) {
            group.addSubjects(new HashSet<>(subjectService.readAllById(new ArrayList<>(dto.getSubjects()))));
        }
        return groupRepository.save(group);
    }


    public Group updateGroup(long id, UpdateGroupDto dto) throws NotFoundException, ValidationException {
        Group group = readById(id);
        if(dto.getName()!=null) {
            if(groupRepository.findByName(dto.getName()).isPresent()) {
                throw new ValidationException(String.format("group(name=%s) already exists", dto.getName()));
            }
            group.setName(dto.getName());
        }
        if(dto.getLevel()!=null) {
            group.setLevel(dto.getLevel());
        }
        if(dto.getSubgroup()!=null) {
            group.setSubgroup(dto.getSubgroup());
        }
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        if(!groupRepository.findById(id).isPresent()) {
            return;
        }
        groupRepository.deleteById(id);
    }
}
