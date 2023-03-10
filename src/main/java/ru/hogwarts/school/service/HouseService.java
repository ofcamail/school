package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class HouseService {

    private FacultyRepository facultyRepository;

    @Autowired
    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(null);
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFaculty(Long facultyId) {
        return facultyRepository.getReferenceById(facultyId);
    }

    public Faculty updateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.getReferenceById(facultyId);
        facultyRepository.deleteById(facultyId);
        return faculty;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public List<Faculty> findFacultiesByColor(String color){
        return facultyRepository.findFacultiesByColor(color);
    }
}