package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabUrgence;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabUrgence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabUrgenceRepository extends JpaRepository<TabUrgence, Long> {
}
