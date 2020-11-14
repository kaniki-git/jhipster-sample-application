package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabParamVitaux;
import com.mycompany.myapp.repository.TabParamVitauxRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabParamVitaux}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabParamVitauxResource {

    private final Logger log = LoggerFactory.getLogger(TabParamVitauxResource.class);

    private static final String ENTITY_NAME = "tabParamVitaux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabParamVitauxRepository tabParamVitauxRepository;

    public TabParamVitauxResource(TabParamVitauxRepository tabParamVitauxRepository) {
        this.tabParamVitauxRepository = tabParamVitauxRepository;
    }

    /**
     * {@code POST  /tab-param-vitauxes} : Create a new tabParamVitaux.
     *
     * @param tabParamVitaux the tabParamVitaux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabParamVitaux, or with status {@code 400 (Bad Request)} if the tabParamVitaux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-param-vitauxes")
    public ResponseEntity<TabParamVitaux> createTabParamVitaux(@RequestBody TabParamVitaux tabParamVitaux) throws URISyntaxException {
        log.debug("REST request to save TabParamVitaux : {}", tabParamVitaux);
        if (tabParamVitaux.getId() != null) {
            throw new BadRequestAlertException("A new tabParamVitaux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabParamVitaux result = tabParamVitauxRepository.save(tabParamVitaux);
        return ResponseEntity.created(new URI("/api/tab-param-vitauxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-param-vitauxes} : Updates an existing tabParamVitaux.
     *
     * @param tabParamVitaux the tabParamVitaux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabParamVitaux,
     * or with status {@code 400 (Bad Request)} if the tabParamVitaux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabParamVitaux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-param-vitauxes")
    public ResponseEntity<TabParamVitaux> updateTabParamVitaux(@RequestBody TabParamVitaux tabParamVitaux) throws URISyntaxException {
        log.debug("REST request to update TabParamVitaux : {}", tabParamVitaux);
        if (tabParamVitaux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabParamVitaux result = tabParamVitauxRepository.save(tabParamVitaux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabParamVitaux.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-param-vitauxes} : get all the tabParamVitauxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabParamVitauxes in body.
     */
    @GetMapping("/tab-param-vitauxes")
    public List<TabParamVitaux> getAllTabParamVitauxes() {
        log.debug("REST request to get all TabParamVitauxes");
        return tabParamVitauxRepository.findAll();
    }

    /**
     * {@code GET  /tab-param-vitauxes/:id} : get the "id" tabParamVitaux.
     *
     * @param id the id of the tabParamVitaux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabParamVitaux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-param-vitauxes/{id}")
    public ResponseEntity<TabParamVitaux> getTabParamVitaux(@PathVariable Long id) {
        log.debug("REST request to get TabParamVitaux : {}", id);
        Optional<TabParamVitaux> tabParamVitaux = tabParamVitauxRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabParamVitaux);
    }

    /**
     * {@code DELETE  /tab-param-vitauxes/:id} : delete the "id" tabParamVitaux.
     *
     * @param id the id of the tabParamVitaux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-param-vitauxes/{id}")
    public ResponseEntity<Void> deleteTabParamVitaux(@PathVariable Long id) {
        log.debug("REST request to delete TabParamVitaux : {}", id);
        tabParamVitauxRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
