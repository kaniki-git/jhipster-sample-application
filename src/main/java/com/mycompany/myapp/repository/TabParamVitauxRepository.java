package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabParamVitaux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabParamVitaux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabParamVitauxRepository extends JpaRepository<TabParamVitaux, Long> {
}
