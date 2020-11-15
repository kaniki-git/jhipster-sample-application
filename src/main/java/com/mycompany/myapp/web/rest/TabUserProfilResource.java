package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabUserProfil;
import com.mycompany.myapp.repository.TabUserProfilRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabUserProfil}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabUserProfilResource {

    private final Logger log = LoggerFactory.getLogger(TabUserProfilResource.class);

    private static final String ENTITY_NAME = "tabUserProfil";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabUserProfilRepository tabUserProfilRepository;

    public TabUserProfilResource(TabUserProfilRepository tabUserProfilRepository) {
        this.tabUserProfilRepository = tabUserProfilRepository;
    }

    /**
     * {@code POST  /tab-user-profils} : Create a new tabUserProfil.
     *
     * @param tabUserProfil the tabUserProfil to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabUserProfil, or with status {@code 400 (Bad Request)} if the tabUserProfil has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-user-profils")
    public ResponseEntity<TabUserProfil> createTabUserProfil(@RequestBody TabUserProfil tabUserProfil) throws URISyntaxException {
        log.debug("REST request to save TabUserProfil : {}", tabUserProfil);
        if (tabUserProfil.getId() != null) {
            throw new BadRequestAlertException("A new tabUserProfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabUserProfil result = tabUserProfilRepository.save(tabUserProfil);
        return ResponseEntity.created(new URI("/api/tab-user-profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-user-profils} : Updates an existing tabUserProfil.
     *
     * @param tabUserProfil the tabUserProfil to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabUserProfil,
     * or with status {@code 400 (Bad Request)} if the tabUserProfil is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabUserProfil couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-user-profils")
    public ResponseEntity<TabUserProfil> updateTabUserProfil(@RequestBody TabUserProfil tabUserProfil) throws URISyntaxException {
        log.debug("REST request to update TabUserProfil : {}", tabUserProfil);
        if (tabUserProfil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabUserProfil result = tabUserProfilRepository.save(tabUserProfil);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabUserProfil.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-user-profils} : get all the tabUserProfils.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabUserProfils in body.
     */
    @GetMapping("/tab-user-profils")
    public List<TabUserProfil> getAllTabUserProfils() {
        log.debug("REST request to get all TabUserProfils");
        return tabUserProfilRepository.findAll();
    }

    /**
     * {@code GET  /tab-user-profils/:id} : get the "id" tabUserProfil.
     *
     * @param id the id of the tabUserProfil to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabUserProfil, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-user-profils/{id}")
    public ResponseEntity<TabUserProfil> getTabUserProfil(@PathVariable Long id) {
        log.debug("REST request to get TabUserProfil : {}", id);
        Optional<TabUserProfil> tabUserProfil = tabUserProfilRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabUserProfil);
    }

    /**
     * {@code DELETE  /tab-user-profils/:id} : delete the "id" tabUserProfil.
     *
     * @param id the id of the tabUserProfil to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-user-profils/{id}")
    public ResponseEntity<Void> deleteTabUserProfil(@PathVariable Long id) {
        log.debug("REST request to delete TabUserProfil : {}", id);
        tabUserProfilRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
