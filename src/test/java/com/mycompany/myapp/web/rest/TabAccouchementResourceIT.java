package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabAccouchement;
import com.mycompany.myapp.repository.TabAccouchementRepository;

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
 * Integration tests for the {@link TabAccouchementResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabAccouchementResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_ACCOUCHE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_ACCOUCHE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULE_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_SEXE_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENON_1_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_PRENON_1_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENON_2_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_PRENON_2_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MERE = "BBBBBBBBBB";

    private static final Float DEFAULT_AGE_BEBE = 1F;
    private static final Float UPDATED_AGE_BEBE = 2F;

    private static final Float DEFAULT_TA_1_BEBE = 1F;
    private static final Float UPDATED_TA_1_BEBE = 2F;

    private static final Float DEFAULT_TA_2_BEBE = 1F;
    private static final Float UPDATED_TA_2_BEBE = 2F;

    private static final String DEFAULT_POIDS_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_POIDS_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_TAILLE_BEBE = "AAAAAAAAAA";
    private static final String UPDATED_TAILLE_BEBE = "BBBBBBBBBB";

    private static final String DEFAULT_ALLAITEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ALLAITEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CONCLUSION = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION = "BBBBBBBBBB";

    @Autowired
    private TabAccouchementRepository tabAccouchementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabAccouchementMockMvc;

    private TabAccouchement tabAccouchement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabAccouchement createEntity(EntityManager em) {
        TabAccouchement tabAccouchement = new TabAccouchement()
            .dateAccouche(DEFAULT_DATE_ACCOUCHE)
            .matriculeBebe(DEFAULT_MATRICULE_BEBE)
            .sexeBebe(DEFAULT_SEXE_BEBE)
            .nomBebe(DEFAULT_NOM_BEBE)
            .prenon1Bebe(DEFAULT_PRENON_1_BEBE)
            .prenon2Bebe(DEFAULT_PRENON_2_BEBE)
            .nomMere(DEFAULT_NOM_MERE)
            .ageBebe(DEFAULT_AGE_BEBE)
            .ta1Bebe(DEFAULT_TA_1_BEBE)
            .ta2Bebe(DEFAULT_TA_2_BEBE)
            .poidsBebe(DEFAULT_POIDS_BEBE)
            .tailleBebe(DEFAULT_TAILLE_BEBE)
            .allaitement(DEFAULT_ALLAITEMENT)
            .conclusion(DEFAULT_CONCLUSION);
        return tabAccouchement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabAccouchement createUpdatedEntity(EntityManager em) {
        TabAccouchement tabAccouchement = new TabAccouchement()
            .dateAccouche(UPDATED_DATE_ACCOUCHE)
            .matriculeBebe(UPDATED_MATRICULE_BEBE)
            .sexeBebe(UPDATED_SEXE_BEBE)
            .nomBebe(UPDATED_NOM_BEBE)
            .prenon1Bebe(UPDATED_PRENON_1_BEBE)
            .prenon2Bebe(UPDATED_PRENON_2_BEBE)
            .nomMere(UPDATED_NOM_MERE)
            .ageBebe(UPDATED_AGE_BEBE)
            .ta1Bebe(UPDATED_TA_1_BEBE)
            .ta2Bebe(UPDATED_TA_2_BEBE)
            .poidsBebe(UPDATED_POIDS_BEBE)
            .tailleBebe(UPDATED_TAILLE_BEBE)
            .allaitement(UPDATED_ALLAITEMENT)
            .conclusion(UPDATED_CONCLUSION);
        return tabAccouchement;
    }

    @BeforeEach
    public void initTest() {
        tabAccouchement = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabAccouchement() throws Exception {
        int databaseSizeBeforeCreate = tabAccouchementRepository.findAll().size();
        // Create the TabAccouchement
        restTabAccouchementMockMvc.perform(post("/api/tab-accouchements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAccouchement)))
            .andExpect(status().isCreated());

        // Validate the TabAccouchement in the database
        List<TabAccouchement> tabAccouchementList = tabAccouchementRepository.findAll();
        assertThat(tabAccouchementList).hasSize(databaseSizeBeforeCreate + 1);
        TabAccouchement testTabAccouchement = tabAccouchementList.get(tabAccouchementList.size() - 1);
        assertThat(testTabAccouchement.getDateAccouche()).isEqualTo(DEFAULT_DATE_ACCOUCHE);
        assertThat(testTabAccouchement.getMatriculeBebe()).isEqualTo(DEFAULT_MATRICULE_BEBE);
        assertThat(testTabAccouchement.getSexeBebe()).isEqualTo(DEFAULT_SEXE_BEBE);
        assertThat(testTabAccouchement.getNomBebe()).isEqualTo(DEFAULT_NOM_BEBE);
        assertThat(testTabAccouchement.getPrenon1Bebe()).isEqualTo(DEFAULT_PRENON_1_BEBE);
        assertThat(testTabAccouchement.getPrenon2Bebe()).isEqualTo(DEFAULT_PRENON_2_BEBE);
        assertThat(testTabAccouchement.getNomMere()).isEqualTo(DEFAULT_NOM_MERE);
        assertThat(testTabAccouchement.getAgeBebe()).isEqualTo(DEFAULT_AGE_BEBE);
        assertThat(testTabAccouchement.getTa1Bebe()).isEqualTo(DEFAULT_TA_1_BEBE);
        assertThat(testTabAccouchement.getTa2Bebe()).isEqualTo(DEFAULT_TA_2_BEBE);
        assertThat(testTabAccouchement.getPoidsBebe()).isEqualTo(DEFAULT_POIDS_BEBE);
        assertThat(testTabAccouchement.getTailleBebe()).isEqualTo(DEFAULT_TAILLE_BEBE);
        assertThat(testTabAccouchement.getAllaitement()).isEqualTo(DEFAULT_ALLAITEMENT);
        assertThat(testTabAccouchement.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
    }

    @Test
    @Transactional
    public void createTabAccouchementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabAccouchementRepository.findAll().size();

        // Create the TabAccouchement with an existing ID
        tabAccouchement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabAccouchementMockMvc.perform(post("/api/tab-accouchements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAccouchement)))
            .andExpect(status().isBadRequest());

        // Validate the TabAccouchement in the database
        List<TabAccouchement> tabAccouchementList = tabAccouchementRepository.findAll();
        assertThat(tabAccouchementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabAccouchements() throws Exception {
        // Initialize the database
        tabAccouchementRepository.saveAndFlush(tabAccouchement);

        // Get all the tabAccouchementList
        restTabAccouchementMockMvc.perform(get("/api/tab-accouchements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabAccouchement.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateAccouche").value(hasItem(sameInstant(DEFAULT_DATE_ACCOUCHE))))
            .andExpect(jsonPath("$.[*].matriculeBebe").value(hasItem(DEFAULT_MATRICULE_BEBE)))
            .andExpect(jsonPath("$.[*].sexeBebe").value(hasItem(DEFAULT_SEXE_BEBE)))
            .andExpect(jsonPath("$.[*].nomBebe").value(hasItem(DEFAULT_NOM_BEBE)))
            .andExpect(jsonPath("$.[*].prenon1Bebe").value(hasItem(DEFAULT_PRENON_1_BEBE)))
            .andExpect(jsonPath("$.[*].prenon2Bebe").value(hasItem(DEFAULT_PRENON_2_BEBE)))
            .andExpect(jsonPath("$.[*].nomMere").value(hasItem(DEFAULT_NOM_MERE)))
            .andExpect(jsonPath("$.[*].ageBebe").value(hasItem(DEFAULT_AGE_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].ta1Bebe").value(hasItem(DEFAULT_TA_1_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].ta2Bebe").value(hasItem(DEFAULT_TA_2_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].poidsBebe").value(hasItem(DEFAULT_POIDS_BEBE)))
            .andExpect(jsonPath("$.[*].tailleBebe").value(hasItem(DEFAULT_TAILLE_BEBE)))
            .andExpect(jsonPath("$.[*].allaitement").value(hasItem(DEFAULT_ALLAITEMENT)))
            .andExpect(jsonPath("$.[*].conclusion").value(hasItem(DEFAULT_CONCLUSION)));
    }
    
    @Test
    @Transactional
    public void getTabAccouchement() throws Exception {
        // Initialize the database
        tabAccouchementRepository.saveAndFlush(tabAccouchement);

        // Get the tabAccouchement
        restTabAccouchementMockMvc.perform(get("/api/tab-accouchements/{id}", tabAccouchement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabAccouchement.getId().intValue()))
            .andExpect(jsonPath("$.dateAccouche").value(sameInstant(DEFAULT_DATE_ACCOUCHE)))
            .andExpect(jsonPath("$.matriculeBebe").value(DEFAULT_MATRICULE_BEBE))
            .andExpect(jsonPath("$.sexeBebe").value(DEFAULT_SEXE_BEBE))
            .andExpect(jsonPath("$.nomBebe").value(DEFAULT_NOM_BEBE))
            .andExpect(jsonPath("$.prenon1Bebe").value(DEFAULT_PRENON_1_BEBE))
            .andExpect(jsonPath("$.prenon2Bebe").value(DEFAULT_PRENON_2_BEBE))
            .andExpect(jsonPath("$.nomMere").value(DEFAULT_NOM_MERE))
            .andExpect(jsonPath("$.ageBebe").value(DEFAULT_AGE_BEBE.doubleValue()))
            .andExpect(jsonPath("$.ta1Bebe").value(DEFAULT_TA_1_BEBE.doubleValue()))
            .andExpect(jsonPath("$.ta2Bebe").value(DEFAULT_TA_2_BEBE.doubleValue()))
            .andExpect(jsonPath("$.poidsBebe").value(DEFAULT_POIDS_BEBE))
            .andExpect(jsonPath("$.tailleBebe").value(DEFAULT_TAILLE_BEBE))
            .andExpect(jsonPath("$.allaitement").value(DEFAULT_ALLAITEMENT))
            .andExpect(jsonPath("$.conclusion").value(DEFAULT_CONCLUSION));
    }
    @Test
    @Transactional
    public void getNonExistingTabAccouchement() throws Exception {
        // Get the tabAccouchement
        restTabAccouchementMockMvc.perform(get("/api/tab-accouchements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabAccouchement() throws Exception {
        // Initialize the database
        tabAccouchementRepository.saveAndFlush(tabAccouchement);

        int databaseSizeBeforeUpdate = tabAccouchementRepository.findAll().size();

        // Update the tabAccouchement
        TabAccouchement updatedTabAccouchement = tabAccouchementRepository.findById(tabAccouchement.getId()).get();
        // Disconnect from session so that the updates on updatedTabAccouchement are not directly saved in db
        em.detach(updatedTabAccouchement);
        updatedTabAccouchement
            .dateAccouche(UPDATED_DATE_ACCOUCHE)
            .matriculeBebe(UPDATED_MATRICULE_BEBE)
            .sexeBebe(UPDATED_SEXE_BEBE)
            .nomBebe(UPDATED_NOM_BEBE)
            .prenon1Bebe(UPDATED_PRENON_1_BEBE)
            .prenon2Bebe(UPDATED_PRENON_2_BEBE)
            .nomMere(UPDATED_NOM_MERE)
            .ageBebe(UPDATED_AGE_BEBE)
            .ta1Bebe(UPDATED_TA_1_BEBE)
            .ta2Bebe(UPDATED_TA_2_BEBE)
            .poidsBebe(UPDATED_POIDS_BEBE)
            .tailleBebe(UPDATED_TAILLE_BEBE)
            .allaitement(UPDATED_ALLAITEMENT)
            .conclusion(UPDATED_CONCLUSION);

        restTabAccouchementMockMvc.perform(put("/api/tab-accouchements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabAccouchement)))
            .andExpect(status().isOk());

        // Validate the TabAccouchement in the database
        List<TabAccouchement> tabAccouchementList = tabAccouchementRepository.findAll();
        assertThat(tabAccouchementList).hasSize(databaseSizeBeforeUpdate);
        TabAccouchement testTabAccouchement = tabAccouchementList.get(tabAccouchementList.size() - 1);
        assertThat(testTabAccouchement.getDateAccouche()).isEqualTo(UPDATED_DATE_ACCOUCHE);
        assertThat(testTabAccouchement.getMatriculeBebe()).isEqualTo(UPDATED_MATRICULE_BEBE);
        assertThat(testTabAccouchement.getSexeBebe()).isEqualTo(UPDATED_SEXE_BEBE);
        assertThat(testTabAccouchement.getNomBebe()).isEqualTo(UPDATED_NOM_BEBE);
        assertThat(testTabAccouchement.getPrenon1Bebe()).isEqualTo(UPDATED_PRENON_1_BEBE);
        assertThat(testTabAccouchement.getPrenon2Bebe()).isEqualTo(UPDATED_PRENON_2_BEBE);
        assertThat(testTabAccouchement.getNomMere()).isEqualTo(UPDATED_NOM_MERE);
        assertThat(testTabAccouchement.getAgeBebe()).isEqualTo(UPDATED_AGE_BEBE);
        assertThat(testTabAccouchement.getTa1Bebe()).isEqualTo(UPDATED_TA_1_BEBE);
        assertThat(testTabAccouchement.getTa2Bebe()).isEqualTo(UPDATED_TA_2_BEBE);
        assertThat(testTabAccouchement.getPoidsBebe()).isEqualTo(UPDATED_POIDS_BEBE);
        assertThat(testTabAccouchement.getTailleBebe()).isEqualTo(UPDATED_TAILLE_BEBE);
        assertThat(testTabAccouchement.getAllaitement()).isEqualTo(UPDATED_ALLAITEMENT);
        assertThat(testTabAccouchement.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
    }

    @Test
    @Transactional
    public void updateNonExistingTabAccouchement() throws Exception {
        int databaseSizeBeforeUpdate = tabAccouchementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabAccouchementMockMvc.perform(put("/api/tab-accouchements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAccouchement)))
            .andExpect(status().isBadRequest());

        // Validate the TabAccouchement in the database
        List<TabAccouchement> tabAccouchementList = tabAccouchementRepository.findAll();
        assertThat(tabAccouchementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabAccouchement() throws Exception {
        // Initialize the database
        tabAccouchementRepository.saveAndFlush(tabAccouchement);

        int databaseSizeBeforeDelete = tabAccouchementRepository.findAll().size();

        // Delete the tabAccouchement
        restTabAccouchementMockMvc.perform(delete("/api/tab-accouchements/{id}", tabAccouchement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabAccouchement> tabAccouchementList = tabAccouchementRepository.findAll();
        assertThat(tabAccouchementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
