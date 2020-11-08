package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabAdministratif;
import com.mycompany.myapp.repository.TabAdministratifRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabAdministratif}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabAdministratifResource {

    private final Logger log = LoggerFactory.getLogger(TabAdministratifResource.class);

    private static final String ENTITY_NAME = "tabAdministratif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabAdministratifRepository tabAdministratifRepository;

    public TabAdministratifResource(TabAdministratifRepository tabAdministratifRepository) {
        this.tabAdministratifRepository = tabAdministratifRepository;
    }

    /**
     * {@code POST  /tab-administratifs} : Create a new tabAdministratif.
     *
     * @param tabAdministratif the tabAdministratif to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabAdministratif, or with status {@code 400 (Bad Request)} if the tabAdministratif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-administratifs")
    public ResponseEntity<TabAdministratif> createTabAdministratif(@RequestBody TabAdministratif tabAdministratif) throws URISyntaxException {
        log.debug("REST request to save TabAdministratif : {}", tabAdministratif);
        if (tabAdministratif.getId() != null) {
            throw new BadRequestAlertException("A new tabAdministratif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabAdministratif result = tabAdministratifRepository.save(tabAdministratif);
        return ResponseEntity.created(new URI("/api/tab-administratifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-administratifs} : Updates an existing tabAdministratif.
     *
     * @param tabAdministratif the tabAdministratif to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabAdministratif,
     * or with status {@code 400 (Bad Request)} if the tabAdministratif is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabAdministratif couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-administratifs")
    public ResponseEntity<TabAdministratif> updateTabAdministratif(@RequestBody TabAdministratif tabAdministratif) throws URISyntaxException {
        log.debug("REST request to update TabAdministratif : {}", tabAdministratif);
        if (tabAdministratif.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabAdministratif result = tabAdministratifRepository.save(tabAdministratif);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabAdministratif.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-administratifs} : get all the tabAdministratifs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabAdministratifs in body.
     */
    @GetMapping("/tab-administratifs")
    public List<TabAdministratif> getAllTabAdministratifs() {
        log.debug("REST request to get all TabAdministratifs");
        return tabAdministratifRepository.findAll();
    }

    /**
     * {@code GET  /tab-administratifs/:id} : get the "id" tabAdministratif.
     *
     * @param id the id of the tabAdministratif to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabAdministratif, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-administratifs/{id}")
    public ResponseEntity<TabAdministratif> getTabAdministratif(@PathVariable Long id) {
        log.debug("REST request to get TabAdministratif : {}", id);
        Optional<TabAdministratif> tabAdministratif = tabAdministratifRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabAdministratif);
    }

    /**
     * {@code DELETE  /tab-administratifs/:id} : delete the "id" tabAdministratif.
     *
     * @param id the id of the tabAdministratif to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-administratifs/{id}")
    public ResponseEntity<Void> deleteTabAdministratif(@PathVariable Long id) {
        log.debug("REST request to delete TabAdministratif : {}", id);
        tabAdministratifRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
