package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabPersonnel;
import com.mycompany.myapp.repository.TabPersonnelRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabPersonnel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabPersonnelResource {

    private final Logger log = LoggerFactory.getLogger(TabPersonnelResource.class);

    private static final String ENTITY_NAME = "tabPersonnel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabPersonnelRepository tabPersonnelRepository;

    public TabPersonnelResource(TabPersonnelRepository tabPersonnelRepository) {
        this.tabPersonnelRepository = tabPersonnelRepository;
    }

    /**
     * {@code POST  /tab-personnels} : Create a new tabPersonnel.
     *
     * @param tabPersonnel the tabPersonnel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabPersonnel, or with status {@code 400 (Bad Request)} if the tabPersonnel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-personnels")
    public ResponseEntity<TabPersonnel> createTabPersonnel(@RequestBody TabPersonnel tabPersonnel) throws URISyntaxException {
        log.debug("REST request to save TabPersonnel : {}", tabPersonnel);
        if (tabPersonnel.getId() != null) {
            throw new BadRequestAlertException("A new tabPersonnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabPersonnel result = tabPersonnelRepository.save(tabPersonnel);
        return ResponseEntity.created(new URI("/api/tab-personnels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-personnels} : Updates an existing tabPersonnel.
     *
     * @param tabPersonnel the tabPersonnel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabPersonnel,
     * or with status {@code 400 (Bad Request)} if the tabPersonnel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabPersonnel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-personnels")
    public ResponseEntity<TabPersonnel> updateTabPersonnel(@RequestBody TabPersonnel tabPersonnel) throws URISyntaxException {
        log.debug("REST request to update TabPersonnel : {}", tabPersonnel);
        if (tabPersonnel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabPersonnel result = tabPersonnelRepository.save(tabPersonnel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabPersonnel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-personnels} : get all the tabPersonnels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabPersonnels in body.
     */
    @GetMapping("/tab-personnels")
    public List<TabPersonnel> getAllTabPersonnels() {
        log.debug("REST request to get all TabPersonnels");
        return tabPersonnelRepository.findAll();
    }

    /**
     * {@code GET  /tab-personnels/:id} : get the "id" tabPersonnel.
     *
     * @param id the id of the tabPersonnel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabPersonnel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-personnels/{id}")
    public ResponseEntity<TabPersonnel> getTabPersonnel(@PathVariable Long id) {
        log.debug("REST request to get TabPersonnel : {}", id);
        Optional<TabPersonnel> tabPersonnel = tabPersonnelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabPersonnel);
    }

    /**
     * {@code DELETE  /tab-personnels/:id} : delete the "id" tabPersonnel.
     *
     * @param id the id of the tabPersonnel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-personnels/{id}")
    public ResponseEntity<Void> deleteTabPersonnel(@PathVariable Long id) {
        log.debug("REST request to delete TabPersonnel : {}", id);
        tabPersonnelRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
