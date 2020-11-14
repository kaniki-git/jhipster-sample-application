package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabGynecologie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabGynecologie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabGynecologieRepository extends JpaRepository<TabGynecologie, Long> {
}
