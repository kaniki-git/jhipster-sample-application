package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabHistoriquePatient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabHistoriquePatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabHistoriquePatientRepository extends JpaRepository<TabHistoriquePatient, Long> {
}
