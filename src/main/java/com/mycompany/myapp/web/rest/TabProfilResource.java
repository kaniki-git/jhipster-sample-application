package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabProfil;
import com.mycompany.myapp.repository.TabProfilRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabProfil}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabProfilResource {

    private final Logger log = LoggerFactory.getLogger(TabProfilResource.class);

    private static final String ENTITY_NAME = "tabProfil";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabProfilRepository tabProfilRepository;

    public TabProfilResource(TabProfilRepository tabProfilRepository) {
        this.tabProfilRepository = tabProfilRepository;
    }

    /**
     * {@code POST  /tab-profils} : Create a new tabProfil.
     *
     * @param tabProfil the tabProfil to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabProfil, or with status {@code 400 (Bad Request)} if the tabProfil has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-profils")
    public ResponseEntity<TabProfil> createTabProfil(@RequestBody TabProfil tabProfil) throws URISyntaxException {
        log.debug("REST request to save TabProfil : {}", tabProfil);
        if (tabProfil.getId() != null) {
            throw new BadRequestAlertException("A new tabProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabProfil result = tabProfilRepository.save(tabProfil);
        return ResponseEntity.created(new URI("/api/tab-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-profils} : Updates an existing tabProfil.
     *
     * @param tabProfil the tabProfil to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabProfil,
     * or with status {@code 400 (Bad Request)} if the tabProfil is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabProfil couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-profils")
    public ResponseEntity<TabProfil> updateTabProfil(@RequestBody TabProfil tabProfil) throws URISyntaxException {
        log.debug("REST request to update TabProfil : {}", tabProfil);
        if (tabProfil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabProfil result = tabProfilRepository.save(tabProfil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabProfil.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-profils} : get all the tabProfils.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabProfils in body.
     */
    @GetMapping("/tab-profils")
    public List<TabProfil> getAllTabProfils() {
        log.debug("REST request to get all TabProfils");
        return tabProfilRepository.findAll();
    }

    /**
     * {@code GET  /tab-profils/:id} : get the "id" tabProfil.
     *
     * @param id the id of the tabProfil to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabProfil, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-profils/{id}")
    public ResponseEntity<TabProfil> getTabProfil(@PathVariable Long id) {
        log.debug("REST request to get TabProfil : {}", id);
        Optional<TabProfil> tabProfil = tabProfilRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabProfil);
    }

    /**
     * {@code DELETE  /tab-profils/:id} : delete the "id" tabProfil.
     *
     * @param id the id of the tabProfil to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-profils/{id}")
    public ResponseEntity<Void> deleteTabProfil(@PathVariable Long id) {
        log.debug("REST request to delete TabProfil : {}", id);
        tabProfilRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
