package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabHospital;
import com.mycompany.myapp.repository.TabHospitalRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabHospital}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabHospitalResource {

    private final Logger log = LoggerFactory.getLogger(TabHospitalResource.class);

    private static final String ENTITY_NAME = "tabHospital";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabHospitalRepository tabHospitalRepository;

    public TabHospitalResource(TabHospitalRepository tabHospitalRepository) {
        this.tabHospitalRepository = tabHospitalRepository;
    }

    /**
     * {@code POST  /tab-hospitals} : Create a new tabHospital.
     *
     * @param tabHospital the tabHospital to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabHospital, or with status {@code 400 (Bad Request)} if the tabHospital has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-hospitals")
    public ResponseEntity<TabHospital> createTabHospital(@RequestBody TabHospital tabHospital) throws URISyntaxException {
        log.debug("REST request to save TabHospital : {}", tabHospital);
        if (tabHospital.getId() != null) {
            throw new BadRequestAlertException("A new tabHospital cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabHospital result = tabHospitalRepository.save(tabHospital);
        return ResponseEntity.created(new URI("/api/tab-hospitals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-hospitals} : Updates an existing tabHospital.
     *
     * @param tabHospital the tabHospital to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabHospital,
     * or with status {@code 400 (Bad Request)} if the tabHospital is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabHospital couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-hospitals")
    public ResponseEntity<TabHospital> updateTabHospital(@RequestBody TabHospital tabHospital) throws URISyntaxException {
        log.debug("REST request to update TabHospital : {}", tabHospital);
        if (tabHospital.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabHospital result = tabHospitalRepository.save(tabHospital);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabHospital.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-hospitals} : get all the tabHospitals.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabHospitals in body.
     */
    @GetMapping("/tab-hospitals")
    public List<TabHospital> getAllTabHospitals(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all TabHospitals");
        return tabHospitalRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /tab-hospitals/:id} : get the "id" tabHospital.
     *
     * @param id the id of the tabHospital to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabHospital, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-hospitals/{id}")
    public ResponseEntity<TabHospital> getTabHospital(@PathVariable Long id) {
        log.debug("REST request to get TabHospital : {}", id);
        Optional<TabHospital> tabHospital = tabHospitalRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(tabHospital);
    }

    /**
     * {@code DELETE  /tab-hospitals/:id} : delete the "id" tabHospital.
     *
     * @param id the id of the tabHospital to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-hospitals/{id}")
    public ResponseEntity<Void> deleteTabHospital(@PathVariable Long id) {
        log.debug("REST request to delete TabHospital : {}", id);
        tabHospitalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
