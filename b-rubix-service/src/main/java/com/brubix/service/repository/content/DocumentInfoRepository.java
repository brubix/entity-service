package com.brubix.service.repository.content;

import com.brubix.entity.inventory.DocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentInfoRepository extends JpaRepository<DocumentInfo, Long> {
}
