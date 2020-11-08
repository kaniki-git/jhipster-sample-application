package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabProfil;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabProfilRepository extends JpaRepository<TabProfil, Long> {
}
