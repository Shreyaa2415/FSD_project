package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    private String section;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "deptId")
    private Department department;

    public Class() {}

    public Class(String section, Department department) {
        this.section = section;
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

    @Override
    public String toString() {
        return "Class [classId=" + classId + ", section=" + section +
                ", department=" + (department != null ? department.getDeptName() : "null") + "]";
    }
}
