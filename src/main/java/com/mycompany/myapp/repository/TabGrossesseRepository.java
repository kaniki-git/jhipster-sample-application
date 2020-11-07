package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabGrossesse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabGrossesse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabGrossesseRepository extends JpaRepository<TabGrossesse, Long> {
}
