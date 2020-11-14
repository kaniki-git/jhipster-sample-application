package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabComptabilite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabComptabilite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabComptabiliteRepository extends JpaRepository<TabComptabilite, Long> {
}
