package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;

@RequestMapping("api")
@RestController
public class HouseController {
    HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/faculty")
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO faculty) {
        FacultyDTO createdFaculty = houseService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long facultyId) {
        FacultyDTO faculty = houseService.findFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/faculty")
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO faculty) {
        FacultyDTO updatedFaculty = houseService.updateFaculty(faculty);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/faculty/{facultyId}")
    public ResponseEntity<FacultyDTO> deleteFaculty(@PathVariable Long facultyId) {
        FacultyDTO faculty = houseService.deleteFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/faculties")
    public ResponseEntity<Collection<FacultyDTO>> findAllFaculties(@RequestParam(required = false) String color,
                                                                   @RequestParam(required = false) String name) {
        if (color != null) {
            return ResponseEntity.ok(houseService.findFacultiesByColor(color));
        }
        if (name != null) {
            return ResponseEntity.ok(houseService.findFacultyByName(name));
        }
        return ResponseEntity.ok(houseService.findAllFaculties());
    }


    @GetMapping("/faculty/students")
    public ResponseEntity<Collection<StudentDTO>> getStudentsByFacultyId(Long facultyId) {
        return ResponseEntity.ok(houseService.getStudentsByFacultyId(facultyId));
    }
}