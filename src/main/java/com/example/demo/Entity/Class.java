package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Double area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassroomType classroomType;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "deptId")
    private Department department;

    public Class() {}

    public Class(String section, Integer capacity, Double area, ClassroomType classroomType, Department department) {
        this.section = section;
        this.capacity = capacity;
        this.area = area;
        this.classroomType = classroomType;
        this.department = department;
    }

    // getters n setters
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

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

    @Override
    public String toString() {
        return "Class [classId=" + classId + ", section=" + section +
                ", capacity=" + capacity + ", area=" + area + ", classroomType=" + classroomType +
                ", department=" + (department != null ? department.getDeptName() : "null") + "]";
    }
}
