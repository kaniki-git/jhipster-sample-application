package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabUserRepository extends JpaRepository<TabUser, Long> {
}
