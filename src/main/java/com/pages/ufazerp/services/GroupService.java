package com.pages.ufazerp.services;

import com.pages.ufazerp.domain.Group;
import com.pages.ufazerp.repositories.GroupRepository;
import com.pages.ufazerp.util.dto.group.CreateGroupDto;
import com.pages.ufazerp.util.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupService {

    private final GroupRepository groupRepository;
    private final SubjectService subjectService;

    public GroupService(GroupRepository groupRepository, SubjectService subjectService) {
        this.groupRepository = groupRepository;
        this.subjectService = subjectService;
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

}
