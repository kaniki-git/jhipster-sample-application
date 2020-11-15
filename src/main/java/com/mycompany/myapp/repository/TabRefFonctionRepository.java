package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabRefFonction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabRefFonction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabRefFonctionRepository extends JpaRepository<TabRefFonction, Long> {
}
