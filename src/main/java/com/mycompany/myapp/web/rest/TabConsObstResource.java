package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabConsObst;
import com.mycompany.myapp.repository.TabConsObstRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TabConsObst}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabConsObstResource {

    private final Logger log = LoggerFactory.getLogger(TabConsObstResource.class);

    private static final String ENTITY_NAME = "tabConsObst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabConsObstRepository tabConsObstRepository;

    public TabConsObstResource(TabConsObstRepository tabConsObstRepository) {
        this.tabConsObstRepository = tabConsObstRepository;
    }

    /**
     * {@code POST  /tab-cons-obsts} : Create a new tabConsObst.
     *
     * @param tabConsObst the tabConsObst to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabConsObst, or with status {@code 400 (Bad Request)} if the tabConsObst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-cons-obsts")
    public ResponseEntity<TabConsObst> createTabConsObst(@RequestBody TabConsObst tabConsObst) throws URISyntaxException {
        log.debug("REST request to save TabConsObst : {}", tabConsObst);
        if (tabConsObst.getId() != null) {
            throw new BadRequestAlertException("A new tabConsObst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabConsObst result = tabConsObstRepository.save(tabConsObst);
        return ResponseEntity.created(new URI("/api/tab-cons-obsts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-cons-obsts} : Updates an existing tabConsObst.
     *
     * @param tabConsObst the tabConsObst to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabConsObst,
     * or with status {@code 400 (Bad Request)} if the tabConsObst is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabConsObst couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-cons-obsts")
    public ResponseEntity<TabConsObst> updateTabConsObst(@RequestBody TabConsObst tabConsObst) throws URISyntaxException {
        log.debug("REST request to update TabConsObst : {}", tabConsObst);
        if (tabConsObst.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabConsObst result = tabConsObstRepository.save(tabConsObst);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabConsObst.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-cons-obsts} : get all the tabConsObsts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabConsObsts in body.
     */
    @GetMapping("/tab-cons-obsts")
    public List<TabConsObst> getAllTabConsObsts() {
        log.debug("REST request to get all TabConsObsts");
        return tabConsObstRepository.findAll();
    }

    /**
     * {@code GET  /tab-cons-obsts/:id} : get the "id" tabConsObst.
     *
     * @param id the id of the tabConsObst to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabConsObst, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-cons-obsts/{id}")
    public ResponseEntity<TabConsObst> getTabConsObst(@PathVariable Long id) {
        log.debug("REST request to get TabConsObst : {}", id);
        Optional<TabConsObst> tabConsObst = tabConsObstRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabConsObst);
    }

    /**
     * {@code DELETE  /tab-cons-obsts/:id} : delete the "id" tabConsObst.
     *
     * @param id the id of the tabConsObst to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-cons-obsts/{id}")
    public ResponseEntity<Void> deleteTabConsObst(@PathVariable Long id) {
        log.debug("REST request to delete TabConsObst : {}", id);
        tabConsObstRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
