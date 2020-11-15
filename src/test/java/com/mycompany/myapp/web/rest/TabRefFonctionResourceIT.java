package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabRefFonction;
import com.mycompany.myapp.repository.TabRefFonctionRepository;

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
 * Integration tests for the {@link TabRefFonctionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabRefFonctionResourceIT {

    private static final Integer DEFAULT_ID_FONCTION = 1;
    private static final Integer UPDATED_ID_FONCTION = 2;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TabRefFonctionRepository tabRefFonctionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabRefFonctionMockMvc;

    private TabRefFonction tabRefFonction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRefFonction createEntity(EntityManager em) {
        TabRefFonction tabRefFonction = new TabRefFonction()
            .idFonction(DEFAULT_ID_FONCTION)
            .libelle(DEFAULT_LIBELLE);
        return tabRefFonction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRefFonction createUpdatedEntity(EntityManager em) {
        TabRefFonction tabRefFonction = new TabRefFonction()
            .idFonction(UPDATED_ID_FONCTION)
            .libelle(UPDATED_LIBELLE);
        return tabRefFonction;
    }

    @BeforeEach
    public void initTest() {
        tabRefFonction = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabRefFonction() throws Exception {
        int databaseSizeBeforeCreate = tabRefFonctionRepository.findAll().size();
        // Create the TabRefFonction
        restTabRefFonctionMockMvc.perform(post("/api/tab-ref-fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefFonction)))
            .andExpect(status().isCreated());

        // Validate the TabRefFonction in the database
        List<TabRefFonction> tabRefFonctionList = tabRefFonctionRepository.findAll();
        assertThat(tabRefFonctionList).hasSize(databaseSizeBeforeCreate + 1);
        TabRefFonction testTabRefFonction = tabRefFonctionList.get(tabRefFonctionList.size() - 1);
        assertThat(testTabRefFonction.getIdFonction()).isEqualTo(DEFAULT_ID_FONCTION);
        assertThat(testTabRefFonction.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTabRefFonctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabRefFonctionRepository.findAll().size();

        // Create the TabRefFonction with an existing ID
        tabRefFonction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabRefFonctionMockMvc.perform(post("/api/tab-ref-fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefFonction)))
            .andExpect(status().isBadRequest());

        // Validate the TabRefFonction in the database
        List<TabRefFonction> tabRefFonctionList = tabRefFonctionRepository.findAll();
        assertThat(tabRefFonctionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabRefFonctions() throws Exception {
        // Initialize the database
        tabRefFonctionRepository.saveAndFlush(tabRefFonction);

        // Get all the tabRefFonctionList
        restTabRefFonctionMockMvc.perform(get("/api/tab-ref-fonctions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabRefFonction.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFonction").value(hasItem(DEFAULT_ID_FONCTION)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTabRefFonction() throws Exception {
        // Initialize the database
        tabRefFonctionRepository.saveAndFlush(tabRefFonction);

        // Get the tabRefFonction
        restTabRefFonctionMockMvc.perform(get("/api/tab-ref-fonctions/{id}", tabRefFonction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabRefFonction.getId().intValue()))
            .andExpect(jsonPath("$.idFonction").value(DEFAULT_ID_FONCTION))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTabRefFonction() throws Exception {
        // Get the tabRefFonction
        restTabRefFonctionMockMvc.perform(get("/api/tab-ref-fonctions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabRefFonction() throws Exception {
        // Initialize the database
        tabRefFonctionRepository.saveAndFlush(tabRefFonction);

        int databaseSizeBeforeUpdate = tabRefFonctionRepository.findAll().size();

        // Update the tabRefFonction
        TabRefFonction updatedTabRefFonction = tabRefFonctionRepository.findById(tabRefFonction.getId()).get();
        // Disconnect from session so that the updates on updatedTabRefFonction are not directly saved in db
        em.detach(updatedTabRefFonction);
        updatedTabRefFonction
            .idFonction(UPDATED_ID_FONCTION)
            .libelle(UPDATED_LIBELLE);

        restTabRefFonctionMockMvc.perform(put("/api/tab-ref-fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabRefFonction)))
            .andExpect(status().isOk());

        // Validate the TabRefFonction in the database
        List<TabRefFonction> tabRefFonctionList = tabRefFonctionRepository.findAll();
        assertThat(tabRefFonctionList).hasSize(databaseSizeBeforeUpdate);
        TabRefFonction testTabRefFonction = tabRefFonctionList.get(tabRefFonctionList.size() - 1);
        assertThat(testTabRefFonction.getIdFonction()).isEqualTo(UPDATED_ID_FONCTION);
        assertThat(testTabRefFonction.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabRefFonction() throws Exception {
        int databaseSizeBeforeUpdate = tabRefFonctionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabRefFonctionMockMvc.perform(put("/api/tab-ref-fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefFonction)))
            .andExpect(status().isBadRequest());

        // Validate the TabRefFonction in the database
        List<TabRefFonction> tabRefFonctionList = tabRefFonctionRepository.findAll();
        assertThat(tabRefFonctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabRefFonction() throws Exception {
        // Initialize the database
        tabRefFonctionRepository.saveAndFlush(tabRefFonction);

        int databaseSizeBeforeDelete = tabRefFonctionRepository.findAll().size();

        // Delete the tabRefFonction
        restTabRefFonctionMockMvc.perform(delete("/api/tab-ref-fonctions/{id}", tabRefFonction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabRefFonction> tabRefFonctionList = tabRefFonctionRepository.findAll();
        assertThat(tabRefFonctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
