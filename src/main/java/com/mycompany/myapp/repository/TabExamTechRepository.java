package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabExamTech;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabExamTech entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabExamTechRepository extends JpaRepository<TabExamTech, Long> {
}
