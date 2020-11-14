package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabPersonnel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabPersonnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabPersonnelRepository extends JpaRepository<TabPersonnel, Long> {
}
