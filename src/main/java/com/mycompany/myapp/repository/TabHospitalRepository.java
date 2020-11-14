package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabHospital;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TabHospital entity.
 */
@Repository
public interface TabHospitalRepository extends JpaRepository<TabHospital, Long> {

    @Query(value = "select distinct tabHospital from TabHospital tabHospital left join fetch tabHospital.tabPersonnels",
        countQuery = "select count(distinct tabHospital) from TabHospital tabHospital")
    Page<TabHospital> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct tabHospital from TabHospital tabHospital left join fetch tabHospital.tabPersonnels")
    List<TabHospital> findAllWithEagerRelationships();

    @Query("select tabHospital from TabHospital tabHospital left join fetch tabHospital.tabPersonnels where tabHospital.id =:id")
    Optional<TabHospital> findOneWithEagerRelationships(@Param("id") Long id);
}
