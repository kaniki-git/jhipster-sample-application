package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabAccouchement;
import com.mycompany.myapp.repository.TabAccouchementRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabAccouchement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabAccouchementResource {

    private final Logger log = LoggerFactory.getLogger(TabAccouchementResource.class);

    private static final String ENTITY_NAME = "tabAccouchement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabAccouchementRepository tabAccouchementRepository;

    public TabAccouchementResource(TabAccouchementRepository tabAccouchementRepository) {
        this.tabAccouchementRepository = tabAccouchementRepository;
    }

    /**
     * {@code POST  /tab-accouchements} : Create a new tabAccouchement.
     *
     * @param tabAccouchement the tabAccouchement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabAccouchement, or with status {@code 400 (Bad Request)} if the tabAccouchement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-accouchements")
    public ResponseEntity<TabAccouchement> createTabAccouchement(@RequestBody TabAccouchement tabAccouchement) throws URISyntaxException {
        log.debug("REST request to save TabAccouchement : {}", tabAccouchement);
        if (tabAccouchement.getId() != null) {
            throw new BadRequestAlertException("A new tabAccouchement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabAccouchement result = tabAccouchementRepository.save(tabAccouchement);
        return ResponseEntity.created(new URI("/api/tab-accouchements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-accouchements} : Updates an existing tabAccouchement.
     *
     * @param tabAccouchement the tabAccouchement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabAccouchement,
     * or with status {@code 400 (Bad Request)} if the tabAccouchement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabAccouchement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-accouchements")
    public ResponseEntity<TabAccouchement> updateTabAccouchement(@RequestBody TabAccouchement tabAccouchement) throws URISyntaxException {
        log.debug("REST request to update TabAccouchement : {}", tabAccouchement);
        if (tabAccouchement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabAccouchement result = tabAccouchementRepository.save(tabAccouchement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabAccouchement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-accouchements} : get all the tabAccouchements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabAccouchements in body.
     */
    @GetMapping("/tab-accouchements")
    public List<TabAccouchement> getAllTabAccouchements() {
        log.debug("REST request to get all TabAccouchements");
        return tabAccouchementRepository.findAll();
    }

    /**
     * {@code GET  /tab-accouchements/:id} : get the "id" tabAccouchement.
     *
     * @param id the id of the tabAccouchement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabAccouchement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-accouchements/{id}")
    public ResponseEntity<TabAccouchement> getTabAccouchement(@PathVariable Long id) {
        log.debug("REST request to get TabAccouchement : {}", id);
        Optional<TabAccouchement> tabAccouchement = tabAccouchementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabAccouchement);
    }

    /**
     * {@code DELETE  /tab-accouchements/:id} : delete the "id" tabAccouchement.
     *
     * @param id the id of the tabAccouchement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-accouchements/{id}")
    public ResponseEntity<Void> deleteTabAccouchement(@PathVariable Long id) {
        log.debug("REST request to delete TabAccouchement : {}", id);
        tabAccouchementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
