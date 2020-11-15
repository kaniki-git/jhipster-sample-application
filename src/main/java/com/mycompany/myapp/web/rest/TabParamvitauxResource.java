package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabParamvitaux;
import com.mycompany.myapp.repository.TabParamvitauxRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabParamvitaux}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabParamvitauxResource {

    private final Logger log = LoggerFactory.getLogger(TabParamvitauxResource.class);

    private static final String ENTITY_NAME = "tabParamvitaux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabParamvitauxRepository tabParamvitauxRepository;

    public TabParamvitauxResource(TabParamvitauxRepository tabParamvitauxRepository) {
        this.tabParamvitauxRepository = tabParamvitauxRepository;
    }

    /**
     * {@code POST  /tab-paramvitauxes} : Create a new tabParamvitaux.
     *
     * @param tabParamvitaux the tabParamvitaux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabParamvitaux, or with status {@code 400 (Bad Request)} if the tabParamvitaux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-paramvitauxes")
    public ResponseEntity<TabParamvitaux> createTabParamvitaux(@RequestBody TabParamvitaux tabParamvitaux) throws URISyntaxException {
        log.debug("REST request to save TabParamvitaux : {}", tabParamvitaux);
        if (tabParamvitaux.getId() != null) {
            throw new BadRequestAlertException("A new tabParamvitaux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabParamvitaux result = tabParamvitauxRepository.save(tabParamvitaux);
        return ResponseEntity.created(new URI("/api/tab-paramvitauxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-paramvitauxes} : Updates an existing tabParamvitaux.
     *
     * @param tabParamvitaux the tabParamvitaux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabParamvitaux,
     * or with status {@code 400 (Bad Request)} if the tabParamvitaux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabParamvitaux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-paramvitauxes")
    public ResponseEntity<TabParamvitaux> updateTabParamvitaux(@RequestBody TabParamvitaux tabParamvitaux) throws URISyntaxException {
        log.debug("REST request to update TabParamvitaux : {}", tabParamvitaux);
        if (tabParamvitaux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabParamvitaux result = tabParamvitauxRepository.save(tabParamvitaux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabParamvitaux.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-paramvitauxes} : get all the tabParamvitauxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabParamvitauxes in body.
     */
    @GetMapping("/tab-paramvitauxes")
    public List<TabParamvitaux> getAllTabParamvitauxes() {
        log.debug("REST request to get all TabParamvitauxes");
        return tabParamvitauxRepository.findAll();
    }

    /**
     * {@code GET  /tab-paramvitauxes/:id} : get the "id" tabParamvitaux.
     *
     * @param id the id of the tabParamvitaux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabParamvitaux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-paramvitauxes/{id}")
    public ResponseEntity<TabParamvitaux> getTabParamvitaux(@PathVariable Long id) {
        log.debug("REST request to get TabParamvitaux : {}", id);
        Optional<TabParamvitaux> tabParamvitaux = tabParamvitauxRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabParamvitaux);
    }

    /**
     * {@code DELETE  /tab-paramvitauxes/:id} : delete the "id" tabParamvitaux.
     *
     * @param id the id of the tabParamvitaux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-paramvitauxes/{id}")
    public ResponseEntity<Void> deleteTabParamvitaux(@PathVariable Long id) {
        log.debug("REST request to delete TabParamvitaux : {}", id);
        tabParamvitauxRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
