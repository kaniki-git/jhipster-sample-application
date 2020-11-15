package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabRefPays;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabRefPays entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabRefPaysRepository extends JpaRepository<TabRefPays, Long> {
}
