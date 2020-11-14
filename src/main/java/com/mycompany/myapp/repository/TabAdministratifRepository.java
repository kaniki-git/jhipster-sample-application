package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabAdministratif;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabAdministratif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabAdministratifRepository extends JpaRepository<TabAdministratif, Long> {
}
