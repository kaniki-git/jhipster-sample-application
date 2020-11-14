package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabPatient;
import com.mycompany.myapp.repository.TabPatientRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabPatient}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabPatientResource {

    private final Logger log = LoggerFactory.getLogger(TabPatientResource.class);

    private static final String ENTITY_NAME = "tabPatient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabPatientRepository tabPatientRepository;

    public TabPatientResource(TabPatientRepository tabPatientRepository) {
        this.tabPatientRepository = tabPatientRepository;
    }

    /**
     * {@code POST  /tab-patients} : Create a new tabPatient.
     *
     * @param tabPatient the tabPatient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabPatient, or with status {@code 400 (Bad Request)} if the tabPatient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-patients")
    public ResponseEntity<TabPatient> createTabPatient(@RequestBody TabPatient tabPatient) throws URISyntaxException {
        log.debug("REST request to save TabPatient : {}", tabPatient);
        if (tabPatient.getId() != null) {
            throw new BadRequestAlertException("A new tabPatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabPatient result = tabPatientRepository.save(tabPatient);
        return ResponseEntity.created(new URI("/api/tab-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-patients} : Updates an existing tabPatient.
     *
     * @param tabPatient the tabPatient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabPatient,
     * or with status {@code 400 (Bad Request)} if the tabPatient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabPatient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-patients")
    public ResponseEntity<TabPatient> updateTabPatient(@RequestBody TabPatient tabPatient) throws URISyntaxException {
        log.debug("REST request to update TabPatient : {}", tabPatient);
        if (tabPatient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabPatient result = tabPatientRepository.save(tabPatient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabPatient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-patients} : get all the tabPatients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabPatients in body.
     */
    @GetMapping("/tab-patients")
    public List<TabPatient> getAllTabPatients() {
        log.debug("REST request to get all TabPatients");
        return tabPatientRepository.findAll();
    }

    /**
     * {@code GET  /tab-patients/:id} : get the "id" tabPatient.
     *
     * @param id the id of the tabPatient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabPatient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-patients/{id}")
    public ResponseEntity<TabPatient> getTabPatient(@PathVariable Long id) {
        log.debug("REST request to get TabPatient : {}", id);
        Optional<TabPatient> tabPatient = tabPatientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabPatient);
    }

    /**
     * {@code DELETE  /tab-patients/:id} : delete the "id" tabPatient.
     *
     * @param id the id of the tabPatient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-patients/{id}")
    public ResponseEntity<Void> deleteTabPatient(@PathVariable Long id) {
        log.debug("REST request to delete TabPatient : {}", id);
        tabPatientRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
