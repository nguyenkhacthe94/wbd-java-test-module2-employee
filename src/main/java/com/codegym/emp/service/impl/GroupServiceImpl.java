package com.codegym.emp.service.impl;

import com.codegym.emp.model.Group;
import com.codegym.emp.repository.GroupRepository;
import com.codegym.emp.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private GroupRepository repository;
    @Autowired
    public GroupServiceImpl (GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Group> findAll() {
        return repository.findAll();
    }
}
