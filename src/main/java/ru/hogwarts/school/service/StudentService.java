package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setId(null);
        studentRepository.save(student);
        return student;
    }

    public Student getStudent(Long studentId) {
        return studentRepository.getReferenceById(studentId);
    }

    public Student updateStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        Student student = studentRepository.getReferenceById(studentId);
        studentRepository.deleteById(studentId);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentsByAge(int age){
        return studentRepository.findStudentsByAge(age);
    }
}