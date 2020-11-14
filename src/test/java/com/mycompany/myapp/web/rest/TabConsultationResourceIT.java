package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabConsultation;
import com.mycompany.myapp.repository.TabConsultationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.ProvenancePatient;
import com.mycompany.myapp.domain.enumeration.LedevenirPatient;
/**
 * Integration tests for the {@link TabConsultationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabConsultationResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_CONSULTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CONSULTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ProvenancePatient DEFAULT_PROVENANCE_PATIENT = ProvenancePatient.Domicile;
    private static final ProvenancePatient UPDATED_PROVENANCE_PATIENT = ProvenancePatient.Urgence;

    private static final String DEFAULT_MOTIF = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF = "BBBBBBBBBB";

    private static final String DEFAULT_AFFECT_ACTUEL = "AAAAAAAAAA";
    private static final String UPDATED_AFFECT_ACTUEL = "BBBBBBBBBB";

    private static final String DEFAULT_ANTECEDENT = "AAAAAAAAAA";
    private static final String UPDATED_ANTECEDENT = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITE_HABITUEL = "AAAAAAAAAA";
    private static final String UPDATED_TRAITE_HABITUEL = "BBBBBBBBBB";

    private static final String DEFAULT_ALLERGIE = "AAAAAAAAAA";
    private static final String UPDATED_ALLERGIE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TABAC = false;
    private static final Boolean UPDATED_TABAC = true;

    private static final Boolean DEFAULT_ALCOOL = false;
    private static final Boolean UPDATED_ALCOOL = true;

    private static final String DEFAULT_FACTEUR_RISQUE = "AAAAAAAAAA";
    private static final String UPDATED_FACTEUR_RISQUE = "BBBBBBBBBB";

    private static final String DEFAULT_HYPOTHESE_DIAG = "AAAAAAAAAA";
    private static final String UPDATED_HYPOTHESE_DIAG = "BBBBBBBBBB";

    private static final String DEFAULT_AVIS_MEDICAL = "AAAAAAAAAA";
    private static final String UPDATED_AVIS_MEDICAL = "BBBBBBBBBB";

    private static final String DEFAULT_ORDRE_MEDICAL = "AAAAAAAAAA";
    private static final String UPDATED_ORDRE_MEDICAL = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITE_PROPOSE = "AAAAAAAAAA";
    private static final String UPDATED_TRAITE_PROPOSE = "BBBBBBBBBB";

    private static final LedevenirPatient DEFAULT_LEDEVENIR_PATIENT = LedevenirPatient.TransHopi;
    private static final LedevenirPatient UPDATED_LEDEVENIR_PATIENT = LedevenirPatient.TransAutreHopi;

    private static final ZonedDateTime DEFAULT_TARIF_CONSULT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TARIF_CONSULT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabConsultationRepository tabConsultationRepository;

    @Mock
    private TabConsultationRepository tabConsultationRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabConsultationMockMvc;

    private TabConsultation tabConsultation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsultation createEntity(EntityManager em) {
        TabConsultation tabConsultation = new TabConsultation()
            .dateConsulte(DEFAULT_DATE_CONSULTE)
            .provenancePatient(DEFAULT_PROVENANCE_PATIENT)
            .motif(DEFAULT_MOTIF)
            .affectActuel(DEFAULT_AFFECT_ACTUEL)
            .antecedent(DEFAULT_ANTECEDENT)
            .traiteHabituel(DEFAULT_TRAITE_HABITUEL)
            .allergie(DEFAULT_ALLERGIE)
            .tabac(DEFAULT_TABAC)
            .alcool(DEFAULT_ALCOOL)
            .facteurRisque(DEFAULT_FACTEUR_RISQUE)
            .hypotheseDiag(DEFAULT_HYPOTHESE_DIAG)
            .avisMedical(DEFAULT_AVIS_MEDICAL)
            .ordreMedical(DEFAULT_ORDRE_MEDICAL)
            .traitePropose(DEFAULT_TRAITE_PROPOSE)
            .ledevenirPatient(DEFAULT_LEDEVENIR_PATIENT)
            .tarifConsult(DEFAULT_TARIF_CONSULT)
            .rapport(DEFAULT_RAPPORT);
        return tabConsultation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsultation createUpdatedEntity(EntityManager em) {
        TabConsultation tabConsultation = new TabConsultation()
            .dateConsulte(UPDATED_DATE_CONSULTE)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .motif(UPDATED_MOTIF)
            .affectActuel(UPDATED_AFFECT_ACTUEL)
            .antecedent(UPDATED_ANTECEDENT)
            .traiteHabituel(UPDATED_TRAITE_HABITUEL)
            .allergie(UPDATED_ALLERGIE)
            .tabac(UPDATED_TABAC)
            .alcool(UPDATED_ALCOOL)
            .facteurRisque(UPDATED_FACTEUR_RISQUE)
            .hypotheseDiag(UPDATED_HYPOTHESE_DIAG)
            .avisMedical(UPDATED_AVIS_MEDICAL)
            .ordreMedical(UPDATED_ORDRE_MEDICAL)
            .traitePropose(UPDATED_TRAITE_PROPOSE)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .tarifConsult(UPDATED_TARIF_CONSULT)
            .rapport(UPDATED_RAPPORT);
        return tabConsultation;
    }

    @BeforeEach
    public void initTest() {
        tabConsultation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabConsultation() throws Exception {
        int databaseSizeBeforeCreate = tabConsultationRepository.findAll().size();
        // Create the TabConsultation
        restTabConsultationMockMvc.perform(post("/api/tab-consultations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsultation)))
            .andExpect(status().isCreated());

        // Validate the TabConsultation in the database
        List<TabConsultation> tabConsultationList = tabConsultationRepository.findAll();
        assertThat(tabConsultationList).hasSize(databaseSizeBeforeCreate + 1);
        TabConsultation testTabConsultation = tabConsultationList.get(tabConsultationList.size() - 1);
        assertThat(testTabConsultation.getDateConsulte()).isEqualTo(DEFAULT_DATE_CONSULTE);
        assertThat(testTabConsultation.getProvenancePatient()).isEqualTo(DEFAULT_PROVENANCE_PATIENT);
        assertThat(testTabConsultation.getMotif()).isEqualTo(DEFAULT_MOTIF);
        assertThat(testTabConsultation.getAffectActuel()).isEqualTo(DEFAULT_AFFECT_ACTUEL);
        assertThat(testTabConsultation.getAntecedent()).isEqualTo(DEFAULT_ANTECEDENT);
        assertThat(testTabConsultation.getTraiteHabituel()).isEqualTo(DEFAULT_TRAITE_HABITUEL);
        assertThat(testTabConsultation.getAllergie()).isEqualTo(DEFAULT_ALLERGIE);
        assertThat(testTabConsultation.isTabac()).isEqualTo(DEFAULT_TABAC);
        assertThat(testTabConsultation.isAlcool()).isEqualTo(DEFAULT_ALCOOL);
        assertThat(testTabConsultation.getFacteurRisque()).isEqualTo(DEFAULT_FACTEUR_RISQUE);
        assertThat(testTabConsultation.getHypotheseDiag()).isEqualTo(DEFAULT_HYPOTHESE_DIAG);
        assertThat(testTabConsultation.getAvisMedical()).isEqualTo(DEFAULT_AVIS_MEDICAL);
        assertThat(testTabConsultation.getOrdreMedical()).isEqualTo(DEFAULT_ORDRE_MEDICAL);
        assertThat(testTabConsultation.getTraitePropose()).isEqualTo(DEFAULT_TRAITE_PROPOSE);
        assertThat(testTabConsultation.getLedevenirPatient()).isEqualTo(DEFAULT_LEDEVENIR_PATIENT);
        assertThat(testTabConsultation.getTarifConsult()).isEqualTo(DEFAULT_TARIF_CONSULT);
        assertThat(testTabConsultation.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabConsultationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabConsultationRepository.findAll().size();

        // Create the TabConsultation with an existing ID
        tabConsultation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabConsultationMockMvc.perform(post("/api/tab-consultations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsultation)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsultation in the database
        List<TabConsultation> tabConsultationList = tabConsultationRepository.findAll();
        assertThat(tabConsultationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabConsultations() throws Exception {
        // Initialize the database
        tabConsultationRepository.saveAndFlush(tabConsultation);

        // Get all the tabConsultationList
        restTabConsultationMockMvc.perform(get("/api/tab-consultations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabConsultation.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateConsulte").value(hasItem(sameInstant(DEFAULT_DATE_CONSULTE))))
            .andExpect(jsonPath("$.[*].provenancePatient").value(hasItem(DEFAULT_PROVENANCE_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].motif").value(hasItem(DEFAULT_MOTIF)))
            .andExpect(jsonPath("$.[*].affectActuel").value(hasItem(DEFAULT_AFFECT_ACTUEL)))
            .andExpect(jsonPath("$.[*].antecedent").value(hasItem(DEFAULT_ANTECEDENT)))
            .andExpect(jsonPath("$.[*].traiteHabituel").value(hasItem(DEFAULT_TRAITE_HABITUEL)))
            .andExpect(jsonPath("$.[*].allergie").value(hasItem(DEFAULT_ALLERGIE)))
            .andExpect(jsonPath("$.[*].tabac").value(hasItem(DEFAULT_TABAC.booleanValue())))
            .andExpect(jsonPath("$.[*].alcool").value(hasItem(DEFAULT_ALCOOL.booleanValue())))
            .andExpect(jsonPath("$.[*].facteurRisque").value(hasItem(DEFAULT_FACTEUR_RISQUE)))
            .andExpect(jsonPath("$.[*].hypotheseDiag").value(hasItem(DEFAULT_HYPOTHESE_DIAG)))
            .andExpect(jsonPath("$.[*].avisMedical").value(hasItem(DEFAULT_AVIS_MEDICAL)))
            .andExpect(jsonPath("$.[*].ordreMedical").value(hasItem(DEFAULT_ORDRE_MEDICAL)))
            .andExpect(jsonPath("$.[*].traitePropose").value(hasItem(DEFAULT_TRAITE_PROPOSE)))
            .andExpect(jsonPath("$.[*].ledevenirPatient").value(hasItem(DEFAULT_LEDEVENIR_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].tarifConsult").value(hasItem(sameInstant(DEFAULT_TARIF_CONSULT))))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllTabConsultationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(tabConsultationRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTabConsultationMockMvc.perform(get("/api/tab-consultations?eagerload=true"))
            .andExpect(status().isOk());

        verify(tabConsultationRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllTabConsultationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(tabConsultationRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTabConsultationMockMvc.perform(get("/api/tab-consultations?eagerload=true"))
            .andExpect(status().isOk());

        verify(tabConsultationRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTabConsultation() throws Exception {
        // Initialize the database
        tabConsultationRepository.saveAndFlush(tabConsultation);

        // Get the tabConsultation
        restTabConsultationMockMvc.perform(get("/api/tab-consultations/{id}", tabConsultation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabConsultation.getId().intValue()))
            .andExpect(jsonPath("$.dateConsulte").value(sameInstant(DEFAULT_DATE_CONSULTE)))
            .andExpect(jsonPath("$.provenancePatient").value(DEFAULT_PROVENANCE_PATIENT.toString()))
            .andExpect(jsonPath("$.motif").value(DEFAULT_MOTIF))
            .andExpect(jsonPath("$.affectActuel").value(DEFAULT_AFFECT_ACTUEL))
            .andExpect(jsonPath("$.antecedent").value(DEFAULT_ANTECEDENT))
            .andExpect(jsonPath("$.traiteHabituel").value(DEFAULT_TRAITE_HABITUEL))
            .andExpect(jsonPath("$.allergie").value(DEFAULT_ALLERGIE))
            .andExpect(jsonPath("$.tabac").value(DEFAULT_TABAC.booleanValue()))
            .andExpect(jsonPath("$.alcool").value(DEFAULT_ALCOOL.booleanValue()))
            .andExpect(jsonPath("$.facteurRisque").value(DEFAULT_FACTEUR_RISQUE))
            .andExpect(jsonPath("$.hypotheseDiag").value(DEFAULT_HYPOTHESE_DIAG))
            .andExpect(jsonPath("$.avisMedical").value(DEFAULT_AVIS_MEDICAL))
            .andExpect(jsonPath("$.ordreMedical").value(DEFAULT_ORDRE_MEDICAL))
            .andExpect(jsonPath("$.traitePropose").value(DEFAULT_TRAITE_PROPOSE))
            .andExpect(jsonPath("$.ledevenirPatient").value(DEFAULT_LEDEVENIR_PATIENT.toString()))
            .andExpect(jsonPath("$.tarifConsult").value(sameInstant(DEFAULT_TARIF_CONSULT)))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabConsultation() throws Exception {
        // Get the tabConsultation
        restTabConsultationMockMvc.perform(get("/api/tab-consultations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabConsultation() throws Exception {
        // Initialize the database
        tabConsultationRepository.saveAndFlush(tabConsultation);

        int databaseSizeBeforeUpdate = tabConsultationRepository.findAll().size();

        // Update the tabConsultation
        TabConsultation updatedTabConsultation = tabConsultationRepository.findById(tabConsultation.getId()).get();
        // Disconnect from session so that the updates on updatedTabConsultation are not directly saved in db
        em.detach(updatedTabConsultation);
        updatedTabConsultation
            .dateConsulte(UPDATED_DATE_CONSULTE)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .motif(UPDATED_MOTIF)
            .affectActuel(UPDATED_AFFECT_ACTUEL)
            .antecedent(UPDATED_ANTECEDENT)
            .traiteHabituel(UPDATED_TRAITE_HABITUEL)
            .allergie(UPDATED_ALLERGIE)
            .tabac(UPDATED_TABAC)
            .alcool(UPDATED_ALCOOL)
            .facteurRisque(UPDATED_FACTEUR_RISQUE)
            .hypotheseDiag(UPDATED_HYPOTHESE_DIAG)
            .avisMedical(UPDATED_AVIS_MEDICAL)
            .ordreMedical(UPDATED_ORDRE_MEDICAL)
            .traitePropose(UPDATED_TRAITE_PROPOSE)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .tarifConsult(UPDATED_TARIF_CONSULT)
            .rapport(UPDATED_RAPPORT);

        restTabConsultationMockMvc.perform(put("/api/tab-consultations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabConsultation)))
            .andExpect(status().isOk());

        // Validate the TabConsultation in the database
        List<TabConsultation> tabConsultationList = tabConsultationRepository.findAll();
        assertThat(tabConsultationList).hasSize(databaseSizeBeforeUpdate);
        TabConsultation testTabConsultation = tabConsultationList.get(tabConsultationList.size() - 1);
        assertThat(testTabConsultation.getDateConsulte()).isEqualTo(UPDATED_DATE_CONSULTE);
        assertThat(testTabConsultation.getProvenancePatient()).isEqualTo(UPDATED_PROVENANCE_PATIENT);
        assertThat(testTabConsultation.getMotif()).isEqualTo(UPDATED_MOTIF);
        assertThat(testTabConsultation.getAffectActuel()).isEqualTo(UPDATED_AFFECT_ACTUEL);
        assertThat(testTabConsultation.getAntecedent()).isEqualTo(UPDATED_ANTECEDENT);
        assertThat(testTabConsultation.getTraiteHabituel()).isEqualTo(UPDATED_TRAITE_HABITUEL);
        assertThat(testTabConsultation.getAllergie()).isEqualTo(UPDATED_ALLERGIE);
        assertThat(testTabConsultation.isTabac()).isEqualTo(UPDATED_TABAC);
        assertThat(testTabConsultation.isAlcool()).isEqualTo(UPDATED_ALCOOL);
        assertThat(testTabConsultation.getFacteurRisque()).isEqualTo(UPDATED_FACTEUR_RISQUE);
        assertThat(testTabConsultation.getHypotheseDiag()).isEqualTo(UPDATED_HYPOTHESE_DIAG);
        assertThat(testTabConsultation.getAvisMedical()).isEqualTo(UPDATED_AVIS_MEDICAL);
        assertThat(testTabConsultation.getOrdreMedical()).isEqualTo(UPDATED_ORDRE_MEDICAL);
        assertThat(testTabConsultation.getTraitePropose()).isEqualTo(UPDATED_TRAITE_PROPOSE);
        assertThat(testTabConsultation.getLedevenirPatient()).isEqualTo(UPDATED_LEDEVENIR_PATIENT);
        assertThat(testTabConsultation.getTarifConsult()).isEqualTo(UPDATED_TARIF_CONSULT);
        assertThat(testTabConsultation.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabConsultation() throws Exception {
        int databaseSizeBeforeUpdate = tabConsultationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabConsultationMockMvc.perform(put("/api/tab-consultations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsultation)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsultation in the database
        List<TabConsultation> tabConsultationList = tabConsultationRepository.findAll();
        assertThat(tabConsultationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabConsultation() throws Exception {
        // Initialize the database
        tabConsultationRepository.saveAndFlush(tabConsultation);

        int databaseSizeBeforeDelete = tabConsultationRepository.findAll().size();

        // Delete the tabConsultation
        restTabConsultationMockMvc.perform(delete("/api/tab-consultations/{id}", tabConsultation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabConsultation> tabConsultationList = tabConsultationRepository.findAll();
        assertThat(tabConsultationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
