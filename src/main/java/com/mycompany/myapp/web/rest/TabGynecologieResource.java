package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabGynecologie;
import com.mycompany.myapp.repository.TabGynecologieRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabGynecologie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabGynecologieResource {

    private final Logger log = LoggerFactory.getLogger(TabGynecologieResource.class);

    private static final String ENTITY_NAME = "tabGynecologie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabGynecologieRepository tabGynecologieRepository;

    public TabGynecologieResource(TabGynecologieRepository tabGynecologieRepository) {
        this.tabGynecologieRepository = tabGynecologieRepository;
    }

    /**
     * {@code POST  /tab-gynecologies} : Create a new tabGynecologie.
     *
     * @param tabGynecologie the tabGynecologie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabGynecologie, or with status {@code 400 (Bad Request)} if the tabGynecologie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-gynecologies")
    public ResponseEntity<TabGynecologie> createTabGynecologie(@RequestBody TabGynecologie tabGynecologie) throws URISyntaxException {
        log.debug("REST request to save TabGynecologie : {}", tabGynecologie);
        if (tabGynecologie.getId() != null) {
            throw new BadRequestAlertException("A new tabGynecologie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabGynecologie result = tabGynecologieRepository.save(tabGynecologie);
        return ResponseEntity.created(new URI("/api/tab-gynecologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-gynecologies} : Updates an existing tabGynecologie.
     *
     * @param tabGynecologie the tabGynecologie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabGynecologie,
     * or with status {@code 400 (Bad Request)} if the tabGynecologie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabGynecologie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-gynecologies")
    public ResponseEntity<TabGynecologie> updateTabGynecologie(@RequestBody TabGynecologie tabGynecologie) throws URISyntaxException {
        log.debug("REST request to update TabGynecologie : {}", tabGynecologie);
        if (tabGynecologie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabGynecologie result = tabGynecologieRepository.save(tabGynecologie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabGynecologie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-gynecologies} : get all the tabGynecologies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabGynecologies in body.
     */
    @GetMapping("/tab-gynecologies")
    public List<TabGynecologie> getAllTabGynecologies() {
        log.debug("REST request to get all TabGynecologies");
        return tabGynecologieRepository.findAll();
    }

    /**
     * {@code GET  /tab-gynecologies/:id} : get the "id" tabGynecologie.
     *
     * @param id the id of the tabGynecologie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabGynecologie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-gynecologies/{id}")
    public ResponseEntity<TabGynecologie> getTabGynecologie(@PathVariable Long id) {
        log.debug("REST request to get TabGynecologie : {}", id);
        Optional<TabGynecologie> tabGynecologie = tabGynecologieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabGynecologie);
    }

    /**
     * {@code DELETE  /tab-gynecologies/:id} : delete the "id" tabGynecologie.
     *
     * @param id the id of the tabGynecologie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-gynecologies/{id}")
    public ResponseEntity<Void> deleteTabGynecologie(@PathVariable Long id) {
        log.debug("REST request to delete TabGynecologie : {}", id);
        tabGynecologieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
