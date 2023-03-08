package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private final Map<Long, Faculty> faculties;
    private Long generatedFacultyId;

    public HouseService() {
        this.faculties = new HashMap<>();
        this.generatedFacultyId = 1L;
    }

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId);
        faculties.put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }

    public Faculty getFaculty(Long facultyId) {
        return faculties.get(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        faculties.put(facultyId, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyId) {
        return faculties.remove(facultyId);
    }

    public List<Faculty> getAllFaculties() {
        return faculties.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}