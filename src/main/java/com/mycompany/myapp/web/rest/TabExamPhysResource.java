package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabExamPhys;
import com.mycompany.myapp.repository.TabExamPhysRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabExamPhys}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabExamPhysResource {

    private final Logger log = LoggerFactory.getLogger(TabExamPhysResource.class);

    private static final String ENTITY_NAME = "tabExamPhys";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabExamPhysRepository tabExamPhysRepository;

    public TabExamPhysResource(TabExamPhysRepository tabExamPhysRepository) {
        this.tabExamPhysRepository = tabExamPhysRepository;
    }

    /**
     * {@code POST  /tab-exam-phys} : Create a new tabExamPhys.
     *
     * @param tabExamPhys the tabExamPhys to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabExamPhys, or with status {@code 400 (Bad Request)} if the tabExamPhys has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-exam-phys")
    public ResponseEntity<TabExamPhys> createTabExamPhys(@RequestBody TabExamPhys tabExamPhys) throws URISyntaxException {
        log.debug("REST request to save TabExamPhys : {}", tabExamPhys);
        if (tabExamPhys.getId() != null) {
            throw new BadRequestAlertException("A new tabExamPhys cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabExamPhys result = tabExamPhysRepository.save(tabExamPhys);
        return ResponseEntity.created(new URI("/api/tab-exam-phys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-exam-phys} : Updates an existing tabExamPhys.
     *
     * @param tabExamPhys the tabExamPhys to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabExamPhys,
     * or with status {@code 400 (Bad Request)} if the tabExamPhys is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabExamPhys couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-exam-phys")
    public ResponseEntity<TabExamPhys> updateTabExamPhys(@RequestBody TabExamPhys tabExamPhys) throws URISyntaxException {
        log.debug("REST request to update TabExamPhys : {}", tabExamPhys);
        if (tabExamPhys.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabExamPhys result = tabExamPhysRepository.save(tabExamPhys);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabExamPhys.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-exam-phys} : get all the tabExamPhys.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabExamPhys in body.
     */
    @GetMapping("/tab-exam-phys")
    public List<TabExamPhys> getAllTabExamPhys() {
        log.debug("REST request to get all TabExamPhys");
        return tabExamPhysRepository.findAll();
    }

    /**
     * {@code GET  /tab-exam-phys/:id} : get the "id" tabExamPhys.
     *
     * @param id the id of the tabExamPhys to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabExamPhys, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-exam-phys/{id}")
    public ResponseEntity<TabExamPhys> getTabExamPhys(@PathVariable Long id) {
        log.debug("REST request to get TabExamPhys : {}", id);
        Optional<TabExamPhys> tabExamPhys = tabExamPhysRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabExamPhys);
    }

    /**
     * {@code DELETE  /tab-exam-phys/:id} : delete the "id" tabExamPhys.
     *
     * @param id the id of the tabExamPhys to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-exam-phys/{id}")
    public ResponseEntity<Void> deleteTabExamPhys(@PathVariable Long id) {
        log.debug("REST request to delete TabExamPhys : {}", id);
        tabExamPhysRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
