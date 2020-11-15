package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabAccouchement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabAccouchement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabAccouchementRepository extends JpaRepository<TabAccouchement, Long> {
}
