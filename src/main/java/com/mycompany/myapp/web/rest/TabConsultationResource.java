package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabConsultation;
import com.mycompany.myapp.repository.TabConsultationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabConsultation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabConsultationResource {

    private final Logger log = LoggerFactory.getLogger(TabConsultationResource.class);

    private static final String ENTITY_NAME = "tabConsultation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabConsultationRepository tabConsultationRepository;

    public TabConsultationResource(TabConsultationRepository tabConsultationRepository) {
        this.tabConsultationRepository = tabConsultationRepository;
    }

    /**
     * {@code POST  /tab-consultations} : Create a new tabConsultation.
     *
     * @param tabConsultation the tabConsultation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabConsultation, or with status {@code 400 (Bad Request)} if the tabConsultation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-consultations")
    public ResponseEntity<TabConsultation> createTabConsultation(@RequestBody TabConsultation tabConsultation) throws URISyntaxException {
        log.debug("REST request to save TabConsultation : {}", tabConsultation);
        if (tabConsultation.getId() != null) {
            throw new BadRequestAlertException("A new tabConsultation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabConsultation result = tabConsultationRepository.save(tabConsultation);
        return ResponseEntity.created(new URI("/api/tab-consultations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-consultations} : Updates an existing tabConsultation.
     *
     * @param tabConsultation the tabConsultation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabConsultation,
     * or with status {@code 400 (Bad Request)} if the tabConsultation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabConsultation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-consultations")
    public ResponseEntity<TabConsultation> updateTabConsultation(@RequestBody TabConsultation tabConsultation) throws URISyntaxException {
        log.debug("REST request to update TabConsultation : {}", tabConsultation);
        if (tabConsultation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabConsultation result = tabConsultationRepository.save(tabConsultation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabConsultation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-consultations} : get all the tabConsultations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabConsultations in body.
     */
    @GetMapping("/tab-consultations")
    public List<TabConsultation> getAllTabConsultations(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all TabConsultations");
        return tabConsultationRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /tab-consultations/:id} : get the "id" tabConsultation.
     *
     * @param id the id of the tabConsultation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabConsultation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-consultations/{id}")
    public ResponseEntity<TabConsultation> getTabConsultation(@PathVariable Long id) {
        log.debug("REST request to get TabConsultation : {}", id);
        Optional<TabConsultation> tabConsultation = tabConsultationRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(tabConsultation);
    }

    /**
     * {@code DELETE  /tab-consultations/:id} : delete the "id" tabConsultation.
     *
     * @param id the id of the tabConsultation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-consultations/{id}")
    public ResponseEntity<Void> deleteTabConsultation(@PathVariable Long id) {
        log.debug("REST request to delete TabConsultation : {}", id);
        tabConsultationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
