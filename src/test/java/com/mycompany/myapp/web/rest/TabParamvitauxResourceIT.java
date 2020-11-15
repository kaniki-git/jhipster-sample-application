package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabParamvitaux;
import com.mycompany.myapp.repository.TabParamvitauxRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TabParamvitauxResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabParamvitauxResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_EXAM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_EXAM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Float DEFAULT_TA_1 = 1F;
    private static final Float UPDATED_TA_1 = 2F;

    private static final Float DEFAULT_TA_2 = 1F;
    private static final Float UPDATED_TA_2 = 2F;

    private static final Float DEFAULT_POULS = 1F;
    private static final Float UPDATED_POULS = 2F;

    private static final Float DEFAULT_TEMPERATURE = 1F;
    private static final Float UPDATED_TEMPERATURE = 2F;

    private static final Float DEFAULT_FREQUENCE = 1F;
    private static final Float UPDATED_FREQUENCE = 2F;

    private static final Float DEFAULT_TAILLE = 1F;
    private static final Float UPDATED_TAILLE = 2F;

    private static final Float DEFAULT_SA_02 = 1F;
    private static final Float UPDATED_SA_02 = 2F;

    private static final Float DEFAULT_SOUS = 1F;
    private static final Float UPDATED_SOUS = 2F;

    private static final Float DEFAULT_POIDS = 1F;
    private static final Float UPDATED_POIDS = 2F;

    @Autowired
    private TabParamvitauxRepository tabParamvitauxRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabParamvitauxMockMvc;

    private TabParamvitaux tabParamvitaux;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabParamvitaux createEntity(EntityManager em) {
        TabParamvitaux tabParamvitaux = new TabParamvitaux()
            .dateExam(DEFAULT_DATE_EXAM)
            .ta1(DEFAULT_TA_1)
            .ta2(DEFAULT_TA_2)
            .pouls(DEFAULT_POULS)
            .temperature(DEFAULT_TEMPERATURE)
            .frequence(DEFAULT_FREQUENCE)
            .taille(DEFAULT_TAILLE)
            .sa02(DEFAULT_SA_02)
            .sous(DEFAULT_SOUS)
            .poids(DEFAULT_POIDS);
        return tabParamvitaux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabParamvitaux createUpdatedEntity(EntityManager em) {
        TabParamvitaux tabParamvitaux = new TabParamvitaux()
            .dateExam(UPDATED_DATE_EXAM)
            .ta1(UPDATED_TA_1)
            .ta2(UPDATED_TA_2)
            .pouls(UPDATED_POULS)
            .temperature(UPDATED_TEMPERATURE)
            .frequence(UPDATED_FREQUENCE)
            .taille(UPDATED_TAILLE)
            .sa02(UPDATED_SA_02)
            .sous(UPDATED_SOUS)
            .poids(UPDATED_POIDS);
        return tabParamvitaux;
    }

    @BeforeEach
    public void initTest() {
        tabParamvitaux = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabParamvitaux() throws Exception {
        int databaseSizeBeforeCreate = tabParamvitauxRepository.findAll().size();
        // Create the TabParamvitaux
        restTabParamvitauxMockMvc.perform(post("/api/tab-paramvitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamvitaux)))
            .andExpect(status().isCreated());

        // Validate the TabParamvitaux in the database
        List<TabParamvitaux> tabParamvitauxList = tabParamvitauxRepository.findAll();
        assertThat(tabParamvitauxList).hasSize(databaseSizeBeforeCreate + 1);
        TabParamvitaux testTabParamvitaux = tabParamvitauxList.get(tabParamvitauxList.size() - 1);
        assertThat(testTabParamvitaux.getDateExam()).isEqualTo(DEFAULT_DATE_EXAM);
        assertThat(testTabParamvitaux.getTa1()).isEqualTo(DEFAULT_TA_1);
        assertThat(testTabParamvitaux.getTa2()).isEqualTo(DEFAULT_TA_2);
        assertThat(testTabParamvitaux.getPouls()).isEqualTo(DEFAULT_POULS);
        assertThat(testTabParamvitaux.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testTabParamvitaux.getFrequence()).isEqualTo(DEFAULT_FREQUENCE);
        assertThat(testTabParamvitaux.getTaille()).isEqualTo(DEFAULT_TAILLE);
        assertThat(testTabParamvitaux.getSa02()).isEqualTo(DEFAULT_SA_02);
        assertThat(testTabParamvitaux.getSous()).isEqualTo(DEFAULT_SOUS);
        assertThat(testTabParamvitaux.getPoids()).isEqualTo(DEFAULT_POIDS);
    }

    @Test
    @Transactional
    public void createTabParamvitauxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabParamvitauxRepository.findAll().size();

        // Create the TabParamvitaux with an existing ID
        tabParamvitaux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabParamvitauxMockMvc.perform(post("/api/tab-paramvitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamvitaux)))
            .andExpect(status().isBadRequest());

        // Validate the TabParamvitaux in the database
        List<TabParamvitaux> tabParamvitauxList = tabParamvitauxRepository.findAll();
        assertThat(tabParamvitauxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabParamvitauxes() throws Exception {
        // Initialize the database
        tabParamvitauxRepository.saveAndFlush(tabParamvitaux);

        // Get all the tabParamvitauxList
        restTabParamvitauxMockMvc.perform(get("/api/tab-paramvitauxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabParamvitaux.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateExam").value(hasItem(sameInstant(DEFAULT_DATE_EXAM))))
            .andExpect(jsonPath("$.[*].ta1").value(hasItem(DEFAULT_TA_1.doubleValue())))
            .andExpect(jsonPath("$.[*].ta2").value(hasItem(DEFAULT_TA_2.doubleValue())))
            .andExpect(jsonPath("$.[*].pouls").value(hasItem(DEFAULT_POULS.doubleValue())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].frequence").value(hasItem(DEFAULT_FREQUENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].taille").value(hasItem(DEFAULT_TAILLE.doubleValue())))
            .andExpect(jsonPath("$.[*].sa02").value(hasItem(DEFAULT_SA_02.doubleValue())))
            .andExpect(jsonPath("$.[*].sous").value(hasItem(DEFAULT_SOUS.doubleValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTabParamvitaux() throws Exception {
        // Initialize the database
        tabParamvitauxRepository.saveAndFlush(tabParamvitaux);

        // Get the tabParamvitaux
        restTabParamvitauxMockMvc.perform(get("/api/tab-paramvitauxes/{id}", tabParamvitaux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabParamvitaux.getId().intValue()))
            .andExpect(jsonPath("$.dateExam").value(sameInstant(DEFAULT_DATE_EXAM)))
            .andExpect(jsonPath("$.ta1").value(DEFAULT_TA_1.doubleValue()))
            .andExpect(jsonPath("$.ta2").value(DEFAULT_TA_2.doubleValue()))
            .andExpect(jsonPath("$.pouls").value(DEFAULT_POULS.doubleValue()))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.frequence").value(DEFAULT_FREQUENCE.doubleValue()))
            .andExpect(jsonPath("$.taille").value(DEFAULT_TAILLE.doubleValue()))
            .andExpect(jsonPath("$.sa02").value(DEFAULT_SA_02.doubleValue()))
            .andExpect(jsonPath("$.sous").value(DEFAULT_SOUS.doubleValue()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTabParamvitaux() throws Exception {
        // Get the tabParamvitaux
        restTabParamvitauxMockMvc.perform(get("/api/tab-paramvitauxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabParamvitaux() throws Exception {
        // Initialize the database
        tabParamvitauxRepository.saveAndFlush(tabParamvitaux);

        int databaseSizeBeforeUpdate = tabParamvitauxRepository.findAll().size();

        // Update the tabParamvitaux
        TabParamvitaux updatedTabParamvitaux = tabParamvitauxRepository.findById(tabParamvitaux.getId()).get();
        // Disconnect from session so that the updates on updatedTabParamvitaux are not directly saved in db
        em.detach(updatedTabParamvitaux);
        updatedTabParamvitaux
            .dateExam(UPDATED_DATE_EXAM)
            .ta1(UPDATED_TA_1)
            .ta2(UPDATED_TA_2)
            .pouls(UPDATED_POULS)
            .temperature(UPDATED_TEMPERATURE)
            .frequence(UPDATED_FREQUENCE)
            .taille(UPDATED_TAILLE)
            .sa02(UPDATED_SA_02)
            .sous(UPDATED_SOUS)
            .poids(UPDATED_POIDS);

        restTabParamvitauxMockMvc.perform(put("/api/tab-paramvitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabParamvitaux)))
            .andExpect(status().isOk());

        // Validate the TabParamvitaux in the database
        List<TabParamvitaux> tabParamvitauxList = tabParamvitauxRepository.findAll();
        assertThat(tabParamvitauxList).hasSize(databaseSizeBeforeUpdate);
        TabParamvitaux testTabParamvitaux = tabParamvitauxList.get(tabParamvitauxList.size() - 1);
        assertThat(testTabParamvitaux.getDateExam()).isEqualTo(UPDATED_DATE_EXAM);
        assertThat(testTabParamvitaux.getTa1()).isEqualTo(UPDATED_TA_1);
        assertThat(testTabParamvitaux.getTa2()).isEqualTo(UPDATED_TA_2);
        assertThat(testTabParamvitaux.getPouls()).isEqualTo(UPDATED_POULS);
        assertThat(testTabParamvitaux.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testTabParamvitaux.getFrequence()).isEqualTo(UPDATED_FREQUENCE);
        assertThat(testTabParamvitaux.getTaille()).isEqualTo(UPDATED_TAILLE);
        assertThat(testTabParamvitaux.getSa02()).isEqualTo(UPDATED_SA_02);
        assertThat(testTabParamvitaux.getSous()).isEqualTo(UPDATED_SOUS);
        assertThat(testTabParamvitaux.getPoids()).isEqualTo(UPDATED_POIDS);
    }

    @Test
    @Transactional
    public void updateNonExistingTabParamvitaux() throws Exception {
        int databaseSizeBeforeUpdate = tabParamvitauxRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabParamvitauxMockMvc.perform(put("/api/tab-paramvitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamvitaux)))
            .andExpect(status().isBadRequest());

        // Validate the TabParamvitaux in the database
        List<TabParamvitaux> tabParamvitauxList = tabParamvitauxRepository.findAll();
        assertThat(tabParamvitauxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabParamvitaux() throws Exception {
        // Initialize the database
        tabParamvitauxRepository.saveAndFlush(tabParamvitaux);

        int databaseSizeBeforeDelete = tabParamvitauxRepository.findAll().size();

        // Delete the tabParamvitaux
        restTabParamvitauxMockMvc.perform(delete("/api/tab-paramvitauxes/{id}", tabParamvitaux.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabParamvitaux> tabParamvitauxList = tabParamvitauxRepository.findAll();
        assertThat(tabParamvitauxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
