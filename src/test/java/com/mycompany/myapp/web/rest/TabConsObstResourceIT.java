package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabConsObst;
import com.mycompany.myapp.repository.TabConsObstRepository;

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
 * Integration tests for the {@link TabConsObstResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabConsObstResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_CONS_OBST = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CONS_OBST = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_AGE_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_AGE_PATIENT = "BBBBBBBBBB";

    private static final Float DEFAULT_POIDS = 1F;
    private static final Float UPDATED_POIDS = 2F;

    private static final Float DEFAULT_TAS = 1F;
    private static final Float UPDATED_TAS = 2F;

    private static final Float DEFAULT_TAD = 1F;
    private static final Float UPDATED_TAD = 2F;

    private static final Float DEFAULT_A = 1F;
    private static final Float UPDATED_A = 2F;

    private static final String DEFAULT_S = "AAAAAAAAAA";
    private static final String UPDATED_S = "BBBBBBBBBB";

    private static final String DEFAULT_N = "AAAAAAAAAA";
    private static final String UPDATED_N = "BBBBBBBBBB";

    private static final String DEFAULT_HU = "AAAAAAAAAA";
    private static final String UPDATED_HU = "BBBBBBBBBB";

    private static final String DEFAULT_BC = "AAAAAAAAAA";
    private static final String UPDATED_BC = "BBBBBBBBBB";

    private static final String DEFAULT_SB = "AAAAAAAAAA";
    private static final String UPDATED_SB = "BBBBBBBBBB";

    private static final String DEFAULT_OE = "AAAAAAAAAA";
    private static final String UPDATED_OE = "BBBBBBBBBB";

    private static final String DEFAULT_PRES = "AAAAAAAAAA";
    private static final String UPDATED_PRES = "BBBBBBBBBB";

    private static final String DEFAULT_CONCLUSION = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITEMENT = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SUIVI_PAR = "AAAAAAAAAA";
    private static final String UPDATED_SUIVI_PAR = "BBBBBBBBBB";

    @Autowired
    private TabConsObstRepository tabConsObstRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabConsObstMockMvc;

    private TabConsObst tabConsObst;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsObst createEntity(EntityManager em) {
        TabConsObst tabConsObst = new TabConsObst()
            .dateConsObst(DEFAULT_DATE_CONS_OBST)
            .agePatient(DEFAULT_AGE_PATIENT)
            .poids(DEFAULT_POIDS)
            .tas(DEFAULT_TAS)
            .tad(DEFAULT_TAD)
            .a(DEFAULT_A)
            .s(DEFAULT_S)
            .n(DEFAULT_N)
            .hu(DEFAULT_HU)
            .bc(DEFAULT_BC)
            .sb(DEFAULT_SB)
            .oe(DEFAULT_OE)
            .pres(DEFAULT_PRES)
            .conclusion(DEFAULT_CONCLUSION)
            .traitement(DEFAULT_TRAITEMENT)
            .suiviPar(DEFAULT_SUIVI_PAR);
        return tabConsObst;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsObst createUpdatedEntity(EntityManager em) {
        TabConsObst tabConsObst = new TabConsObst()
            .dateConsObst(UPDATED_DATE_CONS_OBST)
            .agePatient(UPDATED_AGE_PATIENT)
            .poids(UPDATED_POIDS)
            .tas(UPDATED_TAS)
            .tad(UPDATED_TAD)
            .a(UPDATED_A)
            .s(UPDATED_S)
            .n(UPDATED_N)
            .hu(UPDATED_HU)
            .bc(UPDATED_BC)
            .sb(UPDATED_SB)
            .oe(UPDATED_OE)
            .pres(UPDATED_PRES)
            .conclusion(UPDATED_CONCLUSION)
            .traitement(UPDATED_TRAITEMENT)
            .suiviPar(UPDATED_SUIVI_PAR);
        return tabConsObst;
    }

    @BeforeEach
    public void initTest() {
        tabConsObst = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabConsObst() throws Exception {
        int databaseSizeBeforeCreate = tabConsObstRepository.findAll().size();
        // Create the TabConsObst
        restTabConsObstMockMvc.perform(post("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isCreated());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeCreate + 1);
        TabConsObst testTabConsObst = tabConsObstList.get(tabConsObstList.size() - 1);
        assertThat(testTabConsObst.getDateConsObst()).isEqualTo(DEFAULT_DATE_CONS_OBST);
        assertThat(testTabConsObst.getAgePatient()).isEqualTo(DEFAULT_AGE_PATIENT);
        assertThat(testTabConsObst.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testTabConsObst.getTas()).isEqualTo(DEFAULT_TAS);
        assertThat(testTabConsObst.getTad()).isEqualTo(DEFAULT_TAD);
        assertThat(testTabConsObst.getA()).isEqualTo(DEFAULT_A);
        assertThat(testTabConsObst.getS()).isEqualTo(DEFAULT_S);
        assertThat(testTabConsObst.getN()).isEqualTo(DEFAULT_N);
        assertThat(testTabConsObst.getHu()).isEqualTo(DEFAULT_HU);
        assertThat(testTabConsObst.getBc()).isEqualTo(DEFAULT_BC);
        assertThat(testTabConsObst.getSb()).isEqualTo(DEFAULT_SB);
        assertThat(testTabConsObst.getOe()).isEqualTo(DEFAULT_OE);
        assertThat(testTabConsObst.getPres()).isEqualTo(DEFAULT_PRES);
        assertThat(testTabConsObst.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
        assertThat(testTabConsObst.getTraitement()).isEqualTo(DEFAULT_TRAITEMENT);
        assertThat(testTabConsObst.getSuiviPar()).isEqualTo(DEFAULT_SUIVI_PAR);
    }

    @Test
    @Transactional
    public void createTabConsObstWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabConsObstRepository.findAll().size();

        // Create the TabConsObst with an existing ID
        tabConsObst.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabConsObstMockMvc.perform(post("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabConsObsts() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        // Get all the tabConsObstList
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabConsObst.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateConsObst").value(hasItem(sameInstant(DEFAULT_DATE_CONS_OBST))))
            .andExpect(jsonPath("$.[*].agePatient").value(hasItem(DEFAULT_AGE_PATIENT)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.doubleValue())))
            .andExpect(jsonPath("$.[*].tas").value(hasItem(DEFAULT_TAS.doubleValue())))
            .andExpect(jsonPath("$.[*].tad").value(hasItem(DEFAULT_TAD.doubleValue())))
            .andExpect(jsonPath("$.[*].a").value(hasItem(DEFAULT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].s").value(hasItem(DEFAULT_S)))
            .andExpect(jsonPath("$.[*].n").value(hasItem(DEFAULT_N)))
            .andExpect(jsonPath("$.[*].hu").value(hasItem(DEFAULT_HU)))
            .andExpect(jsonPath("$.[*].bc").value(hasItem(DEFAULT_BC)))
            .andExpect(jsonPath("$.[*].sb").value(hasItem(DEFAULT_SB)))
            .andExpect(jsonPath("$.[*].oe").value(hasItem(DEFAULT_OE)))
            .andExpect(jsonPath("$.[*].pres").value(hasItem(DEFAULT_PRES)))
            .andExpect(jsonPath("$.[*].conclusion").value(hasItem(DEFAULT_CONCLUSION)))
            .andExpect(jsonPath("$.[*].traitement").value(hasItem(DEFAULT_TRAITEMENT)))
            .andExpect(jsonPath("$.[*].suiviPar").value(hasItem(DEFAULT_SUIVI_PAR)));
    }
    
    @Test
    @Transactional
    public void getTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        // Get the tabConsObst
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts/{id}", tabConsObst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabConsObst.getId().intValue()))
            .andExpect(jsonPath("$.dateConsObst").value(sameInstant(DEFAULT_DATE_CONS_OBST)))
            .andExpect(jsonPath("$.agePatient").value(DEFAULT_AGE_PATIENT))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.doubleValue()))
            .andExpect(jsonPath("$.tas").value(DEFAULT_TAS.doubleValue()))
            .andExpect(jsonPath("$.tad").value(DEFAULT_TAD.doubleValue()))
            .andExpect(jsonPath("$.a").value(DEFAULT_A.doubleValue()))
            .andExpect(jsonPath("$.s").value(DEFAULT_S))
            .andExpect(jsonPath("$.n").value(DEFAULT_N))
            .andExpect(jsonPath("$.hu").value(DEFAULT_HU))
            .andExpect(jsonPath("$.bc").value(DEFAULT_BC))
            .andExpect(jsonPath("$.sb").value(DEFAULT_SB))
            .andExpect(jsonPath("$.oe").value(DEFAULT_OE))
            .andExpect(jsonPath("$.pres").value(DEFAULT_PRES))
            .andExpect(jsonPath("$.conclusion").value(DEFAULT_CONCLUSION))
            .andExpect(jsonPath("$.traitement").value(DEFAULT_TRAITEMENT))
            .andExpect(jsonPath("$.suiviPar").value(DEFAULT_SUIVI_PAR));
    }
    @Test
    @Transactional
    public void getNonExistingTabConsObst() throws Exception {
        // Get the tabConsObst
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        int databaseSizeBeforeUpdate = tabConsObstRepository.findAll().size();

        // Update the tabConsObst
        TabConsObst updatedTabConsObst = tabConsObstRepository.findById(tabConsObst.getId()).get();
        // Disconnect from session so that the updates on updatedTabConsObst are not directly saved in db
        em.detach(updatedTabConsObst);
        updatedTabConsObst
            .dateConsObst(UPDATED_DATE_CONS_OBST)
            .agePatient(UPDATED_AGE_PATIENT)
            .poids(UPDATED_POIDS)
            .tas(UPDATED_TAS)
            .tad(UPDATED_TAD)
            .a(UPDATED_A)
            .s(UPDATED_S)
            .n(UPDATED_N)
            .hu(UPDATED_HU)
            .bc(UPDATED_BC)
            .sb(UPDATED_SB)
            .oe(UPDATED_OE)
            .pres(UPDATED_PRES)
            .conclusion(UPDATED_CONCLUSION)
            .traitement(UPDATED_TRAITEMENT)
            .suiviPar(UPDATED_SUIVI_PAR);

        restTabConsObstMockMvc.perform(put("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabConsObst)))
            .andExpect(status().isOk());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeUpdate);
        TabConsObst testTabConsObst = tabConsObstList.get(tabConsObstList.size() - 1);
        assertThat(testTabConsObst.getDateConsObst()).isEqualTo(UPDATED_DATE_CONS_OBST);
        assertThat(testTabConsObst.getAgePatient()).isEqualTo(UPDATED_AGE_PATIENT);
        assertThat(testTabConsObst.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testTabConsObst.getTas()).isEqualTo(UPDATED_TAS);
        assertThat(testTabConsObst.getTad()).isEqualTo(UPDATED_TAD);
        assertThat(testTabConsObst.getA()).isEqualTo(UPDATED_A);
        assertThat(testTabConsObst.getS()).isEqualTo(UPDATED_S);
        assertThat(testTabConsObst.getN()).isEqualTo(UPDATED_N);
        assertThat(testTabConsObst.getHu()).isEqualTo(UPDATED_HU);
        assertThat(testTabConsObst.getBc()).isEqualTo(UPDATED_BC);
        assertThat(testTabConsObst.getSb()).isEqualTo(UPDATED_SB);
        assertThat(testTabConsObst.getOe()).isEqualTo(UPDATED_OE);
        assertThat(testTabConsObst.getPres()).isEqualTo(UPDATED_PRES);
        assertThat(testTabConsObst.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testTabConsObst.getTraitement()).isEqualTo(UPDATED_TRAITEMENT);
        assertThat(testTabConsObst.getSuiviPar()).isEqualTo(UPDATED_SUIVI_PAR);
    }

    @Test
    @Transactional
    public void updateNonExistingTabConsObst() throws Exception {
        int databaseSizeBeforeUpdate = tabConsObstRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabConsObstMockMvc.perform(put("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        int databaseSizeBeforeDelete = tabConsObstRepository.findAll().size();

        // Delete the tabConsObst
        restTabConsObstMockMvc.perform(delete("/api/tab-cons-obsts/{id}", tabConsObst.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
