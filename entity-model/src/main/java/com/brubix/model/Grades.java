package com.brubix.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */


@Entity
@Table(name = "classes", catalog = "bigrubix")
@Getter
@Setter
public class Grades {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private List<Course> subjects;

    @Column(name = "name", unique = true, nullable = false)
    private String name;


    private class Course {
        List<Subject> subjects;
        String description;
    }

    private class Subject {
        String name;
        String description;
    }
}
