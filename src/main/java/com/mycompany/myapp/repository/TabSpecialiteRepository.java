package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabSpecialite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabSpecialite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabSpecialiteRepository extends JpaRepository<TabSpecialite, Long> {
}
