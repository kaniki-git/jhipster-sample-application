package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabExamphys;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabExamphys entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabExamphysRepository extends JpaRepository<TabExamphys, Long> {
}
