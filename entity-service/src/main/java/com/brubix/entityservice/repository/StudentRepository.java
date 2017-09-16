package com.brubix.entityservice.repository;

import com.brubix.model.Student;
import com.brubix.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 16/09/17.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
