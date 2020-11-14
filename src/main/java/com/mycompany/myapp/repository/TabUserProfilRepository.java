package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabUserProfil;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabUserProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabUserProfilRepository extends JpaRepository<TabUserProfil, Long> {
}
