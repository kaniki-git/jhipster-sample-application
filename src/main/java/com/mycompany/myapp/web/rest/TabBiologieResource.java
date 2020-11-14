package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabBiologie;
import com.mycompany.myapp.repository.TabBiologieRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabBiologie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabBiologieResource {

    private final Logger log = LoggerFactory.getLogger(TabBiologieResource.class);

    private static final String ENTITY_NAME = "tabBiologie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabBiologieRepository tabBiologieRepository;

    public TabBiologieResource(TabBiologieRepository tabBiologieRepository) {
        this.tabBiologieRepository = tabBiologieRepository;
    }

    /**
     * {@code POST  /tab-biologies} : Create a new tabBiologie.
     *
     * @param tabBiologie the tabBiologie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabBiologie, or with status {@code 400 (Bad Request)} if the tabBiologie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-biologies")
    public ResponseEntity<TabBiologie> createTabBiologie(@RequestBody TabBiologie tabBiologie) throws URISyntaxException {
        log.debug("REST request to save TabBiologie : {}", tabBiologie);
        if (tabBiologie.getId() != null) {
            throw new BadRequestAlertException("A new tabBiologie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabBiologie result = tabBiologieRepository.save(tabBiologie);
        return ResponseEntity.created(new URI("/api/tab-biologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-biologies} : Updates an existing tabBiologie.
     *
     * @param tabBiologie the tabBiologie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabBiologie,
     * or with status {@code 400 (Bad Request)} if the tabBiologie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabBiologie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-biologies")
    public ResponseEntity<TabBiologie> updateTabBiologie(@RequestBody TabBiologie tabBiologie) throws URISyntaxException {
        log.debug("REST request to update TabBiologie : {}", tabBiologie);
        if (tabBiologie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabBiologie result = tabBiologieRepository.save(tabBiologie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabBiologie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-biologies} : get all the tabBiologies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabBiologies in body.
     */
    @GetMapping("/tab-biologies")
    public List<TabBiologie> getAllTabBiologies() {
        log.debug("REST request to get all TabBiologies");
        return tabBiologieRepository.findAll();
    }

    /**
     * {@code GET  /tab-biologies/:id} : get the "id" tabBiologie.
     *
     * @param id the id of the tabBiologie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabBiologie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-biologies/{id}")
    public ResponseEntity<TabBiologie> getTabBiologie(@PathVariable Long id) {
        log.debug("REST request to get TabBiologie : {}", id);
        Optional<TabBiologie> tabBiologie = tabBiologieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabBiologie);
    }

    /**
     * {@code DELETE  /tab-biologies/:id} : delete the "id" tabBiologie.
     *
     * @param id the id of the tabBiologie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-biologies/{id}")
    public ResponseEntity<Void> deleteTabBiologie(@PathVariable Long id) {
        log.debug("REST request to delete TabBiologie : {}", id);
        tabBiologieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
