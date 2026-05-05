package com.example.demo.service;

import com.example.demo.Entity.Class;
import com.example.demo.Entity.Department;
import com.example.demo.dto.ClassCreateRequest;
import com.example.demo.dto.ClassUpdateRequest;
import com.example.demo.repository.Classrepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Class addClass(ClassCreateRequest request) {
        Department department = departmentRepository.findById(request.getDepartment().getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Department ID."));

        Class cls = new Class();
        cls.setSection(request.getSection().trim());
        cls.setDepartment(department);
        cls.setCapacity(request.getCapacity());
        cls.setArea(request.getArea());
        cls.setClassroomType(request.getClassroomType());
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

    public Class updateClass(int id, ClassUpdateRequest updatedClass) {
        Optional<Class> existing = classRepository.findById(id);
        if (existing.isPresent()) {
            Class cls = existing.get();
            if (updatedClass.getSection() != null && !updatedClass.getSection().trim().isEmpty()) {
                cls.setSection(updatedClass.getSection().trim());
            }
            if (updatedClass.getDepartment() != null && updatedClass.getDepartment().getDeptId() != 0) {
                Department department = departmentRepository.findById(updatedClass.getDepartment().getDeptId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Department ID."));
                cls.setDepartment(department);
            }
            if (updatedClass.getCapacity() != null) {
                cls.setCapacity(updatedClass.getCapacity());
            }
            if (updatedClass.getArea() != null) {
                cls.setArea(updatedClass.getArea());
            }
            if (updatedClass.getClassroomType() != null) {
                cls.setClassroomType(updatedClass.getClassroomType());
            }
            return classRepository.save(cls);
        } else {
            throw new RuntimeException("Class not found with ID: " + id);
        }
    }
}
