package com.brubix.brubixservice.repository.inventory;

import com.brubix.entity.inventory.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sanjaya on 16/09/17.
 */
public interface TeacherRepository extends JpaRepository<Faculty, Long> {
}
