package com.example.demo.Controller;

import com.example.demo.Entity.Class;
import com.example.demo.dto.ClassCreateRequest;
import com.example.demo.dto.ClassUpdateRequest;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/getid/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable int classId) {
        Optional<Class> cls = classService.getClassById(classId);
        return cls.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClass(@RequestBody ClassCreateRequest request) {
        String validationError = validateCreateRequest(request);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        try {
            Class savedClass = classService.addClass(request);
            return ResponseEntity.ok(savedClass);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/update/{classId}")
    public ResponseEntity<?> updateClass(@PathVariable int classId, @RequestBody ClassUpdateRequest request) {
        String validationError = validateUpdateRequest(request);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        try {
            Class saved = classService.updateClass(classId, request);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("Class not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{classId}")
    public ResponseEntity<Void> deleteClassById(@PathVariable int classId) {
        boolean deleted = classService.deleteClassById(classId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Class>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    private String validateCreateRequest(ClassCreateRequest request) {
        if (request == null) {
            return "Request body is required.";
        }
        if (request.getSection() == null || request.getSection().trim().isEmpty()) {
            return "Section is required.";
        }
        if (request.getDepartment() == null || request.getDepartment().getDeptId() == 0) {
            return "Department ID is required.";
        }
        if (request.getCapacity() == null || request.getCapacity() <= 0) {
            return "Capacity must be greater than 0.";
        }
        if (request.getArea() == null || request.getArea() <= 0) {
            return "Area must be greater than 0.";
        }
        if (request.getClassroomType() == null) {
            return "Classroom type is required.";
        }
        return null;
    }

    private String validateUpdateRequest(ClassUpdateRequest request) {
        if (request == null) {
            return "Request body is required.";
        }
        if (request.getSection() != null && request.getSection().trim().isEmpty()) {
            return "Section cannot be empty.";
        }
        if (request.getDepartment() != null && request.getDepartment().getDeptId() == 0) {
            return "Invalid Department ID.";
        }
        if (request.getCapacity() != null && request.getCapacity() <= 0) {
            return "Capacity must be greater than 0.";
        }
        if (request.getArea() != null && request.getArea() <= 0) {
            return "Area must be greater than 0.";
        }
        return null;
    }
}
