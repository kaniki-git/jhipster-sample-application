package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabConsobst;
import com.mycompany.myapp.repository.TabConsobstRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabConsobst}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabConsobstResource {

    private final Logger log = LoggerFactory.getLogger(TabConsobstResource.class);

    private static final String ENTITY_NAME = "tabConsobst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabConsobstRepository tabConsobstRepository;

    public TabConsobstResource(TabConsobstRepository tabConsobstRepository) {
        this.tabConsobstRepository = tabConsobstRepository;
    }

    /**
     * {@code POST  /tab-consobsts} : Create a new tabConsobst.
     *
     * @param tabConsobst the tabConsobst to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabConsobst, or with status {@code 400 (Bad Request)} if the tabConsobst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-consobsts")
    public ResponseEntity<TabConsobst> createTabConsobst(@RequestBody TabConsobst tabConsobst) throws URISyntaxException {
        log.debug("REST request to save TabConsobst : {}", tabConsobst);
        if (tabConsobst.getId() != null) {
            throw new BadRequestAlertException("A new tabConsobst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabConsobst result = tabConsobstRepository.save(tabConsobst);
        return ResponseEntity.created(new URI("/api/tab-consobsts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-consobsts} : Updates an existing tabConsobst.
     *
     * @param tabConsobst the tabConsobst to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabConsobst,
     * or with status {@code 400 (Bad Request)} if the tabConsobst is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabConsobst couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-consobsts")
    public ResponseEntity<TabConsobst> updateTabConsobst(@RequestBody TabConsobst tabConsobst) throws URISyntaxException {
        log.debug("REST request to update TabConsobst : {}", tabConsobst);
        if (tabConsobst.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabConsobst result = tabConsobstRepository.save(tabConsobst);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabConsobst.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-consobsts} : get all the tabConsobsts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabConsobsts in body.
     */
    @GetMapping("/tab-consobsts")
    public List<TabConsobst> getAllTabConsobsts() {
        log.debug("REST request to get all TabConsobsts");
        return tabConsobstRepository.findAll();
    }

    /**
     * {@code GET  /tab-consobsts/:id} : get the "id" tabConsobst.
     *
     * @param id the id of the tabConsobst to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabConsobst, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-consobsts/{id}")
    public ResponseEntity<TabConsobst> getTabConsobst(@PathVariable Long id) {
        log.debug("REST request to get TabConsobst : {}", id);
        Optional<TabConsobst> tabConsobst = tabConsobstRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabConsobst);
    }

    /**
     * {@code DELETE  /tab-consobsts/:id} : delete the "id" tabConsobst.
     *
     * @param id the id of the tabConsobst to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-consobsts/{id}")
    public ResponseEntity<Void> deleteTabConsobst(@PathVariable Long id) {
        log.debug("REST request to delete TabConsobst : {}", id);
        tabConsobstRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
