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
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final MappingUtils mappingUtils;


    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, MappingUtils mappingUtils) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.mappingUtils = mappingUtils;
    }

    public StudentDTO createStudent(StudentDTO student) {
        student.setId(null);
        studentRepository.save(mappingUtils.mapFromDTOtoStudent(student));
        return student;
    }

    public StudentDTO findStudent(Long studentId) {
        return mappingUtils.mapFromStudentToDTO(Objects.requireNonNull(studentRepository.findById(studentId).orElse(null)));
    }

    public StudentDTO updateStudent(StudentDTO student) {
        studentRepository.save(mappingUtils.mapFromDTOtoStudent(student));
        return student;
    }

    public StudentDTO deleteStudent(Long studentId) {
        StudentDTO student = mappingUtils.mapFromStudentToDTO(Objects.requireNonNull(studentRepository.findById(studentId).orElse(null)));
        studentRepository.deleteById(studentId);
        return student;
    }

    public List<StudentDTO> findAllStudents() {
        return studentRepository.findAll().stream().map(mappingUtils::mapFromStudentToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> findStudentsByAge(int age) {
        return studentRepository.findStudentsByAge(age).stream().map(mappingUtils::mapFromStudentToDTO).collect(Collectors.toList());
    }

    public List<StudentDTO> findByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax).stream().map(mappingUtils::mapFromStudentToDTO).collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByIdStudent(Long studentId) {
        return mappingUtils.mapFromFacultyToDTO(
                Objects.requireNonNull(
                        facultyRepository.findById
                                        (Objects.requireNonNull(studentRepository.findById(studentId).orElse(null)).getFaculty().getId())
                                .orElse(null)));
    }
}