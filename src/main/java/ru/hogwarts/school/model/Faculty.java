package ru.hogwarts.school.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    @OneToMany
    private List<Student> students;
}