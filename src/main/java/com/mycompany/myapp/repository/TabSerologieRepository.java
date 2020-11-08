package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabSerologie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabSerologie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabSerologieRepository extends JpaRepository<TabSerologie, Long> {
}
