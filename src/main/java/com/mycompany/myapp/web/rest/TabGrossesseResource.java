package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabGrossesse;
import com.mycompany.myapp.repository.TabGrossesseRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabGrossesse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabGrossesseResource {

    private final Logger log = LoggerFactory.getLogger(TabGrossesseResource.class);

    private static final String ENTITY_NAME = "tabGrossesse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabGrossesseRepository tabGrossesseRepository;

    public TabGrossesseResource(TabGrossesseRepository tabGrossesseRepository) {
        this.tabGrossesseRepository = tabGrossesseRepository;
    }

    /**
     * {@code POST  /tab-grossesses} : Create a new tabGrossesse.
     *
     * @param tabGrossesse the tabGrossesse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabGrossesse, or with status {@code 400 (Bad Request)} if the tabGrossesse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-grossesses")
    public ResponseEntity<TabGrossesse> createTabGrossesse(@RequestBody TabGrossesse tabGrossesse) throws URISyntaxException {
        log.debug("REST request to save TabGrossesse : {}", tabGrossesse);
        if (tabGrossesse.getId() != null) {
            throw new BadRequestAlertException("A new tabGrossesse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabGrossesse result = tabGrossesseRepository.save(tabGrossesse);
        return ResponseEntity.created(new URI("/api/tab-grossesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-grossesses} : Updates an existing tabGrossesse.
     *
     * @param tabGrossesse the tabGrossesse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabGrossesse,
     * or with status {@code 400 (Bad Request)} if the tabGrossesse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabGrossesse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-grossesses")
    public ResponseEntity<TabGrossesse> updateTabGrossesse(@RequestBody TabGrossesse tabGrossesse) throws URISyntaxException {
        log.debug("REST request to update TabGrossesse : {}", tabGrossesse);
        if (tabGrossesse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabGrossesse result = tabGrossesseRepository.save(tabGrossesse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabGrossesse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-grossesses} : get all the tabGrossesses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabGrossesses in body.
     */
    @GetMapping("/tab-grossesses")
    public List<TabGrossesse> getAllTabGrossesses() {
        log.debug("REST request to get all TabGrossesses");
        return tabGrossesseRepository.findAll();
    }

    /**
     * {@code GET  /tab-grossesses/:id} : get the "id" tabGrossesse.
     *
     * @param id the id of the tabGrossesse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabGrossesse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-grossesses/{id}")
    public ResponseEntity<TabGrossesse> getTabGrossesse(@PathVariable Long id) {
        log.debug("REST request to get TabGrossesse : {}", id);
        Optional<TabGrossesse> tabGrossesse = tabGrossesseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabGrossesse);
    }

    /**
     * {@code DELETE  /tab-grossesses/:id} : delete the "id" tabGrossesse.
     *
     * @param id the id of the tabGrossesse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-grossesses/{id}")
    public ResponseEntity<Void> deleteTabGrossesse(@PathVariable Long id) {
        log.debug("REST request to delete TabGrossesse : {}", id);
        tabGrossesseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
