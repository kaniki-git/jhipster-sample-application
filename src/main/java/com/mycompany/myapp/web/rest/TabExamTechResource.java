package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabExamTech;
import com.mycompany.myapp.repository.TabExamTechRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabExamTech}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabExamTechResource {

    private final Logger log = LoggerFactory.getLogger(TabExamTechResource.class);

    private static final String ENTITY_NAME = "tabExamTech";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabExamTechRepository tabExamTechRepository;

    public TabExamTechResource(TabExamTechRepository tabExamTechRepository) {
        this.tabExamTechRepository = tabExamTechRepository;
    }

    /**
     * {@code POST  /tab-exam-teches} : Create a new tabExamTech.
     *
     * @param tabExamTech the tabExamTech to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabExamTech, or with status {@code 400 (Bad Request)} if the tabExamTech has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-exam-teches")
    public ResponseEntity<TabExamTech> createTabExamTech(@RequestBody TabExamTech tabExamTech) throws URISyntaxException {
        log.debug("REST request to save TabExamTech : {}", tabExamTech);
        if (tabExamTech.getId() != null) {
            throw new BadRequestAlertException("A new tabExamTech cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabExamTech result = tabExamTechRepository.save(tabExamTech);
        return ResponseEntity.created(new URI("/api/tab-exam-teches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-exam-teches} : Updates an existing tabExamTech.
     *
     * @param tabExamTech the tabExamTech to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabExamTech,
     * or with status {@code 400 (Bad Request)} if the tabExamTech is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabExamTech couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-exam-teches")
    public ResponseEntity<TabExamTech> updateTabExamTech(@RequestBody TabExamTech tabExamTech) throws URISyntaxException {
        log.debug("REST request to update TabExamTech : {}", tabExamTech);
        if (tabExamTech.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabExamTech result = tabExamTechRepository.save(tabExamTech);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabExamTech.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-exam-teches} : get all the tabExamTeches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabExamTeches in body.
     */
    @GetMapping("/tab-exam-teches")
    public List<TabExamTech> getAllTabExamTeches() {
        log.debug("REST request to get all TabExamTeches");
        return tabExamTechRepository.findAll();
    }

    /**
     * {@code GET  /tab-exam-teches/:id} : get the "id" tabExamTech.
     *
     * @param id the id of the tabExamTech to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabExamTech, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-exam-teches/{id}")
    public ResponseEntity<TabExamTech> getTabExamTech(@PathVariable Long id) {
        log.debug("REST request to get TabExamTech : {}", id);
        Optional<TabExamTech> tabExamTech = tabExamTechRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabExamTech);
    }

    /**
     * {@code DELETE  /tab-exam-teches/:id} : delete the "id" tabExamTech.
     *
     * @param id the id of the tabExamTech to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-exam-teches/{id}")
    public ResponseEntity<Void> deleteTabExamTech(@PathVariable Long id) {
        log.debug("REST request to delete TabExamTech : {}", id);
        tabExamTechRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
