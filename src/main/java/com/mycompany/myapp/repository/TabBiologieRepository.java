package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabBiologie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabBiologie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabBiologieRepository extends JpaRepository<TabBiologie, Long> {
}
