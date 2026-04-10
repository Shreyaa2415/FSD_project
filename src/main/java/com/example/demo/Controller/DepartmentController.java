package com.example.demo.Controller;

import com.example.demo.Entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(savedDepartment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable int deptId) {
        if (departmentRepository.existsById(deptId)) {
            departmentRepository.deleteById(deptId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
