package com.example.demo.service;

import com.example.demo.Entity.Class;
import com.example.demo.Entity.Department; // isko add kiya hai becoz Department bhi chahiye hume
import com.example.demo.repository.Classrepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService { // simply functions banaya he jo controller use karega

    @Autowired
    private Classrepository classRepository;

    @Autowired
    private DepartmentRepository departmentRepository; // DepartmentRepository ko bhi inject kiya hai becoz dummy use karenge

    public Optional<Class> getClassById(int id) {
        return classRepository.findById(id);
    }

    public Class addClass(Class cls) {
        return classRepository.save(cls);
    }

    public boolean deleteClassById(int id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Class updateClass(int id, Class updatedClass) {
        Optional<Class> existing = classRepository.findById(id);
        if (existing.isPresent()) {
            Class cls = existing.get();
            cls.setSection(updatedClass.getSection());
            cls.setDepartment(updatedClass.getDepartment());
            return classRepository.save(cls);
        } else {
            throw new RuntimeException("Class not found with ID: " + id);
        }
    }
}
