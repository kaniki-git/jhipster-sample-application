package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabCoordonnee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabCoordonnee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabCoordonneeRepository extends JpaRepository<TabCoordonnee, Long> {
}
