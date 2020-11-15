package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabSpecialite;
import com.mycompany.myapp.repository.TabSpecialiteRepository;

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
 * Integration tests for the {@link TabSpecialiteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabSpecialiteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PERSONNEL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PERSONNEL = "BBBBBBBBBB";

    @Autowired
    private TabSpecialiteRepository tabSpecialiteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabSpecialiteMockMvc;

    private TabSpecialite tabSpecialite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabSpecialite createEntity(EntityManager em) {
        TabSpecialite tabSpecialite = new TabSpecialite()
            .libelle(DEFAULT_LIBELLE)
            .nomPersonnel(DEFAULT_NOM_PERSONNEL);
        return tabSpecialite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabSpecialite createUpdatedEntity(EntityManager em) {
        TabSpecialite tabSpecialite = new TabSpecialite()
            .libelle(UPDATED_LIBELLE)
            .nomPersonnel(UPDATED_NOM_PERSONNEL);
        return tabSpecialite;
    }

    @BeforeEach
    public void initTest() {
        tabSpecialite = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabSpecialite() throws Exception {
        int databaseSizeBeforeCreate = tabSpecialiteRepository.findAll().size();
        // Create the TabSpecialite
        restTabSpecialiteMockMvc.perform(post("/api/tab-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSpecialite)))
            .andExpect(status().isCreated());

        // Validate the TabSpecialite in the database
        List<TabSpecialite> tabSpecialiteList = tabSpecialiteRepository.findAll();
        assertThat(tabSpecialiteList).hasSize(databaseSizeBeforeCreate + 1);
        TabSpecialite testTabSpecialite = tabSpecialiteList.get(tabSpecialiteList.size() - 1);
        assertThat(testTabSpecialite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTabSpecialite.getNomPersonnel()).isEqualTo(DEFAULT_NOM_PERSONNEL);
    }

    @Test
    @Transactional
    public void createTabSpecialiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabSpecialiteRepository.findAll().size();

        // Create the TabSpecialite with an existing ID
        tabSpecialite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabSpecialiteMockMvc.perform(post("/api/tab-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSpecialite)))
            .andExpect(status().isBadRequest());

        // Validate the TabSpecialite in the database
        List<TabSpecialite> tabSpecialiteList = tabSpecialiteRepository.findAll();
        assertThat(tabSpecialiteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabSpecialites() throws Exception {
        // Initialize the database
        tabSpecialiteRepository.saveAndFlush(tabSpecialite);

        // Get all the tabSpecialiteList
        restTabSpecialiteMockMvc.perform(get("/api/tab-specialites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabSpecialite.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].nomPersonnel").value(hasItem(DEFAULT_NOM_PERSONNEL)));
    }
    
    @Test
    @Transactional
    public void getTabSpecialite() throws Exception {
        // Initialize the database
        tabSpecialiteRepository.saveAndFlush(tabSpecialite);

        // Get the tabSpecialite
        restTabSpecialiteMockMvc.perform(get("/api/tab-specialites/{id}", tabSpecialite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabSpecialite.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.nomPersonnel").value(DEFAULT_NOM_PERSONNEL));
    }
    @Test
    @Transactional
    public void getNonExistingTabSpecialite() throws Exception {
        // Get the tabSpecialite
        restTabSpecialiteMockMvc.perform(get("/api/tab-specialites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabSpecialite() throws Exception {
        // Initialize the database
        tabSpecialiteRepository.saveAndFlush(tabSpecialite);

        int databaseSizeBeforeUpdate = tabSpecialiteRepository.findAll().size();

        // Update the tabSpecialite
        TabSpecialite updatedTabSpecialite = tabSpecialiteRepository.findById(tabSpecialite.getId()).get();
        // Disconnect from session so that the updates on updatedTabSpecialite are not directly saved in db
        em.detach(updatedTabSpecialite);
        updatedTabSpecialite
            .libelle(UPDATED_LIBELLE)
            .nomPersonnel(UPDATED_NOM_PERSONNEL);

        restTabSpecialiteMockMvc.perform(put("/api/tab-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabSpecialite)))
            .andExpect(status().isOk());

        // Validate the TabSpecialite in the database
        List<TabSpecialite> tabSpecialiteList = tabSpecialiteRepository.findAll();
        assertThat(tabSpecialiteList).hasSize(databaseSizeBeforeUpdate);
        TabSpecialite testTabSpecialite = tabSpecialiteList.get(tabSpecialiteList.size() - 1);
        assertThat(testTabSpecialite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTabSpecialite.getNomPersonnel()).isEqualTo(UPDATED_NOM_PERSONNEL);
    }

    @Test
    @Transactional
    public void updateNonExistingTabSpecialite() throws Exception {
        int databaseSizeBeforeUpdate = tabSpecialiteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabSpecialiteMockMvc.perform(put("/api/tab-specialites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSpecialite)))
            .andExpect(status().isBadRequest());

        // Validate the TabSpecialite in the database
        List<TabSpecialite> tabSpecialiteList = tabSpecialiteRepository.findAll();
        assertThat(tabSpecialiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabSpecialite() throws Exception {
        // Initialize the database
        tabSpecialiteRepository.saveAndFlush(tabSpecialite);

        int databaseSizeBeforeDelete = tabSpecialiteRepository.findAll().size();

        // Delete the tabSpecialite
        restTabSpecialiteMockMvc.perform(delete("/api/tab-specialites/{id}", tabSpecialite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabSpecialite> tabSpecialiteList = tabSpecialiteRepository.findAll();
        assertThat(tabSpecialiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
