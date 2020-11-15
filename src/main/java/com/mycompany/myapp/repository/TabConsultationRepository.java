package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabConsultation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TabConsultation entity.
 */
@Repository
public interface TabConsultationRepository extends JpaRepository<TabConsultation, Long> {

    @Query(value = "select distinct tabConsultation from TabConsultation tabConsultation left join fetch tabConsultation.tabPersonnels",
        countQuery = "select count(distinct tabConsultation) from TabConsultation tabConsultation")
    Page<TabConsultation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct tabConsultation from TabConsultation tabConsultation left join fetch tabConsultation.tabPersonnels")
    List<TabConsultation> findAllWithEagerRelationships();

    @Query("select tabConsultation from TabConsultation tabConsultation left join fetch tabConsultation.tabPersonnels where tabConsultation.id =:id")
    Optional<TabConsultation> findOneWithEagerRelationships(@Param("id") Long id);
}
