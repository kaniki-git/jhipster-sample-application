package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabProfil;
import com.mycompany.myapp.repository.TabProfilRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TabProfilResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabProfilResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EST_ACTIF = false;
    private static final Boolean UPDATED_EST_ACTIF = true;

    @Autowired
    private TabProfilRepository tabProfilRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabProfilMockMvc;

    private TabProfil tabProfil;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabProfil createEntity(EntityManager em) {
        TabProfil tabProfil = new TabProfil()
            .libelle(DEFAULT_LIBELLE)
            .estActif(DEFAULT_EST_ACTIF);
        return tabProfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabProfil createUpdatedEntity(EntityManager em) {
        TabProfil tabProfil = new TabProfil()
            .libelle(UPDATED_LIBELLE)
            .estActif(UPDATED_EST_ACTIF);
        return tabProfil;
    }

    @BeforeEach
    public void initTest() {
        tabProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabProfil() throws Exception {
        int databaseSizeBeforeCreate = tabProfilRepository.findAll().size();
        // Create the TabProfil
        restTabProfilMockMvc.perform(post("/api/tab-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabProfil)))
            .andExpect(status().isCreated());

        // Validate the TabProfil in the database
        List<TabProfil> tabProfilList = tabProfilRepository.findAll();
        assertThat(tabProfilList).hasSize(databaseSizeBeforeCreate + 1);
        TabProfil testTabProfil = tabProfilList.get(tabProfilList.size() - 1);
        assertThat(testTabProfil.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTabProfil.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
    }

    @Test
    @Transactional
    public void createTabProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabProfilRepository.findAll().size();

        // Create the TabProfil with an existing ID
        tabProfil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabProfilMockMvc.perform(post("/api/tab-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabProfil)))
            .andExpect(status().isBadRequest());

        // Validate the TabProfil in the database
        List<TabProfil> tabProfilList = tabProfilRepository.findAll();
        assertThat(tabProfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabProfils() throws Exception {
        // Initialize the database
        tabProfilRepository.saveAndFlush(tabProfil);

        // Get all the tabProfilList
        restTabProfilMockMvc.perform(get("/api/tab-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabProfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTabProfil() throws Exception {
        // Initialize the database
        tabProfilRepository.saveAndFlush(tabProfil);

        // Get the tabProfil
        restTabProfilMockMvc.perform(get("/api/tab-profils/{id}", tabProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabProfil.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTabProfil() throws Exception {
        // Get the tabProfil
        restTabProfilMockMvc.perform(get("/api/tab-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabProfil() throws Exception {
        // Initialize the database
        tabProfilRepository.saveAndFlush(tabProfil);

        int databaseSizeBeforeUpdate = tabProfilRepository.findAll().size();

        // Update the tabProfil
        TabProfil updatedTabProfil = tabProfilRepository.findById(tabProfil.getId()).get();
        // Disconnect from session so that the updates on updatedTabProfil are not directly saved in db
        em.detach(updatedTabProfil);
        updatedTabProfil
            .libelle(UPDATED_LIBELLE)
            .estActif(UPDATED_EST_ACTIF);

        restTabProfilMockMvc.perform(put("/api/tab-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabProfil)))
            .andExpect(status().isOk());

        // Validate the TabProfil in the database
        List<TabProfil> tabProfilList = tabProfilRepository.findAll();
        assertThat(tabProfilList).hasSize(databaseSizeBeforeUpdate);
        TabProfil testTabProfil = tabProfilList.get(tabProfilList.size() - 1);
        assertThat(testTabProfil.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTabProfil.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabProfil() throws Exception {
        int databaseSizeBeforeUpdate = tabProfilRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabProfilMockMvc.perform(put("/api/tab-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabProfil)))
            .andExpect(status().isBadRequest());

        // Validate the TabProfil in the database
        List<TabProfil> tabProfilList = tabProfilRepository.findAll();
        assertThat(tabProfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabProfil() throws Exception {
        // Initialize the database
        tabProfilRepository.saveAndFlush(tabProfil);

        int databaseSizeBeforeDelete = tabProfilRepository.findAll().size();

        // Delete the tabProfil
        restTabProfilMockMvc.perform(delete("/api/tab-profils/{id}", tabProfil.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabProfil> tabProfilList = tabProfilRepository.findAll();
        assertThat(tabProfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
