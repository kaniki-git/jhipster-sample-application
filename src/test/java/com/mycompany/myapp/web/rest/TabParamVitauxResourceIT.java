package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabParamVitaux;
import com.mycompany.myapp.repository.TabParamVitauxRepository;

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
 * Integration tests for the {@link TabParamVitauxResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabParamVitauxResourceIT {

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
    private TabParamVitauxRepository tabParamVitauxRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabParamVitauxMockMvc;

    private TabParamVitaux tabParamVitaux;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabParamVitaux createEntity(EntityManager em) {
        TabParamVitaux tabParamVitaux = new TabParamVitaux()
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
        return tabParamVitaux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabParamVitaux createUpdatedEntity(EntityManager em) {
        TabParamVitaux tabParamVitaux = new TabParamVitaux()
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
        return tabParamVitaux;
    }

    @BeforeEach
    public void initTest() {
        tabParamVitaux = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabParamVitaux() throws Exception {
        int databaseSizeBeforeCreate = tabParamVitauxRepository.findAll().size();
        // Create the TabParamVitaux
        restTabParamVitauxMockMvc.perform(post("/api/tab-param-vitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamVitaux)))
            .andExpect(status().isCreated());

        // Validate the TabParamVitaux in the database
        List<TabParamVitaux> tabParamVitauxList = tabParamVitauxRepository.findAll();
        assertThat(tabParamVitauxList).hasSize(databaseSizeBeforeCreate + 1);
        TabParamVitaux testTabParamVitaux = tabParamVitauxList.get(tabParamVitauxList.size() - 1);
        assertThat(testTabParamVitaux.getDateExam()).isEqualTo(DEFAULT_DATE_EXAM);
        assertThat(testTabParamVitaux.getTa1()).isEqualTo(DEFAULT_TA_1);
        assertThat(testTabParamVitaux.getTa2()).isEqualTo(DEFAULT_TA_2);
        assertThat(testTabParamVitaux.getPouls()).isEqualTo(DEFAULT_POULS);
        assertThat(testTabParamVitaux.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testTabParamVitaux.getFrequence()).isEqualTo(DEFAULT_FREQUENCE);
        assertThat(testTabParamVitaux.getTaille()).isEqualTo(DEFAULT_TAILLE);
        assertThat(testTabParamVitaux.getSa02()).isEqualTo(DEFAULT_SA_02);
        assertThat(testTabParamVitaux.getSous()).isEqualTo(DEFAULT_SOUS);
        assertThat(testTabParamVitaux.getPoids()).isEqualTo(DEFAULT_POIDS);
    }

    @Test
    @Transactional
    public void createTabParamVitauxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabParamVitauxRepository.findAll().size();

        // Create the TabParamVitaux with an existing ID
        tabParamVitaux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabParamVitauxMockMvc.perform(post("/api/tab-param-vitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamVitaux)))
            .andExpect(status().isBadRequest());

        // Validate the TabParamVitaux in the database
        List<TabParamVitaux> tabParamVitauxList = tabParamVitauxRepository.findAll();
        assertThat(tabParamVitauxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabParamVitauxes() throws Exception {
        // Initialize the database
        tabParamVitauxRepository.saveAndFlush(tabParamVitaux);

        // Get all the tabParamVitauxList
        restTabParamVitauxMockMvc.perform(get("/api/tab-param-vitauxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabParamVitaux.getId().intValue())))
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
    public void getTabParamVitaux() throws Exception {
        // Initialize the database
        tabParamVitauxRepository.saveAndFlush(tabParamVitaux);

        // Get the tabParamVitaux
        restTabParamVitauxMockMvc.perform(get("/api/tab-param-vitauxes/{id}", tabParamVitaux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabParamVitaux.getId().intValue()))
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
    public void getNonExistingTabParamVitaux() throws Exception {
        // Get the tabParamVitaux
        restTabParamVitauxMockMvc.perform(get("/api/tab-param-vitauxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabParamVitaux() throws Exception {
        // Initialize the database
        tabParamVitauxRepository.saveAndFlush(tabParamVitaux);

        int databaseSizeBeforeUpdate = tabParamVitauxRepository.findAll().size();

        // Update the tabParamVitaux
        TabParamVitaux updatedTabParamVitaux = tabParamVitauxRepository.findById(tabParamVitaux.getId()).get();
        // Disconnect from session so that the updates on updatedTabParamVitaux are not directly saved in db
        em.detach(updatedTabParamVitaux);
        updatedTabParamVitaux
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

        restTabParamVitauxMockMvc.perform(put("/api/tab-param-vitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabParamVitaux)))
            .andExpect(status().isOk());

        // Validate the TabParamVitaux in the database
        List<TabParamVitaux> tabParamVitauxList = tabParamVitauxRepository.findAll();
        assertThat(tabParamVitauxList).hasSize(databaseSizeBeforeUpdate);
        TabParamVitaux testTabParamVitaux = tabParamVitauxList.get(tabParamVitauxList.size() - 1);
        assertThat(testTabParamVitaux.getDateExam()).isEqualTo(UPDATED_DATE_EXAM);
        assertThat(testTabParamVitaux.getTa1()).isEqualTo(UPDATED_TA_1);
        assertThat(testTabParamVitaux.getTa2()).isEqualTo(UPDATED_TA_2);
        assertThat(testTabParamVitaux.getPouls()).isEqualTo(UPDATED_POULS);
        assertThat(testTabParamVitaux.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testTabParamVitaux.getFrequence()).isEqualTo(UPDATED_FREQUENCE);
        assertThat(testTabParamVitaux.getTaille()).isEqualTo(UPDATED_TAILLE);
        assertThat(testTabParamVitaux.getSa02()).isEqualTo(UPDATED_SA_02);
        assertThat(testTabParamVitaux.getSous()).isEqualTo(UPDATED_SOUS);
        assertThat(testTabParamVitaux.getPoids()).isEqualTo(UPDATED_POIDS);
    }

    @Test
    @Transactional
    public void updateNonExistingTabParamVitaux() throws Exception {
        int databaseSizeBeforeUpdate = tabParamVitauxRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabParamVitauxMockMvc.perform(put("/api/tab-param-vitauxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabParamVitaux)))
            .andExpect(status().isBadRequest());

        // Validate the TabParamVitaux in the database
        List<TabParamVitaux> tabParamVitauxList = tabParamVitauxRepository.findAll();
        assertThat(tabParamVitauxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabParamVitaux() throws Exception {
        // Initialize the database
        tabParamVitauxRepository.saveAndFlush(tabParamVitaux);

        int databaseSizeBeforeDelete = tabParamVitauxRepository.findAll().size();

        // Delete the tabParamVitaux
        restTabParamVitauxMockMvc.perform(delete("/api/tab-param-vitauxes/{id}", tabParamVitaux.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabParamVitaux> tabParamVitauxList = tabParamVitauxRepository.findAll();
        assertThat(tabParamVitauxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
