package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabUserProfil;
import com.mycompany.myapp.repository.TabUserProfilRepository;

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
 * Integration tests for the {@link TabUserProfilResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabUserProfilResourceIT {

    private static final Boolean DEFAULT_EST_ACTIF = false;
    private static final Boolean UPDATED_EST_ACTIF = true;

    private static final String DEFAULT_MATRICULE_CREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_CREATION = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATION = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_DATE_MODIF = "BBBBBBBBBB";

    @Autowired
    private TabUserProfilRepository tabUserProfilRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabUserProfilMockMvc;

    private TabUserProfil tabUserProfil;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUserProfil createEntity(EntityManager em) {
        TabUserProfil tabUserProfil = new TabUserProfil()
            .estActif(DEFAULT_EST_ACTIF)
            .matriculeCreation(DEFAULT_MATRICULE_CREATION)
            .dateCreation(DEFAULT_DATE_CREATION)
            .matriculeModif(DEFAULT_MATRICULE_MODIF)
            .dateModif(DEFAULT_DATE_MODIF);
        return tabUserProfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUserProfil createUpdatedEntity(EntityManager em) {
        TabUserProfil tabUserProfil = new TabUserProfil()
            .estActif(UPDATED_EST_ACTIF)
            .matriculeCreation(UPDATED_MATRICULE_CREATION)
            .dateCreation(UPDATED_DATE_CREATION)
            .matriculeModif(UPDATED_MATRICULE_MODIF)
            .dateModif(UPDATED_DATE_MODIF);
        return tabUserProfil;
    }

    @BeforeEach
    public void initTest() {
        tabUserProfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabUserProfil() throws Exception {
        int databaseSizeBeforeCreate = tabUserProfilRepository.findAll().size();
        // Create the TabUserProfil
        restTabUserProfilMockMvc.perform(post("/api/tab-user-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUserProfil)))
            .andExpect(status().isCreated());

        // Validate the TabUserProfil in the database
        List<TabUserProfil> tabUserProfilList = tabUserProfilRepository.findAll();
        assertThat(tabUserProfilList).hasSize(databaseSizeBeforeCreate + 1);
        TabUserProfil testTabUserProfil = tabUserProfilList.get(tabUserProfilList.size() - 1);
        assertThat(testTabUserProfil.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testTabUserProfil.getMatriculeCreation()).isEqualTo(DEFAULT_MATRICULE_CREATION);
        assertThat(testTabUserProfil.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testTabUserProfil.getMatriculeModif()).isEqualTo(DEFAULT_MATRICULE_MODIF);
        assertThat(testTabUserProfil.getDateModif()).isEqualTo(DEFAULT_DATE_MODIF);
    }

    @Test
    @Transactional
    public void createTabUserProfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabUserProfilRepository.findAll().size();

        // Create the TabUserProfil with an existing ID
        tabUserProfil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabUserProfilMockMvc.perform(post("/api/tab-user-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUserProfil)))
            .andExpect(status().isBadRequest());

        // Validate the TabUserProfil in the database
        List<TabUserProfil> tabUserProfilList = tabUserProfilRepository.findAll();
        assertThat(tabUserProfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabUserProfils() throws Exception {
        // Initialize the database
        tabUserProfilRepository.saveAndFlush(tabUserProfil);

        // Get all the tabUserProfilList
        restTabUserProfilMockMvc.perform(get("/api/tab-user-profils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabUserProfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].matriculeCreation").value(hasItem(DEFAULT_MATRICULE_CREATION)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION)))
            .andExpect(jsonPath("$.[*].matriculeModif").value(hasItem(DEFAULT_MATRICULE_MODIF)))
            .andExpect(jsonPath("$.[*].dateModif").value(hasItem(DEFAULT_DATE_MODIF)));
    }
    
    @Test
    @Transactional
    public void getTabUserProfil() throws Exception {
        // Initialize the database
        tabUserProfilRepository.saveAndFlush(tabUserProfil);

        // Get the tabUserProfil
        restTabUserProfilMockMvc.perform(get("/api/tab-user-profils/{id}", tabUserProfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabUserProfil.getId().intValue()))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.matriculeCreation").value(DEFAULT_MATRICULE_CREATION))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION))
            .andExpect(jsonPath("$.matriculeModif").value(DEFAULT_MATRICULE_MODIF))
            .andExpect(jsonPath("$.dateModif").value(DEFAULT_DATE_MODIF));
    }
    @Test
    @Transactional
    public void getNonExistingTabUserProfil() throws Exception {
        // Get the tabUserProfil
        restTabUserProfilMockMvc.perform(get("/api/tab-user-profils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabUserProfil() throws Exception {
        // Initialize the database
        tabUserProfilRepository.saveAndFlush(tabUserProfil);

        int databaseSizeBeforeUpdate = tabUserProfilRepository.findAll().size();

        // Update the tabUserProfil
        TabUserProfil updatedTabUserProfil = tabUserProfilRepository.findById(tabUserProfil.getId()).get();
        // Disconnect from session so that the updates on updatedTabUserProfil are not directly saved in db
        em.detach(updatedTabUserProfil);
        updatedTabUserProfil
            .estActif(UPDATED_EST_ACTIF)
            .matriculeCreation(UPDATED_MATRICULE_CREATION)
            .dateCreation(UPDATED_DATE_CREATION)
            .matriculeModif(UPDATED_MATRICULE_MODIF)
            .dateModif(UPDATED_DATE_MODIF);

        restTabUserProfilMockMvc.perform(put("/api/tab-user-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabUserProfil)))
            .andExpect(status().isOk());

        // Validate the TabUserProfil in the database
        List<TabUserProfil> tabUserProfilList = tabUserProfilRepository.findAll();
        assertThat(tabUserProfilList).hasSize(databaseSizeBeforeUpdate);
        TabUserProfil testTabUserProfil = tabUserProfilList.get(tabUserProfilList.size() - 1);
        assertThat(testTabUserProfil.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testTabUserProfil.getMatriculeCreation()).isEqualTo(UPDATED_MATRICULE_CREATION);
        assertThat(testTabUserProfil.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testTabUserProfil.getMatriculeModif()).isEqualTo(UPDATED_MATRICULE_MODIF);
        assertThat(testTabUserProfil.getDateModif()).isEqualTo(UPDATED_DATE_MODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabUserProfil() throws Exception {
        int databaseSizeBeforeUpdate = tabUserProfilRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabUserProfilMockMvc.perform(put("/api/tab-user-profils")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUserProfil)))
            .andExpect(status().isBadRequest());

        // Validate the TabUserProfil in the database
        List<TabUserProfil> tabUserProfilList = tabUserProfilRepository.findAll();
        assertThat(tabUserProfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabUserProfil() throws Exception {
        // Initialize the database
        tabUserProfilRepository.saveAndFlush(tabUserProfil);

        int databaseSizeBeforeDelete = tabUserProfilRepository.findAll().size();

        // Delete the tabUserProfil
        restTabUserProfilMockMvc.perform(delete("/api/tab-user-profils/{id}", tabUserProfil.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabUserProfil> tabUserProfilList = tabUserProfilRepository.findAll();
        assertThat(tabUserProfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
