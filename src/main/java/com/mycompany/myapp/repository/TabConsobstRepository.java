package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabConsobst;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabConsobst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabConsobstRepository extends JpaRepository<TabConsobst, Long> {
}
