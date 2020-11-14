package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabSpecialite;
import com.mycompany.myapp.repository.TabSpecialiteRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabSpecialite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabSpecialiteResource {

    private final Logger log = LoggerFactory.getLogger(TabSpecialiteResource.class);

    private static final String ENTITY_NAME = "tabSpecialite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabSpecialiteRepository tabSpecialiteRepository;

    public TabSpecialiteResource(TabSpecialiteRepository tabSpecialiteRepository) {
        this.tabSpecialiteRepository = tabSpecialiteRepository;
    }

    /**
     * {@code POST  /tab-specialites} : Create a new tabSpecialite.
     *
     * @param tabSpecialite the tabSpecialite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabSpecialite, or with status {@code 400 (Bad Request)} if the tabSpecialite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-specialites")
    public ResponseEntity<TabSpecialite> createTabSpecialite(@RequestBody TabSpecialite tabSpecialite) throws URISyntaxException {
        log.debug("REST request to save TabSpecialite : {}", tabSpecialite);
        if (tabSpecialite.getId() != null) {
            throw new BadRequestAlertException("A new tabSpecialite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabSpecialite result = tabSpecialiteRepository.save(tabSpecialite);
        return ResponseEntity.created(new URI("/api/tab-specialites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-specialites} : Updates an existing tabSpecialite.
     *
     * @param tabSpecialite the tabSpecialite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabSpecialite,
     * or with status {@code 400 (Bad Request)} if the tabSpecialite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabSpecialite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-specialites")
    public ResponseEntity<TabSpecialite> updateTabSpecialite(@RequestBody TabSpecialite tabSpecialite) throws URISyntaxException {
        log.debug("REST request to update TabSpecialite : {}", tabSpecialite);
        if (tabSpecialite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabSpecialite result = tabSpecialiteRepository.save(tabSpecialite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabSpecialite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-specialites} : get all the tabSpecialites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabSpecialites in body.
     */
    @GetMapping("/tab-specialites")
    public List<TabSpecialite> getAllTabSpecialites() {
        log.debug("REST request to get all TabSpecialites");
        return tabSpecialiteRepository.findAll();
    }

    /**
     * {@code GET  /tab-specialites/:id} : get the "id" tabSpecialite.
     *
     * @param id the id of the tabSpecialite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabSpecialite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-specialites/{id}")
    public ResponseEntity<TabSpecialite> getTabSpecialite(@PathVariable Long id) {
        log.debug("REST request to get TabSpecialite : {}", id);
        Optional<TabSpecialite> tabSpecialite = tabSpecialiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabSpecialite);
    }

    /**
     * {@code DELETE  /tab-specialites/:id} : delete the "id" tabSpecialite.
     *
     * @param id the id of the tabSpecialite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-specialites/{id}")
    public ResponseEntity<Void> deleteTabSpecialite(@PathVariable Long id) {
        log.debug("REST request to delete TabSpecialite : {}", id);
        tabSpecialiteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
