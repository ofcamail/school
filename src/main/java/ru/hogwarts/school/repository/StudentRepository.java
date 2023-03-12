package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<StudentDTO> findStudentsByAge(int age);
    List<StudentDTO> findByAgeBetween(int ageMin, int ageMax);
}