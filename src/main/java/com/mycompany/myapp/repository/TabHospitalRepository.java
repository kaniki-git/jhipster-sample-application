package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabHospital;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabHospital entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabHospitalRepository extends JpaRepository<TabHospital, Long> {
}
