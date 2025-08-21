package com.example.demo.Controller;

import com.example.demo.Entity.Class;
import com.example.demo.repository.Classrepository;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService ClassService;

    @Autowired
    private Classrepository repo;
    // GET student by ID
    @GetMapping("getid/{classId}")
    public ResponseEntity<Class> getclassById(@PathVariable int classId) {
        Optional<Class> Class = ClassService.getClassById(classId);
        return Class.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST add new student
    @PostMapping("/add")
    public Class addClass(@RequestBody Class Class) {
        return repo.save(Class);
    }

    // DELETE student by ID
    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClassById(@PathVariable int classId) {
        boolean deleted = ClassService.deleteClassById(classId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
