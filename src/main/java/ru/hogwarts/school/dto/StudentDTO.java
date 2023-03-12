package ru.hogwarts.school.dto;

import lombok.Data;
@Data
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private Long facultyId;
}