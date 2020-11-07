package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabRendezvous;
import com.mycompany.myapp.repository.TabRendezvousRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabRendezvous}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabRendezvousResource {

    private final Logger log = LoggerFactory.getLogger(TabRendezvousResource.class);

    private static final String ENTITY_NAME = "tabRendezvous";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabRendezvousRepository tabRendezvousRepository;

    public TabRendezvousResource(TabRendezvousRepository tabRendezvousRepository) {
        this.tabRendezvousRepository = tabRendezvousRepository;
    }

    /**
     * {@code POST  /tab-rendezvous} : Create a new tabRendezvous.
     *
     * @param tabRendezvous the tabRendezvous to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabRendezvous, or with status {@code 400 (Bad Request)} if the tabRendezvous has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-rendezvous")
    public ResponseEntity<TabRendezvous> createTabRendezvous(@RequestBody TabRendezvous tabRendezvous) throws URISyntaxException {
        log.debug("REST request to save TabRendezvous : {}", tabRendezvous);
        if (tabRendezvous.getId() != null) {
            throw new BadRequestAlertException("A new tabRendezvous cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabRendezvous result = tabRendezvousRepository.save(tabRendezvous);
        return ResponseEntity.created(new URI("/api/tab-rendezvous/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-rendezvous} : Updates an existing tabRendezvous.
     *
     * @param tabRendezvous the tabRendezvous to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabRendezvous,
     * or with status {@code 400 (Bad Request)} if the tabRendezvous is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabRendezvous couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-rendezvous")
    public ResponseEntity<TabRendezvous> updateTabRendezvous(@RequestBody TabRendezvous tabRendezvous) throws URISyntaxException {
        log.debug("REST request to update TabRendezvous : {}", tabRendezvous);
        if (tabRendezvous.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabRendezvous result = tabRendezvousRepository.save(tabRendezvous);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabRendezvous.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-rendezvous} : get all the tabRendezvous.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabRendezvous in body.
     */
    @GetMapping("/tab-rendezvous")
    public List<TabRendezvous> getAllTabRendezvous() {
        log.debug("REST request to get all TabRendezvous");
        return tabRendezvousRepository.findAll();
    }

    /**
     * {@code GET  /tab-rendezvous/:id} : get the "id" tabRendezvous.
     *
     * @param id the id of the tabRendezvous to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabRendezvous, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-rendezvous/{id}")
    public ResponseEntity<TabRendezvous> getTabRendezvous(@PathVariable Long id) {
        log.debug("REST request to get TabRendezvous : {}", id);
        Optional<TabRendezvous> tabRendezvous = tabRendezvousRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabRendezvous);
    }

    /**
     * {@code DELETE  /tab-rendezvous/:id} : delete the "id" tabRendezvous.
     *
     * @param id the id of the tabRendezvous to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-rendezvous/{id}")
    public ResponseEntity<Void> deleteTabRendezvous(@PathVariable Long id) {
        log.debug("REST request to delete TabRendezvous : {}", id);
        tabRendezvousRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
