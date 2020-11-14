package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabUrgence;
import com.mycompany.myapp.repository.TabUrgenceRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabUrgence}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabUrgenceResource {

    private final Logger log = LoggerFactory.getLogger(TabUrgenceResource.class);

    private static final String ENTITY_NAME = "tabUrgence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabUrgenceRepository tabUrgenceRepository;

    public TabUrgenceResource(TabUrgenceRepository tabUrgenceRepository) {
        this.tabUrgenceRepository = tabUrgenceRepository;
    }

    /**
     * {@code POST  /tab-urgences} : Create a new tabUrgence.
     *
     * @param tabUrgence the tabUrgence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabUrgence, or with status {@code 400 (Bad Request)} if the tabUrgence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-urgences")
    public ResponseEntity<TabUrgence> createTabUrgence(@RequestBody TabUrgence tabUrgence) throws URISyntaxException {
        log.debug("REST request to save TabUrgence : {}", tabUrgence);
        if (tabUrgence.getId() != null) {
            throw new BadRequestAlertException("A new tabUrgence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabUrgence result = tabUrgenceRepository.save(tabUrgence);
        return ResponseEntity.created(new URI("/api/tab-urgences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-urgences} : Updates an existing tabUrgence.
     *
     * @param tabUrgence the tabUrgence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabUrgence,
     * or with status {@code 400 (Bad Request)} if the tabUrgence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabUrgence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-urgences")
    public ResponseEntity<TabUrgence> updateTabUrgence(@RequestBody TabUrgence tabUrgence) throws URISyntaxException {
        log.debug("REST request to update TabUrgence : {}", tabUrgence);
        if (tabUrgence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabUrgence result = tabUrgenceRepository.save(tabUrgence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabUrgence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-urgences} : get all the tabUrgences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabUrgences in body.
     */
    @GetMapping("/tab-urgences")
    public List<TabUrgence> getAllTabUrgences() {
        log.debug("REST request to get all TabUrgences");
        return tabUrgenceRepository.findAll();
    }

    /**
     * {@code GET  /tab-urgences/:id} : get the "id" tabUrgence.
     *
     * @param id the id of the tabUrgence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabUrgence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-urgences/{id}")
    public ResponseEntity<TabUrgence> getTabUrgence(@PathVariable Long id) {
        log.debug("REST request to get TabUrgence : {}", id);
        Optional<TabUrgence> tabUrgence = tabUrgenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabUrgence);
    }

    /**
     * {@code DELETE  /tab-urgences/:id} : delete the "id" tabUrgence.
     *
     * @param id the id of the tabUrgence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-urgences/{id}")
    public ResponseEntity<Void> deleteTabUrgence(@PathVariable Long id) {
        log.debug("REST request to delete TabUrgence : {}", id);
        tabUrgenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
