package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabRefFonction;
import com.mycompany.myapp.repository.TabRefFonctionRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabRefFonction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabRefFonctionResource {

    private final Logger log = LoggerFactory.getLogger(TabRefFonctionResource.class);

    private static final String ENTITY_NAME = "tabRefFonction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabRefFonctionRepository tabRefFonctionRepository;

    public TabRefFonctionResource(TabRefFonctionRepository tabRefFonctionRepository) {
        this.tabRefFonctionRepository = tabRefFonctionRepository;
    }

    /**
     * {@code POST  /tab-ref-fonctions} : Create a new tabRefFonction.
     *
     * @param tabRefFonction the tabRefFonction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabRefFonction, or with status {@code 400 (Bad Request)} if the tabRefFonction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-ref-fonctions")
    public ResponseEntity<TabRefFonction> createTabRefFonction(@RequestBody TabRefFonction tabRefFonction) throws URISyntaxException {
        log.debug("REST request to save TabRefFonction : {}", tabRefFonction);
        if (tabRefFonction.getId() != null) {
            throw new BadRequestAlertException("A new tabRefFonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabRefFonction result = tabRefFonctionRepository.save(tabRefFonction);
        return ResponseEntity.created(new URI("/api/tab-ref-fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-ref-fonctions} : Updates an existing tabRefFonction.
     *
     * @param tabRefFonction the tabRefFonction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabRefFonction,
     * or with status {@code 400 (Bad Request)} if the tabRefFonction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabRefFonction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-ref-fonctions")
    public ResponseEntity<TabRefFonction> updateTabRefFonction(@RequestBody TabRefFonction tabRefFonction) throws URISyntaxException {
        log.debug("REST request to update TabRefFonction : {}", tabRefFonction);
        if (tabRefFonction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabRefFonction result = tabRefFonctionRepository.save(tabRefFonction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabRefFonction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-ref-fonctions} : get all the tabRefFonctions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabRefFonctions in body.
     */
    @GetMapping("/tab-ref-fonctions")
    public List<TabRefFonction> getAllTabRefFonctions() {
        log.debug("REST request to get all TabRefFonctions");
        return tabRefFonctionRepository.findAll();
    }

    /**
     * {@code GET  /tab-ref-fonctions/:id} : get the "id" tabRefFonction.
     *
     * @param id the id of the tabRefFonction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabRefFonction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-ref-fonctions/{id}")
    public ResponseEntity<TabRefFonction> getTabRefFonction(@PathVariable Long id) {
        log.debug("REST request to get TabRefFonction : {}", id);
        Optional<TabRefFonction> tabRefFonction = tabRefFonctionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabRefFonction);
    }

    /**
     * {@code DELETE  /tab-ref-fonctions/:id} : delete the "id" tabRefFonction.
     *
     * @param id the id of the tabRefFonction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-ref-fonctions/{id}")
    public ResponseEntity<Void> deleteTabRefFonction(@PathVariable Long id) {
        log.debug("REST request to delete TabRefFonction : {}", id);
        tabRefFonctionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
