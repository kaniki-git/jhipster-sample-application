package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabCoordonnee;
import com.mycompany.myapp.repository.TabCoordonneeRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabCoordonnee}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabCoordonneeResource {

    private final Logger log = LoggerFactory.getLogger(TabCoordonneeResource.class);

    private static final String ENTITY_NAME = "tabCoordonnee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabCoordonneeRepository tabCoordonneeRepository;

    public TabCoordonneeResource(TabCoordonneeRepository tabCoordonneeRepository) {
        this.tabCoordonneeRepository = tabCoordonneeRepository;
    }

    /**
     * {@code POST  /tab-coordonnees} : Create a new tabCoordonnee.
     *
     * @param tabCoordonnee the tabCoordonnee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabCoordonnee, or with status {@code 400 (Bad Request)} if the tabCoordonnee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-coordonnees")
    public ResponseEntity<TabCoordonnee> createTabCoordonnee(@RequestBody TabCoordonnee tabCoordonnee) throws URISyntaxException {
        log.debug("REST request to save TabCoordonnee : {}", tabCoordonnee);
        if (tabCoordonnee.getId() != null) {
            throw new BadRequestAlertException("A new tabCoordonnee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabCoordonnee result = tabCoordonneeRepository.save(tabCoordonnee);
        return ResponseEntity.created(new URI("/api/tab-coordonnees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-coordonnees} : Updates an existing tabCoordonnee.
     *
     * @param tabCoordonnee the tabCoordonnee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabCoordonnee,
     * or with status {@code 400 (Bad Request)} if the tabCoordonnee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabCoordonnee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-coordonnees")
    public ResponseEntity<TabCoordonnee> updateTabCoordonnee(@RequestBody TabCoordonnee tabCoordonnee) throws URISyntaxException {
        log.debug("REST request to update TabCoordonnee : {}", tabCoordonnee);
        if (tabCoordonnee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabCoordonnee result = tabCoordonneeRepository.save(tabCoordonnee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabCoordonnee.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-coordonnees} : get all the tabCoordonnees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabCoordonnees in body.
     */
    @GetMapping("/tab-coordonnees")
    public List<TabCoordonnee> getAllTabCoordonnees() {
        log.debug("REST request to get all TabCoordonnees");
        return tabCoordonneeRepository.findAll();
    }

    /**
     * {@code GET  /tab-coordonnees/:id} : get the "id" tabCoordonnee.
     *
     * @param id the id of the tabCoordonnee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabCoordonnee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-coordonnees/{id}")
    public ResponseEntity<TabCoordonnee> getTabCoordonnee(@PathVariable Long id) {
        log.debug("REST request to get TabCoordonnee : {}", id);
        Optional<TabCoordonnee> tabCoordonnee = tabCoordonneeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabCoordonnee);
    }

    /**
     * {@code DELETE  /tab-coordonnees/:id} : delete the "id" tabCoordonnee.
     *
     * @param id the id of the tabCoordonnee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-coordonnees/{id}")
    public ResponseEntity<Void> deleteTabCoordonnee(@PathVariable Long id) {
        log.debug("REST request to delete TabCoordonnee : {}", id);
        tabCoordonneeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
