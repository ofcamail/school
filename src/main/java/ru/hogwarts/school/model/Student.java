package ru.hogwarts.school.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;
}