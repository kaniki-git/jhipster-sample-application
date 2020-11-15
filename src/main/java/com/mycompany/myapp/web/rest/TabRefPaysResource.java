package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabRefPays;
import com.mycompany.myapp.repository.TabRefPaysRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabRefPays}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabRefPaysResource {

    private final Logger log = LoggerFactory.getLogger(TabRefPaysResource.class);

    private static final String ENTITY_NAME = "tabRefPays";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabRefPaysRepository tabRefPaysRepository;

    public TabRefPaysResource(TabRefPaysRepository tabRefPaysRepository) {
        this.tabRefPaysRepository = tabRefPaysRepository;
    }

    /**
     * {@code POST  /tab-ref-pays} : Create a new tabRefPays.
     *
     * @param tabRefPays the tabRefPays to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabRefPays, or with status {@code 400 (Bad Request)} if the tabRefPays has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-ref-pays")
    public ResponseEntity<TabRefPays> createTabRefPays(@RequestBody TabRefPays tabRefPays) throws URISyntaxException {
        log.debug("REST request to save TabRefPays : {}", tabRefPays);
        if (tabRefPays.getId() != null) {
            throw new BadRequestAlertException("A new tabRefPays cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabRefPays result = tabRefPaysRepository.save(tabRefPays);
        return ResponseEntity.created(new URI("/api/tab-ref-pays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-ref-pays} : Updates an existing tabRefPays.
     *
     * @param tabRefPays the tabRefPays to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabRefPays,
     * or with status {@code 400 (Bad Request)} if the tabRefPays is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabRefPays couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-ref-pays")
    public ResponseEntity<TabRefPays> updateTabRefPays(@RequestBody TabRefPays tabRefPays) throws URISyntaxException {
        log.debug("REST request to update TabRefPays : {}", tabRefPays);
        if (tabRefPays.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabRefPays result = tabRefPaysRepository.save(tabRefPays);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabRefPays.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-ref-pays} : get all the tabRefPays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabRefPays in body.
     */
    @GetMapping("/tab-ref-pays")
    public List<TabRefPays> getAllTabRefPays() {
        log.debug("REST request to get all TabRefPays");
        return tabRefPaysRepository.findAll();
    }

    /**
     * {@code GET  /tab-ref-pays/:id} : get the "id" tabRefPays.
     *
     * @param id the id of the tabRefPays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabRefPays, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-ref-pays/{id}")
    public ResponseEntity<TabRefPays> getTabRefPays(@PathVariable Long id) {
        log.debug("REST request to get TabRefPays : {}", id);
        Optional<TabRefPays> tabRefPays = tabRefPaysRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabRefPays);
    }

    /**
     * {@code DELETE  /tab-ref-pays/:id} : delete the "id" tabRefPays.
     *
     * @param id the id of the tabRefPays to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-ref-pays/{id}")
    public ResponseEntity<Void> deleteTabRefPays(@PathVariable Long id) {
        log.debug("REST request to delete TabRefPays : {}", id);
        tabRefPaysRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
