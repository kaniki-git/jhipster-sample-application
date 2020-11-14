package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabComptabilite;
import com.mycompany.myapp.repository.TabComptabiliteRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabComptabilite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabComptabiliteResource {

    private final Logger log = LoggerFactory.getLogger(TabComptabiliteResource.class);

    private static final String ENTITY_NAME = "tabComptabilite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabComptabiliteRepository tabComptabiliteRepository;

    public TabComptabiliteResource(TabComptabiliteRepository tabComptabiliteRepository) {
        this.tabComptabiliteRepository = tabComptabiliteRepository;
    }

    /**
     * {@code POST  /tab-comptabilites} : Create a new tabComptabilite.
     *
     * @param tabComptabilite the tabComptabilite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabComptabilite, or with status {@code 400 (Bad Request)} if the tabComptabilite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-comptabilites")
    public ResponseEntity<TabComptabilite> createTabComptabilite(@RequestBody TabComptabilite tabComptabilite) throws URISyntaxException {
        log.debug("REST request to save TabComptabilite : {}", tabComptabilite);
        if (tabComptabilite.getId() != null) {
            throw new BadRequestAlertException("A new tabComptabilite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabComptabilite result = tabComptabiliteRepository.save(tabComptabilite);
        return ResponseEntity.created(new URI("/api/tab-comptabilites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-comptabilites} : Updates an existing tabComptabilite.
     *
     * @param tabComptabilite the tabComptabilite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabComptabilite,
     * or with status {@code 400 (Bad Request)} if the tabComptabilite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabComptabilite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-comptabilites")
    public ResponseEntity<TabComptabilite> updateTabComptabilite(@RequestBody TabComptabilite tabComptabilite) throws URISyntaxException {
        log.debug("REST request to update TabComptabilite : {}", tabComptabilite);
        if (tabComptabilite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabComptabilite result = tabComptabiliteRepository.save(tabComptabilite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabComptabilite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-comptabilites} : get all the tabComptabilites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabComptabilites in body.
     */
    @GetMapping("/tab-comptabilites")
    public List<TabComptabilite> getAllTabComptabilites() {
        log.debug("REST request to get all TabComptabilites");
        return tabComptabiliteRepository.findAll();
    }

    /**
     * {@code GET  /tab-comptabilites/:id} : get the "id" tabComptabilite.
     *
     * @param id the id of the tabComptabilite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabComptabilite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-comptabilites/{id}")
    public ResponseEntity<TabComptabilite> getTabComptabilite(@PathVariable Long id) {
        log.debug("REST request to get TabComptabilite : {}", id);
        Optional<TabComptabilite> tabComptabilite = tabComptabiliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabComptabilite);
    }

    /**
     * {@code DELETE  /tab-comptabilites/:id} : delete the "id" tabComptabilite.
     *
     * @param id the id of the tabComptabilite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-comptabilites/{id}")
    public ResponseEntity<Void> deleteTabComptabilite(@PathVariable Long id) {
        log.debug("REST request to delete TabComptabilite : {}", id);
        tabComptabiliteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
