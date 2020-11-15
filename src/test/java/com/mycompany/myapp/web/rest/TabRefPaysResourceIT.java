package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabRefPays;
import com.mycompany.myapp.repository.TabRefPaysRepository;

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
 * Integration tests for the {@link TabRefPaysResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabRefPaysResourceIT {

    private static final Integer DEFAULT_ID_PAYS = 1;
    private static final Integer UPDATED_ID_PAYS = 2;

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TabRefPaysRepository tabRefPaysRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabRefPaysMockMvc;

    private TabRefPays tabRefPays;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRefPays createEntity(EntityManager em) {
        TabRefPays tabRefPays = new TabRefPays()
            .idPays(DEFAULT_ID_PAYS)
            .libelle(DEFAULT_LIBELLE);
        return tabRefPays;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRefPays createUpdatedEntity(EntityManager em) {
        TabRefPays tabRefPays = new TabRefPays()
            .idPays(UPDATED_ID_PAYS)
            .libelle(UPDATED_LIBELLE);
        return tabRefPays;
    }

    @BeforeEach
    public void initTest() {
        tabRefPays = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabRefPays() throws Exception {
        int databaseSizeBeforeCreate = tabRefPaysRepository.findAll().size();
        // Create the TabRefPays
        restTabRefPaysMockMvc.perform(post("/api/tab-ref-pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefPays)))
            .andExpect(status().isCreated());

        // Validate the TabRefPays in the database
        List<TabRefPays> tabRefPaysList = tabRefPaysRepository.findAll();
        assertThat(tabRefPaysList).hasSize(databaseSizeBeforeCreate + 1);
        TabRefPays testTabRefPays = tabRefPaysList.get(tabRefPaysList.size() - 1);
        assertThat(testTabRefPays.getIdPays()).isEqualTo(DEFAULT_ID_PAYS);
        assertThat(testTabRefPays.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTabRefPaysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabRefPaysRepository.findAll().size();

        // Create the TabRefPays with an existing ID
        tabRefPays.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabRefPaysMockMvc.perform(post("/api/tab-ref-pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefPays)))
            .andExpect(status().isBadRequest());

        // Validate the TabRefPays in the database
        List<TabRefPays> tabRefPaysList = tabRefPaysRepository.findAll();
        assertThat(tabRefPaysList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabRefPays() throws Exception {
        // Initialize the database
        tabRefPaysRepository.saveAndFlush(tabRefPays);

        // Get all the tabRefPaysList
        restTabRefPaysMockMvc.perform(get("/api/tab-ref-pays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabRefPays.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPays").value(hasItem(DEFAULT_ID_PAYS)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getTabRefPays() throws Exception {
        // Initialize the database
        tabRefPaysRepository.saveAndFlush(tabRefPays);

        // Get the tabRefPays
        restTabRefPaysMockMvc.perform(get("/api/tab-ref-pays/{id}", tabRefPays.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabRefPays.getId().intValue()))
            .andExpect(jsonPath("$.idPays").value(DEFAULT_ID_PAYS))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingTabRefPays() throws Exception {
        // Get the tabRefPays
        restTabRefPaysMockMvc.perform(get("/api/tab-ref-pays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabRefPays() throws Exception {
        // Initialize the database
        tabRefPaysRepository.saveAndFlush(tabRefPays);

        int databaseSizeBeforeUpdate = tabRefPaysRepository.findAll().size();

        // Update the tabRefPays
        TabRefPays updatedTabRefPays = tabRefPaysRepository.findById(tabRefPays.getId()).get();
        // Disconnect from session so that the updates on updatedTabRefPays are not directly saved in db
        em.detach(updatedTabRefPays);
        updatedTabRefPays
            .idPays(UPDATED_ID_PAYS)
            .libelle(UPDATED_LIBELLE);

        restTabRefPaysMockMvc.perform(put("/api/tab-ref-pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabRefPays)))
            .andExpect(status().isOk());

        // Validate the TabRefPays in the database
        List<TabRefPays> tabRefPaysList = tabRefPaysRepository.findAll();
        assertThat(tabRefPaysList).hasSize(databaseSizeBeforeUpdate);
        TabRefPays testTabRefPays = tabRefPaysList.get(tabRefPaysList.size() - 1);
        assertThat(testTabRefPays.getIdPays()).isEqualTo(UPDATED_ID_PAYS);
        assertThat(testTabRefPays.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabRefPays() throws Exception {
        int databaseSizeBeforeUpdate = tabRefPaysRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabRefPaysMockMvc.perform(put("/api/tab-ref-pays")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRefPays)))
            .andExpect(status().isBadRequest());

        // Validate the TabRefPays in the database
        List<TabRefPays> tabRefPaysList = tabRefPaysRepository.findAll();
        assertThat(tabRefPaysList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabRefPays() throws Exception {
        // Initialize the database
        tabRefPaysRepository.saveAndFlush(tabRefPays);

        int databaseSizeBeforeDelete = tabRefPaysRepository.findAll().size();

        // Delete the tabRefPays
        restTabRefPaysMockMvc.perform(delete("/api/tab-ref-pays/{id}", tabRefPays.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabRefPays> tabRefPaysList = tabRefPaysRepository.findAll();
        assertThat(tabRefPaysList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
