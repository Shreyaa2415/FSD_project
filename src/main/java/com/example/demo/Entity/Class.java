package com.example.demo.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class") // Table name in PostgreSQL
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int classId;

    private String section;
    private int deptId;
    // Default constructor (required by JPA)
    public Class() {
    }

    // Constructor with fields
    public Class(int classId, String name,String section) {
        this.classId= classId;
        this.section = section;
      
    }

    // Getters and Setters
    public int getclassId() {
        return classId;
    }

    public void setclassId(int classId) {
        this.classId = classId;
    }

    public String getsection() {
        return section;
    }

    public void setsection(String section) {
        this.section = section;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "class [id=" + classId + ", section=" + section+ "]";
    }
}
