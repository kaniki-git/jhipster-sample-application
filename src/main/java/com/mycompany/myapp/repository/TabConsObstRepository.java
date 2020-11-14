package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabConsObst;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabConsObst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabConsObstRepository extends JpaRepository<TabConsObst, Long> {
}
