package com.example.demo.Controller;

import com.example.demo.Entity.Class;
import com.example.demo.Entity.Department;
import com.example.demo.repository.Classrepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private Classrepository classRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    // ✅ GET class by ID
    @GetMapping("/getid/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable int classId) {
        Optional<Class> cls = classService.getClassById(classId);
        return cls.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ POST add new class (with department)
    @PostMapping("/add")
    public ResponseEntity<?> addClass(@RequestBody Class cls) {
        if (cls.getDepartment() == null || cls.getDepartment().getDeptId() == 0) {
            return ResponseEntity.badRequest().body("❌ Department ID is required.");
        }

        Optional<Department> dept = deptRepo.findById(cls.getDepartment().getDeptId());
        if (dept.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Invalid Department ID.");
        }

        cls.setDepartment(dept.get());
        Class savedClass = classRepo.save(cls);
        return ResponseEntity.ok(savedClass);
    }

    // ✅ PUT update existing class
    @PutMapping("/update/{classId}")
    public ResponseEntity<?> updateClass(@PathVariable int classId, @RequestBody Class updatedClass) {
        Optional<Class> existingOpt = classRepo.findById(classId);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Class existing = existingOpt.get();
        existing.setSection(updatedClass.getSection());

        if (updatedClass.getDepartment() != null && updatedClass.getDepartment().getDeptId() != 0) {
            Optional<Department> dept = deptRepo.findById(updatedClass.getDepartment().getDeptId());
            if (dept.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Invalid Department ID.");
            }
            existing.setDepartment(dept.get());
        }

        Class saved = classRepo.save(existing);
        return ResponseEntity.ok(saved);
    }

    // ✅ DELETE class by IDk
    @DeleteMapping("/delete/{classId}")
    public ResponseEntity<Void> deleteClassById(@PathVariable int classId) {
        boolean deleted = classService.deleteClassById(classId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // ✅ GET all classes (optional but useful)
    @GetMapping("/all")
    public ResponseEntity<?> getAllClasses() {
        return ResponseEntity.ok(classRepo.findAll());
    }
}
