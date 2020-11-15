package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabPatient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabPatientRepository extends JpaRepository<TabPatient, Long> {
}
