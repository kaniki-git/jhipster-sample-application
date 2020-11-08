package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabAccouchement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TabAccouchement entity.
 */
@Repository
public interface TabAccouchementRepository extends JpaRepository<TabAccouchement, Long> {

    @Query(value = "select distinct tabAccouchement from TabAccouchement tabAccouchement left join fetch tabAccouchement.tabPersonnels",
        countQuery = "select count(distinct tabAccouchement) from TabAccouchement tabAccouchement")
    Page<TabAccouchement> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct tabAccouchement from TabAccouchement tabAccouchement left join fetch tabAccouchement.tabPersonnels")
    List<TabAccouchement> findAllWithEagerRelationships();

    @Query("select tabAccouchement from TabAccouchement tabAccouchement left join fetch tabAccouchement.tabPersonnels where tabAccouchement.id =:id")
    Optional<TabAccouchement> findOneWithEagerRelationships(@Param("id") Long id);
}
