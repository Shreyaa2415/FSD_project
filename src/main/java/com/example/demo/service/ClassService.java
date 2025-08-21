package com.example.demo.service;

import com.example.demo.Entity.Class;
import com.example.demo.repository.Classrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private Classrepository ClassRepository;

    public Optional<Class> getClassById(int id) {
        return ClassRepository.findById((Integer) id);
    }

    public Class addClass(Class Class) {
        return ClassRepository.save(Class);
    }

    public boolean deleteClassById(int id) {
        if (ClassRepository.existsById((Integer
        ) id)) {
            ClassRepository.deleteById((Integer) id);
            return true;
        } else {
            return false;
        }
    }
}
