package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabHistoriquePatient;
import com.mycompany.myapp.repository.TabHistoriquePatientRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabHistoriquePatient}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabHistoriquePatientResource {

    private final Logger log = LoggerFactory.getLogger(TabHistoriquePatientResource.class);

    private static final String ENTITY_NAME = "tabHistoriquePatient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabHistoriquePatientRepository tabHistoriquePatientRepository;

    public TabHistoriquePatientResource(TabHistoriquePatientRepository tabHistoriquePatientRepository) {
        this.tabHistoriquePatientRepository = tabHistoriquePatientRepository;
    }

    /**
     * {@code POST  /tab-historique-patients} : Create a new tabHistoriquePatient.
     *
     * @param tabHistoriquePatient the tabHistoriquePatient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabHistoriquePatient, or with status {@code 400 (Bad Request)} if the tabHistoriquePatient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-historique-patients")
    public ResponseEntity<TabHistoriquePatient> createTabHistoriquePatient(@RequestBody TabHistoriquePatient tabHistoriquePatient) throws URISyntaxException {
        log.debug("REST request to save TabHistoriquePatient : {}", tabHistoriquePatient);
        if (tabHistoriquePatient.getId() != null) {
            throw new BadRequestAlertException("A new tabHistoriquePatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabHistoriquePatient result = tabHistoriquePatientRepository.save(tabHistoriquePatient);
        return ResponseEntity.created(new URI("/api/tab-historique-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-historique-patients} : Updates an existing tabHistoriquePatient.
     *
     * @param tabHistoriquePatient the tabHistoriquePatient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabHistoriquePatient,
     * or with status {@code 400 (Bad Request)} if the tabHistoriquePatient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabHistoriquePatient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-historique-patients")
    public ResponseEntity<TabHistoriquePatient> updateTabHistoriquePatient(@RequestBody TabHistoriquePatient tabHistoriquePatient) throws URISyntaxException {
        log.debug("REST request to update TabHistoriquePatient : {}", tabHistoriquePatient);
        if (tabHistoriquePatient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabHistoriquePatient result = tabHistoriquePatientRepository.save(tabHistoriquePatient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabHistoriquePatient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-historique-patients} : get all the tabHistoriquePatients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabHistoriquePatients in body.
     */
    @GetMapping("/tab-historique-patients")
    public List<TabHistoriquePatient> getAllTabHistoriquePatients() {
        log.debug("REST request to get all TabHistoriquePatients");
        return tabHistoriquePatientRepository.findAll();
    }

    /**
     * {@code GET  /tab-historique-patients/:id} : get the "id" tabHistoriquePatient.
     *
     * @param id the id of the tabHistoriquePatient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabHistoriquePatient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-historique-patients/{id}")
    public ResponseEntity<TabHistoriquePatient> getTabHistoriquePatient(@PathVariable Long id) {
        log.debug("REST request to get TabHistoriquePatient : {}", id);
        Optional<TabHistoriquePatient> tabHistoriquePatient = tabHistoriquePatientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabHistoriquePatient);
    }

    /**
     * {@code DELETE  /tab-historique-patients/:id} : delete the "id" tabHistoriquePatient.
     *
     * @param id the id of the tabHistoriquePatient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-historique-patients/{id}")
    public ResponseEntity<Void> deleteTabHistoriquePatient(@PathVariable Long id) {
        log.debug("REST request to delete TabHistoriquePatient : {}", id);
        tabHistoriquePatientRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
