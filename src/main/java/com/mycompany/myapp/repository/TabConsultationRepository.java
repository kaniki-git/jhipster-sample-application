package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabConsultation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabConsultation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabConsultationRepository extends JpaRepository<TabConsultation, Long> {
}
