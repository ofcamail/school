package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class HouseService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public HouseService(FacultyRepository facultyRepository, StudentRepository studentRepository, MappingUtils mappingUtils) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.mappingUtils = mappingUtils;
    }

    public FacultyDTO createFaculty(FacultyDTO faculty) {
        faculty.setId(null);
        facultyRepository.save(mappingUtils.mapFromDTOtoFaculty(faculty));
        return faculty;
    }

    public FacultyDTO findFaculty(Long facultyId) {
        return mappingUtils.mapFromFacultyToDTO(Objects.requireNonNull(facultyRepository.findById(facultyId).orElse(null)));
    }

    public FacultyDTO updateFaculty(FacultyDTO faculty) {
        facultyRepository.save(mappingUtils.mapFromDTOtoFaculty(faculty));
        return faculty;
    }

    public FacultyDTO deleteFaculty(Long facultyId) {
        FacultyDTO faculty = mappingUtils.mapFromFacultyToDTO(Objects.requireNonNull(facultyRepository.findById(facultyId).orElse(null)));
        facultyRepository.deleteById(facultyId);
        return faculty;
    }

    public List<FacultyDTO> findAllFaculties() {
        return facultyRepository.findAll().stream().map(mappingUtils::mapFromFacultyToDTO).collect(Collectors.toList());
    }

    public List<FacultyDTO> findFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColor(color).stream().map(mappingUtils::mapFromFacultyToDTO).collect(Collectors.toList());
    }

    public List<FacultyDTO> findFacultyByName(String name) {
        return facultyRepository.findFacultyByName(name).stream().map(mappingUtils::mapFromFacultyToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> getStudentsByFacultyId(Long facultyId) {
        return studentRepository.findAllByFacultyId(facultyId).stream()
                .map(mappingUtils::mapFromStudentToDTO)
                .collect(Collectors.toList());
    }
}