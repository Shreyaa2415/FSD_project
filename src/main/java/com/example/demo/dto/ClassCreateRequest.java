package com.example.demo.dto;

import com.example.demo.Entity.ClassroomType;
import com.example.demo.Entity.Department;

public class ClassCreateRequest {
    private String section;
    private Department department;
    private Integer capacity;
    private Double area;
    private ClassroomType classroomType;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public ClassroomType getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(ClassroomType classroomType) {
        this.classroomType = classroomType;
    }
}

