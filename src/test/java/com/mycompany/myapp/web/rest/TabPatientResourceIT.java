package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabPatient;
import com.mycompany.myapp.repository.TabPatientRepository;

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
 * Integration tests for the {@link TabPatientResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabPatientResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_CIVIL = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_CIVIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NOMBRE_ENFANT = 1;
    private static final Integer UPDATED_NOMBRE_ENFANT = 2;

    private static final String DEFAULT_NOMBRE_GROSSESSE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_GROSSESSE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_2 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATENAISSANCE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATENAISSANCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIEUNAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_LIEUNAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_URL = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabPatientRepository tabPatientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabPatientMockMvc;

    private TabPatient tabPatient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabPatient createEntity(EntityManager em) {
        TabPatient tabPatient = new TabPatient()
            .matricule(DEFAULT_MATRICULE)
            .sexe(DEFAULT_SEXE)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .nombreEnfant(DEFAULT_NOMBRE_ENFANT)
            .nombreGrossesse(DEFAULT_NOMBRE_GROSSESSE)
            .nom(DEFAULT_NOM)
            .prenom1(DEFAULT_PRENOM_1)
            .prenom2(DEFAULT_PRENOM_2)
            .datenaissance(DEFAULT_DATENAISSANCE)
            .lieunaissance(DEFAULT_LIEUNAISSANCE)
            .nationalite(DEFAULT_NATIONALITE)
            .activite(DEFAULT_ACTIVITE)
            .photoUrl(DEFAULT_PHOTO_URL)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabPatient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabPatient createUpdatedEntity(EntityManager em) {
        TabPatient tabPatient = new TabPatient()
            .matricule(UPDATED_MATRICULE)
            .sexe(UPDATED_SEXE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .nombreEnfant(UPDATED_NOMBRE_ENFANT)
            .nombreGrossesse(UPDATED_NOMBRE_GROSSESSE)
            .nom(UPDATED_NOM)
            .prenom1(UPDATED_PRENOM_1)
            .prenom2(UPDATED_PRENOM_2)
            .datenaissance(UPDATED_DATENAISSANCE)
            .lieunaissance(UPDATED_LIEUNAISSANCE)
            .nationalite(UPDATED_NATIONALITE)
            .activite(UPDATED_ACTIVITE)
            .photoUrl(UPDATED_PHOTO_URL)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabPatient;
    }

    @BeforeEach
    public void initTest() {
        tabPatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabPatient() throws Exception {
        int databaseSizeBeforeCreate = tabPatientRepository.findAll().size();
        // Create the TabPatient
        restTabPatientMockMvc.perform(post("/api/tab-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPatient)))
            .andExpect(status().isCreated());

        // Validate the TabPatient in the database
        List<TabPatient> tabPatientList = tabPatientRepository.findAll();
        assertThat(tabPatientList).hasSize(databaseSizeBeforeCreate + 1);
        TabPatient testTabPatient = tabPatientList.get(tabPatientList.size() - 1);
        assertThat(testTabPatient.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testTabPatient.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testTabPatient.getEtatCivil()).isEqualTo(DEFAULT_ETAT_CIVIL);
        assertThat(testTabPatient.getNombreEnfant()).isEqualTo(DEFAULT_NOMBRE_ENFANT);
        assertThat(testTabPatient.getNombreGrossesse()).isEqualTo(DEFAULT_NOMBRE_GROSSESSE);
        assertThat(testTabPatient.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTabPatient.getPrenom1()).isEqualTo(DEFAULT_PRENOM_1);
        assertThat(testTabPatient.getPrenom2()).isEqualTo(DEFAULT_PRENOM_2);
        assertThat(testTabPatient.getDatenaissance()).isEqualTo(DEFAULT_DATENAISSANCE);
        assertThat(testTabPatient.getLieunaissance()).isEqualTo(DEFAULT_LIEUNAISSANCE);
        assertThat(testTabPatient.getNationalite()).isEqualTo(DEFAULT_NATIONALITE);
        assertThat(testTabPatient.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
        assertThat(testTabPatient.getPhotoUrl()).isEqualTo(DEFAULT_PHOTO_URL);
        assertThat(testTabPatient.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabPatient.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabPatient.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabPatient.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabPatientRepository.findAll().size();

        // Create the TabPatient with an existing ID
        tabPatient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabPatientMockMvc.perform(post("/api/tab-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPatient)))
            .andExpect(status().isBadRequest());

        // Validate the TabPatient in the database
        List<TabPatient> tabPatientList = tabPatientRepository.findAll();
        assertThat(tabPatientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabPatients() throws Exception {
        // Initialize the database
        tabPatientRepository.saveAndFlush(tabPatient);

        // Get all the tabPatientList
        restTabPatientMockMvc.perform(get("/api/tab-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabPatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE)))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL)))
            .andExpect(jsonPath("$.[*].nombreEnfant").value(hasItem(DEFAULT_NOMBRE_ENFANT)))
            .andExpect(jsonPath("$.[*].nombreGrossesse").value(hasItem(DEFAULT_NOMBRE_GROSSESSE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom1").value(hasItem(DEFAULT_PRENOM_1)))
            .andExpect(jsonPath("$.[*].prenom2").value(hasItem(DEFAULT_PRENOM_2)))
            .andExpect(jsonPath("$.[*].datenaissance").value(hasItem(sameInstant(DEFAULT_DATENAISSANCE))))
            .andExpect(jsonPath("$.[*].lieunaissance").value(hasItem(DEFAULT_LIEUNAISSANCE)))
            .andExpect(jsonPath("$.[*].nationalite").value(hasItem(DEFAULT_NATIONALITE)))
            .andExpect(jsonPath("$.[*].activite").value(hasItem(DEFAULT_ACTIVITE)))
            .andExpect(jsonPath("$.[*].photoUrl").value(hasItem(DEFAULT_PHOTO_URL)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabPatient() throws Exception {
        // Initialize the database
        tabPatientRepository.saveAndFlush(tabPatient);

        // Get the tabPatient
        restTabPatientMockMvc.perform(get("/api/tab-patients/{id}", tabPatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabPatient.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL))
            .andExpect(jsonPath("$.nombreEnfant").value(DEFAULT_NOMBRE_ENFANT))
            .andExpect(jsonPath("$.nombreGrossesse").value(DEFAULT_NOMBRE_GROSSESSE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom1").value(DEFAULT_PRENOM_1))
            .andExpect(jsonPath("$.prenom2").value(DEFAULT_PRENOM_2))
            .andExpect(jsonPath("$.datenaissance").value(sameInstant(DEFAULT_DATENAISSANCE)))
            .andExpect(jsonPath("$.lieunaissance").value(DEFAULT_LIEUNAISSANCE))
            .andExpect(jsonPath("$.nationalite").value(DEFAULT_NATIONALITE))
            .andExpect(jsonPath("$.activite").value(DEFAULT_ACTIVITE))
            .andExpect(jsonPath("$.photoUrl").value(DEFAULT_PHOTO_URL))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabPatient() throws Exception {
        // Get the tabPatient
        restTabPatientMockMvc.perform(get("/api/tab-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabPatient() throws Exception {
        // Initialize the database
        tabPatientRepository.saveAndFlush(tabPatient);

        int databaseSizeBeforeUpdate = tabPatientRepository.findAll().size();

        // Update the tabPatient
        TabPatient updatedTabPatient = tabPatientRepository.findById(tabPatient.getId()).get();
        // Disconnect from session so that the updates on updatedTabPatient are not directly saved in db
        em.detach(updatedTabPatient);
        updatedTabPatient
            .matricule(UPDATED_MATRICULE)
            .sexe(UPDATED_SEXE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .nombreEnfant(UPDATED_NOMBRE_ENFANT)
            .nombreGrossesse(UPDATED_NOMBRE_GROSSESSE)
            .nom(UPDATED_NOM)
            .prenom1(UPDATED_PRENOM_1)
            .prenom2(UPDATED_PRENOM_2)
            .datenaissance(UPDATED_DATENAISSANCE)
            .lieunaissance(UPDATED_LIEUNAISSANCE)
            .nationalite(UPDATED_NATIONALITE)
            .activite(UPDATED_ACTIVITE)
            .photoUrl(UPDATED_PHOTO_URL)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabPatientMockMvc.perform(put("/api/tab-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabPatient)))
            .andExpect(status().isOk());

        // Validate the TabPatient in the database
        List<TabPatient> tabPatientList = tabPatientRepository.findAll();
        assertThat(tabPatientList).hasSize(databaseSizeBeforeUpdate);
        TabPatient testTabPatient = tabPatientList.get(tabPatientList.size() - 1);
        assertThat(testTabPatient.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testTabPatient.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testTabPatient.getEtatCivil()).isEqualTo(UPDATED_ETAT_CIVIL);
        assertThat(testTabPatient.getNombreEnfant()).isEqualTo(UPDATED_NOMBRE_ENFANT);
        assertThat(testTabPatient.getNombreGrossesse()).isEqualTo(UPDATED_NOMBRE_GROSSESSE);
        assertThat(testTabPatient.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTabPatient.getPrenom1()).isEqualTo(UPDATED_PRENOM_1);
        assertThat(testTabPatient.getPrenom2()).isEqualTo(UPDATED_PRENOM_2);
        assertThat(testTabPatient.getDatenaissance()).isEqualTo(UPDATED_DATENAISSANCE);
        assertThat(testTabPatient.getLieunaissance()).isEqualTo(UPDATED_LIEUNAISSANCE);
        assertThat(testTabPatient.getNationalite()).isEqualTo(UPDATED_NATIONALITE);
        assertThat(testTabPatient.getActivite()).isEqualTo(UPDATED_ACTIVITE);
        assertThat(testTabPatient.getPhotoUrl()).isEqualTo(UPDATED_PHOTO_URL);
        assertThat(testTabPatient.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabPatient.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabPatient.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabPatient.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabPatient() throws Exception {
        int databaseSizeBeforeUpdate = tabPatientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabPatientMockMvc.perform(put("/api/tab-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPatient)))
            .andExpect(status().isBadRequest());

        // Validate the TabPatient in the database
        List<TabPatient> tabPatientList = tabPatientRepository.findAll();
        assertThat(tabPatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabPatient() throws Exception {
        // Initialize the database
        tabPatientRepository.saveAndFlush(tabPatient);

        int databaseSizeBeforeDelete = tabPatientRepository.findAll().size();

        // Delete the tabPatient
        restTabPatientMockMvc.perform(delete("/api/tab-patients/{id}", tabPatient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabPatient> tabPatientList = tabPatientRepository.findAll();
        assertThat(tabPatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
