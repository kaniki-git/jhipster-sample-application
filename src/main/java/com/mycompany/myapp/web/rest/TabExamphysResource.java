package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabExamphys;
import com.mycompany.myapp.repository.TabExamphysRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabExamphys}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabExamphysResource {

    private final Logger log = LoggerFactory.getLogger(TabExamphysResource.class);

    private static final String ENTITY_NAME = "tabExamphys";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabExamphysRepository tabExamphysRepository;

    public TabExamphysResource(TabExamphysRepository tabExamphysRepository) {
        this.tabExamphysRepository = tabExamphysRepository;
    }

    /**
     * {@code POST  /tab-examphys} : Create a new tabExamphys.
     *
     * @param tabExamphys the tabExamphys to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabExamphys, or with status {@code 400 (Bad Request)} if the tabExamphys has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-examphys")
    public ResponseEntity<TabExamphys> createTabExamphys(@RequestBody TabExamphys tabExamphys) throws URISyntaxException {
        log.debug("REST request to save TabExamphys : {}", tabExamphys);
        if (tabExamphys.getId() != null) {
            throw new BadRequestAlertException("A new tabExamphys cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabExamphys result = tabExamphysRepository.save(tabExamphys);
        return ResponseEntity.created(new URI("/api/tab-examphys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-examphys} : Updates an existing tabExamphys.
     *
     * @param tabExamphys the tabExamphys to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabExamphys,
     * or with status {@code 400 (Bad Request)} if the tabExamphys is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabExamphys couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-examphys")
    public ResponseEntity<TabExamphys> updateTabExamphys(@RequestBody TabExamphys tabExamphys) throws URISyntaxException {
        log.debug("REST request to update TabExamphys : {}", tabExamphys);
        if (tabExamphys.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabExamphys result = tabExamphysRepository.save(tabExamphys);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabExamphys.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-examphys} : get all the tabExamphys.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabExamphys in body.
     */
    @GetMapping("/tab-examphys")
    public List<TabExamphys> getAllTabExamphys() {
        log.debug("REST request to get all TabExamphys");
        return tabExamphysRepository.findAll();
    }

    /**
     * {@code GET  /tab-examphys/:id} : get the "id" tabExamphys.
     *
     * @param id the id of the tabExamphys to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabExamphys, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-examphys/{id}")
    public ResponseEntity<TabExamphys> getTabExamphys(@PathVariable Long id) {
        log.debug("REST request to get TabExamphys : {}", id);
        Optional<TabExamphys> tabExamphys = tabExamphysRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabExamphys);
    }

    /**
     * {@code DELETE  /tab-examphys/:id} : delete the "id" tabExamphys.
     *
     * @param id the id of the tabExamphys to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-examphys/{id}")
    public ResponseEntity<Void> deleteTabExamphys(@PathVariable Long id) {
        log.debug("REST request to delete TabExamphys : {}", id);
        tabExamphysRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
