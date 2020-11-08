package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TabRendezvous;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TabRendezvous entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TabRendezvousRepository extends JpaRepository<TabRendezvous, Long> {
}
