package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

@Service
public class MappingUtils {
    private FacultyRepository facultyRepository;

    public MappingUtils(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO mapFromFacultyToDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public Faculty mapFromDTOtoFaculty(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        faculty.setId(dto.getId());
        faculty.setName(dto.getName());
        faculty.setColor(dto.getColor());
        return faculty;
    }

    public StudentDTO mapFromStudentToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        return dto;
    }

    public Student mapFromDTOtoStudent(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setFaculty(facultyRepository.findById(dto.getFacultyId()).orElse(null));
        return student;
    }
}