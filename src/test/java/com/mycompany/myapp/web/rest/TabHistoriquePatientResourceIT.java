package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabHistoriquePatient;
import com.mycompany.myapp.repository.TabHistoriquePatientRepository;

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
 * Integration tests for the {@link TabHistoriquePatientResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabHistoriquePatientResourceIT {

    private static final String DEFAULT_NUMERO_DOSSIER = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DOSSIER = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabHistoriquePatientRepository tabHistoriquePatientRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabHistoriquePatientMockMvc;

    private TabHistoriquePatient tabHistoriquePatient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabHistoriquePatient createEntity(EntityManager em) {
        TabHistoriquePatient tabHistoriquePatient = new TabHistoriquePatient()
            .numeroDossier(DEFAULT_NUMERO_DOSSIER)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .matriculeUtilisateur(DEFAULT_MATRICULE_UTILISATEUR)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabHistoriquePatient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabHistoriquePatient createUpdatedEntity(EntityManager em) {
        TabHistoriquePatient tabHistoriquePatient = new TabHistoriquePatient()
            .numeroDossier(UPDATED_NUMERO_DOSSIER)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .matriculeUtilisateur(UPDATED_MATRICULE_UTILISATEUR)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabHistoriquePatient;
    }

    @BeforeEach
    public void initTest() {
        tabHistoriquePatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabHistoriquePatient() throws Exception {
        int databaseSizeBeforeCreate = tabHistoriquePatientRepository.findAll().size();
        // Create the TabHistoriquePatient
        restTabHistoriquePatientMockMvc.perform(post("/api/tab-historique-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHistoriquePatient)))
            .andExpect(status().isCreated());

        // Validate the TabHistoriquePatient in the database
        List<TabHistoriquePatient> tabHistoriquePatientList = tabHistoriquePatientRepository.findAll();
        assertThat(tabHistoriquePatientList).hasSize(databaseSizeBeforeCreate + 1);
        TabHistoriquePatient testTabHistoriquePatient = tabHistoriquePatientList.get(tabHistoriquePatientList.size() - 1);
        assertThat(testTabHistoriquePatient.getNumeroDossier()).isEqualTo(DEFAULT_NUMERO_DOSSIER);
        assertThat(testTabHistoriquePatient.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTabHistoriquePatient.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testTabHistoriquePatient.getMatriculeUtilisateur()).isEqualTo(DEFAULT_MATRICULE_UTILISATEUR);
        assertThat(testTabHistoriquePatient.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabHistoriquePatient.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabHistoriquePatient.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabHistoriquePatient.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabHistoriquePatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabHistoriquePatientRepository.findAll().size();

        // Create the TabHistoriquePatient with an existing ID
        tabHistoriquePatient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabHistoriquePatientMockMvc.perform(post("/api/tab-historique-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHistoriquePatient)))
            .andExpect(status().isBadRequest());

        // Validate the TabHistoriquePatient in the database
        List<TabHistoriquePatient> tabHistoriquePatientList = tabHistoriquePatientRepository.findAll();
        assertThat(tabHistoriquePatientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabHistoriquePatients() throws Exception {
        // Initialize the database
        tabHistoriquePatientRepository.saveAndFlush(tabHistoriquePatient);

        // Get all the tabHistoriquePatientList
        restTabHistoriquePatientMockMvc.perform(get("/api/tab-historique-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabHistoriquePatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroDossier").value(hasItem(DEFAULT_NUMERO_DOSSIER)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].matriculeUtilisateur").value(hasItem(DEFAULT_MATRICULE_UTILISATEUR)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabHistoriquePatient() throws Exception {
        // Initialize the database
        tabHistoriquePatientRepository.saveAndFlush(tabHistoriquePatient);

        // Get the tabHistoriquePatient
        restTabHistoriquePatientMockMvc.perform(get("/api/tab-historique-patients/{id}", tabHistoriquePatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabHistoriquePatient.getId().intValue()))
            .andExpect(jsonPath("$.numeroDossier").value(DEFAULT_NUMERO_DOSSIER))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.matriculeUtilisateur").value(DEFAULT_MATRICULE_UTILISATEUR))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabHistoriquePatient() throws Exception {
        // Get the tabHistoriquePatient
        restTabHistoriquePatientMockMvc.perform(get("/api/tab-historique-patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabHistoriquePatient() throws Exception {
        // Initialize the database
        tabHistoriquePatientRepository.saveAndFlush(tabHistoriquePatient);

        int databaseSizeBeforeUpdate = tabHistoriquePatientRepository.findAll().size();

        // Update the tabHistoriquePatient
        TabHistoriquePatient updatedTabHistoriquePatient = tabHistoriquePatientRepository.findById(tabHistoriquePatient.getId()).get();
        // Disconnect from session so that the updates on updatedTabHistoriquePatient are not directly saved in db
        em.detach(updatedTabHistoriquePatient);
        updatedTabHistoriquePatient
            .numeroDossier(UPDATED_NUMERO_DOSSIER)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .matriculeUtilisateur(UPDATED_MATRICULE_UTILISATEUR)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabHistoriquePatientMockMvc.perform(put("/api/tab-historique-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabHistoriquePatient)))
            .andExpect(status().isOk());

        // Validate the TabHistoriquePatient in the database
        List<TabHistoriquePatient> tabHistoriquePatientList = tabHistoriquePatientRepository.findAll();
        assertThat(tabHistoriquePatientList).hasSize(databaseSizeBeforeUpdate);
        TabHistoriquePatient testTabHistoriquePatient = tabHistoriquePatientList.get(tabHistoriquePatientList.size() - 1);
        assertThat(testTabHistoriquePatient.getNumeroDossier()).isEqualTo(UPDATED_NUMERO_DOSSIER);
        assertThat(testTabHistoriquePatient.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTabHistoriquePatient.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testTabHistoriquePatient.getMatriculeUtilisateur()).isEqualTo(UPDATED_MATRICULE_UTILISATEUR);
        assertThat(testTabHistoriquePatient.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabHistoriquePatient.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabHistoriquePatient.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabHistoriquePatient.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabHistoriquePatient() throws Exception {
        int databaseSizeBeforeUpdate = tabHistoriquePatientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabHistoriquePatientMockMvc.perform(put("/api/tab-historique-patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHistoriquePatient)))
            .andExpect(status().isBadRequest());

        // Validate the TabHistoriquePatient in the database
        List<TabHistoriquePatient> tabHistoriquePatientList = tabHistoriquePatientRepository.findAll();
        assertThat(tabHistoriquePatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabHistoriquePatient() throws Exception {
        // Initialize the database
        tabHistoriquePatientRepository.saveAndFlush(tabHistoriquePatient);

        int databaseSizeBeforeDelete = tabHistoriquePatientRepository.findAll().size();

        // Delete the tabHistoriquePatient
        restTabHistoriquePatientMockMvc.perform(delete("/api/tab-historique-patients/{id}", tabHistoriquePatient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabHistoriquePatient> tabHistoriquePatientList = tabHistoriquePatientRepository.findAll();
        assertThat(tabHistoriquePatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
