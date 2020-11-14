package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabExamPhys;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabExamPhys entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabExamPhysRepository extends JpaRepository<TabExamPhys, Long> {
}
