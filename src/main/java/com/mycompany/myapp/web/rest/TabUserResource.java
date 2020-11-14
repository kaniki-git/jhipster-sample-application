package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TabUser;
import com.mycompany.myapp.repository.TabUserRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TabUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TabUserResource {

    private final Logger log = LoggerFactory.getLogger(TabUserResource.class);

    private static final String ENTITY_NAME = "tabUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TabUserRepository tabUserRepository;

    public TabUserResource(TabUserRepository tabUserRepository) {
        this.tabUserRepository = tabUserRepository;
    }

    /**
     * {@code POST  /tab-users} : Create a new tabUser.
     *
     * @param tabUser the tabUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tabUser, or with status {@code 400 (Bad Request)} if the tabUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tab-users")
    public ResponseEntity<TabUser> createTabUser(@RequestBody TabUser tabUser) throws URISyntaxException {
        log.debug("REST request to save TabUser : {}", tabUser);
        if (tabUser.getId() != null) {
            throw new BadRequestAlertException("A new tabUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TabUser result = tabUserRepository.save(tabUser);
        return ResponseEntity.created(new URI("/api/tab-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tab-users} : Updates an existing tabUser.
     *
     * @param tabUser the tabUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tabUser,
     * or with status {@code 400 (Bad Request)} if the tabUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tabUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tab-users")
    public ResponseEntity<TabUser> updateTabUser(@RequestBody TabUser tabUser) throws URISyntaxException {
        log.debug("REST request to update TabUser : {}", tabUser);
        if (tabUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TabUser result = tabUserRepository.save(tabUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tabUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tab-users} : get all the tabUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tabUsers in body.
     */
    @GetMapping("/tab-users")
    public List<TabUser> getAllTabUsers() {
        log.debug("REST request to get all TabUsers");
        return tabUserRepository.findAll();
    }

    /**
     * {@code GET  /tab-users/:id} : get the "id" tabUser.
     *
     * @param id the id of the tabUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tabUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tab-users/{id}")
    public ResponseEntity<TabUser> getTabUser(@PathVariable Long id) {
        log.debug("REST request to get TabUser : {}", id);
        Optional<TabUser> tabUser = tabUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tabUser);
    }

    /**
     * {@code DELETE  /tab-users/:id} : delete the "id" tabUser.
     *
     * @param id the id of the tabUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tab-users/{id}")
    public ResponseEntity<Void> deleteTabUser(@PathVariable Long id) {
        log.debug("REST request to delete TabUser : {}", id);
        tabUserRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
