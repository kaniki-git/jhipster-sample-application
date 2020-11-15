package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabParamvitaux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabParamvitaux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabParamvitauxRepository extends JpaRepository<TabParamvitaux, Long> {
}
