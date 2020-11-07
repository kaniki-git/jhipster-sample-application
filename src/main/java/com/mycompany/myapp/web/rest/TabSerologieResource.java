package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabSerologie;
import com.mycompany.myapp.repository.TabSerologieRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabSerologie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabSerologieResource {

    private final Logger log = LoggerFactory.getLogger(TabSerologieResource.class);

    private static final String ENTITY_NAME = "tabSerologie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabSerologieRepository tabSerologieRepository;

    public TabSerologieResource(TabSerologieRepository tabSerologieRepository) {
        this.tabSerologieRepository = tabSerologieRepository;
    }

    /**
     * {@code POST  /tab-serologies} : Create a new tabSerologie.
     *
     * @param tabSerologie the tabSerologie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabSerologie, or with status {@code 400 (Bad Request)} if the tabSerologie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-serologies")
    public ResponseEntity<TabSerologie> createTabSerologie(@RequestBody TabSerologie tabSerologie) throws URISyntaxException {
        log.debug("REST request to save TabSerologie : {}", tabSerologie);
        if (tabSerologie.getId() != null) {
            throw new BadRequestAlertException("A new tabSerologie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabSerologie result = tabSerologieRepository.save(tabSerologie);
        return ResponseEntity.created(new URI("/api/tab-serologies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-serologies} : Updates an existing tabSerologie.
     *
     * @param tabSerologie the tabSerologie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabSerologie,
     * or with status {@code 400 (Bad Request)} if the tabSerologie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabSerologie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-serologies")
    public ResponseEntity<TabSerologie> updateTabSerologie(@RequestBody TabSerologie tabSerologie) throws URISyntaxException {
        log.debug("REST request to update TabSerologie : {}", tabSerologie);
        if (tabSerologie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabSerologie result = tabSerologieRepository.save(tabSerologie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabSerologie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-serologies} : get all the tabSerologies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabSerologies in body.
     */
    @GetMapping("/tab-serologies")
    public List<TabSerologie> getAllTabSerologies() {
        log.debug("REST request to get all TabSerologies");
        return tabSerologieRepository.findAll();
    }

    /**
     * {@code GET  /tab-serologies/:id} : get the "id" tabSerologie.
     *
     * @param id the id of the tabSerologie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabSerologie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-serologies/{id}")
    public ResponseEntity<TabSerologie> getTabSerologie(@PathVariable Long id) {
        log.debug("REST request to get TabSerologie : {}", id);
        Optional<TabSerologie> tabSerologie = tabSerologieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabSerologie);
    }

    /**
     * {@code DELETE  /tab-serologies/:id} : delete the "id" tabSerologie.
     *
     * @param id the id of the tabSerologie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-serologies/{id}")
    public ResponseEntity<Void> deleteTabSerologie(@PathVariable Long id) {
        log.debug("REST request to delete TabSerologie : {}", id);
        tabSerologieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
