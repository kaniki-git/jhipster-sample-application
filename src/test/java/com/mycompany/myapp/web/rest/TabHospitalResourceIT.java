package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabHospital;
import com.mycompany.myapp.repository.TabHospitalRepository;

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
 * Integration tests for the {@link TabHospitalResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabHospitalResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_ADMISSION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_ADMISSION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MOTIF_ADMISSION = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF_ADMISSION = "BBBBBBBBBB";

    private static final ProvenancePatient DEFAULT_PROVENANCE_PATIENT = ProvenancePatient.Domicile;
    private static final ProvenancePatient UPDATED_PROVENANCE_PATIENT = ProvenancePatient.Urgence;

    private static final String DEFAULT_EVOL_JOUR = "AAAAAAAAAA";
    private static final String UPDATED_EVOL_JOUR = "BBBBBBBBBB";

    private static final String DEFAULT_EVOL_SYNTHESE = "AAAAAAAAAA";
    private static final String UPDATED_EVOL_SYNTHESE = "BBBBBBBBBB";

    private static final String DEFAULT_PLANT_THERAPEUTE = "AAAAAAAAAA";
    private static final String UPDATED_PLANT_THERAPEUTE = "BBBBBBBBBB";

    private static final LedevenirPatient DEFAULT_LEDEVENIR_PATIENT = LedevenirPatient.TransHopi;
    private static final LedevenirPatient UPDATED_LEDEVENIR_PATIENT = LedevenirPatient.TransAutreHopi;

    private static final ZonedDateTime DEFAULT_PROCHAIN_RDV = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PROCHAIN_RDV = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIEU_RDV = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_RDV = "BBBBBBBBBB";

    private static final String DEFAULT_CONCLUSION_SEJOUR = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION_SEJOUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_CONSULTANT = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CONSULTANT = "BBBBBBBBBB";

    private static final String DEFAULT_BILAN_ADMISSION = "AAAAAAAAAA";
    private static final String UPDATED_BILAN_ADMISSION = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabHospitalRepository tabHospitalRepository;

    @Mock
    private TabHospitalRepository tabHospitalRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabHospitalMockMvc;

    private TabHospital tabHospital;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabHospital createEntity(EntityManager em) {
        TabHospital tabHospital = new TabHospital()
            .dateAdmission(DEFAULT_DATE_ADMISSION)
            .motifAdmission(DEFAULT_MOTIF_ADMISSION)
            .provenancePatient(DEFAULT_PROVENANCE_PATIENT)
            .evolJour(DEFAULT_EVOL_JOUR)
            .evolSynthese(DEFAULT_EVOL_SYNTHESE)
            .plantTherapeute(DEFAULT_PLANT_THERAPEUTE)
            .ledevenirPatient(DEFAULT_LEDEVENIR_PATIENT)
            .prochainRdv(DEFAULT_PROCHAIN_RDV)
            .lieuRdv(DEFAULT_LIEU_RDV)
            .conclusionSejour(DEFAULT_CONCLUSION_SEJOUR)
            .nomConsultant(DEFAULT_NOM_CONSULTANT)
            .bilanAdmission(DEFAULT_BILAN_ADMISSION)
            .rapport(DEFAULT_RAPPORT);
        return tabHospital;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabHospital createUpdatedEntity(EntityManager em) {
        TabHospital tabHospital = new TabHospital()
            .dateAdmission(UPDATED_DATE_ADMISSION)
            .motifAdmission(UPDATED_MOTIF_ADMISSION)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .evolJour(UPDATED_EVOL_JOUR)
            .evolSynthese(UPDATED_EVOL_SYNTHESE)
            .plantTherapeute(UPDATED_PLANT_THERAPEUTE)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .prochainRdv(UPDATED_PROCHAIN_RDV)
            .lieuRdv(UPDATED_LIEU_RDV)
            .conclusionSejour(UPDATED_CONCLUSION_SEJOUR)
            .nomConsultant(UPDATED_NOM_CONSULTANT)
            .bilanAdmission(UPDATED_BILAN_ADMISSION)
            .rapport(UPDATED_RAPPORT);
        return tabHospital;
    }

    @BeforeEach
    public void initTest() {
        tabHospital = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabHospital() throws Exception {
        int databaseSizeBeforeCreate = tabHospitalRepository.findAll().size();
        // Create the TabHospital
        restTabHospitalMockMvc.perform(post("/api/tab-hospitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHospital)))
            .andExpect(status().isCreated());

        // Validate the TabHospital in the database
        List<TabHospital> tabHospitalList = tabHospitalRepository.findAll();
        assertThat(tabHospitalList).hasSize(databaseSizeBeforeCreate + 1);
        TabHospital testTabHospital = tabHospitalList.get(tabHospitalList.size() - 1);
        assertThat(testTabHospital.getDateAdmission()).isEqualTo(DEFAULT_DATE_ADMISSION);
        assertThat(testTabHospital.getMotifAdmission()).isEqualTo(DEFAULT_MOTIF_ADMISSION);
        assertThat(testTabHospital.getProvenancePatient()).isEqualTo(DEFAULT_PROVENANCE_PATIENT);
        assertThat(testTabHospital.getEvolJour()).isEqualTo(DEFAULT_EVOL_JOUR);
        assertThat(testTabHospital.getEvolSynthese()).isEqualTo(DEFAULT_EVOL_SYNTHESE);
        assertThat(testTabHospital.getPlantTherapeute()).isEqualTo(DEFAULT_PLANT_THERAPEUTE);
        assertThat(testTabHospital.getLedevenirPatient()).isEqualTo(DEFAULT_LEDEVENIR_PATIENT);
        assertThat(testTabHospital.getProchainRdv()).isEqualTo(DEFAULT_PROCHAIN_RDV);
        assertThat(testTabHospital.getLieuRdv()).isEqualTo(DEFAULT_LIEU_RDV);
        assertThat(testTabHospital.getConclusionSejour()).isEqualTo(DEFAULT_CONCLUSION_SEJOUR);
        assertThat(testTabHospital.getNomConsultant()).isEqualTo(DEFAULT_NOM_CONSULTANT);
        assertThat(testTabHospital.getBilanAdmission()).isEqualTo(DEFAULT_BILAN_ADMISSION);
        assertThat(testTabHospital.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabHospitalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabHospitalRepository.findAll().size();

        // Create the TabHospital with an existing ID
        tabHospital.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabHospitalMockMvc.perform(post("/api/tab-hospitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHospital)))
            .andExpect(status().isBadRequest());

        // Validate the TabHospital in the database
        List<TabHospital> tabHospitalList = tabHospitalRepository.findAll();
        assertThat(tabHospitalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabHospitals() throws Exception {
        // Initialize the database
        tabHospitalRepository.saveAndFlush(tabHospital);

        // Get all the tabHospitalList
        restTabHospitalMockMvc.perform(get("/api/tab-hospitals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabHospital.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateAdmission").value(hasItem(sameInstant(DEFAULT_DATE_ADMISSION))))
            .andExpect(jsonPath("$.[*].motifAdmission").value(hasItem(DEFAULT_MOTIF_ADMISSION)))
            .andExpect(jsonPath("$.[*].provenancePatient").value(hasItem(DEFAULT_PROVENANCE_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].evolJour").value(hasItem(DEFAULT_EVOL_JOUR)))
            .andExpect(jsonPath("$.[*].evolSynthese").value(hasItem(DEFAULT_EVOL_SYNTHESE)))
            .andExpect(jsonPath("$.[*].plantTherapeute").value(hasItem(DEFAULT_PLANT_THERAPEUTE)))
            .andExpect(jsonPath("$.[*].ledevenirPatient").value(hasItem(DEFAULT_LEDEVENIR_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].prochainRdv").value(hasItem(sameInstant(DEFAULT_PROCHAIN_RDV))))
            .andExpect(jsonPath("$.[*].lieuRdv").value(hasItem(DEFAULT_LIEU_RDV)))
            .andExpect(jsonPath("$.[*].conclusionSejour").value(hasItem(DEFAULT_CONCLUSION_SEJOUR)))
            .andExpect(jsonPath("$.[*].nomConsultant").value(hasItem(DEFAULT_NOM_CONSULTANT)))
            .andExpect(jsonPath("$.[*].bilanAdmission").value(hasItem(DEFAULT_BILAN_ADMISSION)))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllTabHospitalsWithEagerRelationshipsIsEnabled() throws Exception {
        when(tabHospitalRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTabHospitalMockMvc.perform(get("/api/tab-hospitals?eagerload=true"))
            .andExpect(status().isOk());

        verify(tabHospitalRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllTabHospitalsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(tabHospitalRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTabHospitalMockMvc.perform(get("/api/tab-hospitals?eagerload=true"))
            .andExpect(status().isOk());

        verify(tabHospitalRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTabHospital() throws Exception {
        // Initialize the database
        tabHospitalRepository.saveAndFlush(tabHospital);

        // Get the tabHospital
        restTabHospitalMockMvc.perform(get("/api/tab-hospitals/{id}", tabHospital.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabHospital.getId().intValue()))
            .andExpect(jsonPath("$.dateAdmission").value(sameInstant(DEFAULT_DATE_ADMISSION)))
            .andExpect(jsonPath("$.motifAdmission").value(DEFAULT_MOTIF_ADMISSION))
            .andExpect(jsonPath("$.provenancePatient").value(DEFAULT_PROVENANCE_PATIENT.toString()))
            .andExpect(jsonPath("$.evolJour").value(DEFAULT_EVOL_JOUR))
            .andExpect(jsonPath("$.evolSynthese").value(DEFAULT_EVOL_SYNTHESE))
            .andExpect(jsonPath("$.plantTherapeute").value(DEFAULT_PLANT_THERAPEUTE))
            .andExpect(jsonPath("$.ledevenirPatient").value(DEFAULT_LEDEVENIR_PATIENT.toString()))
            .andExpect(jsonPath("$.prochainRdv").value(sameInstant(DEFAULT_PROCHAIN_RDV)))
            .andExpect(jsonPath("$.lieuRdv").value(DEFAULT_LIEU_RDV))
            .andExpect(jsonPath("$.conclusionSejour").value(DEFAULT_CONCLUSION_SEJOUR))
            .andExpect(jsonPath("$.nomConsultant").value(DEFAULT_NOM_CONSULTANT))
            .andExpect(jsonPath("$.bilanAdmission").value(DEFAULT_BILAN_ADMISSION))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabHospital() throws Exception {
        // Get the tabHospital
        restTabHospitalMockMvc.perform(get("/api/tab-hospitals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabHospital() throws Exception {
        // Initialize the database
        tabHospitalRepository.saveAndFlush(tabHospital);

        int databaseSizeBeforeUpdate = tabHospitalRepository.findAll().size();

        // Update the tabHospital
        TabHospital updatedTabHospital = tabHospitalRepository.findById(tabHospital.getId()).get();
        // Disconnect from session so that the updates on updatedTabHospital are not directly saved in db
        em.detach(updatedTabHospital);
        updatedTabHospital
            .dateAdmission(UPDATED_DATE_ADMISSION)
            .motifAdmission(UPDATED_MOTIF_ADMISSION)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .evolJour(UPDATED_EVOL_JOUR)
            .evolSynthese(UPDATED_EVOL_SYNTHESE)
            .plantTherapeute(UPDATED_PLANT_THERAPEUTE)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .prochainRdv(UPDATED_PROCHAIN_RDV)
            .lieuRdv(UPDATED_LIEU_RDV)
            .conclusionSejour(UPDATED_CONCLUSION_SEJOUR)
            .nomConsultant(UPDATED_NOM_CONSULTANT)
            .bilanAdmission(UPDATED_BILAN_ADMISSION)
            .rapport(UPDATED_RAPPORT);

        restTabHospitalMockMvc.perform(put("/api/tab-hospitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabHospital)))
            .andExpect(status().isOk());

        // Validate the TabHospital in the database
        List<TabHospital> tabHospitalList = tabHospitalRepository.findAll();
        assertThat(tabHospitalList).hasSize(databaseSizeBeforeUpdate);
        TabHospital testTabHospital = tabHospitalList.get(tabHospitalList.size() - 1);
        assertThat(testTabHospital.getDateAdmission()).isEqualTo(UPDATED_DATE_ADMISSION);
        assertThat(testTabHospital.getMotifAdmission()).isEqualTo(UPDATED_MOTIF_ADMISSION);
        assertThat(testTabHospital.getProvenancePatient()).isEqualTo(UPDATED_PROVENANCE_PATIENT);
        assertThat(testTabHospital.getEvolJour()).isEqualTo(UPDATED_EVOL_JOUR);
        assertThat(testTabHospital.getEvolSynthese()).isEqualTo(UPDATED_EVOL_SYNTHESE);
        assertThat(testTabHospital.getPlantTherapeute()).isEqualTo(UPDATED_PLANT_THERAPEUTE);
        assertThat(testTabHospital.getLedevenirPatient()).isEqualTo(UPDATED_LEDEVENIR_PATIENT);
        assertThat(testTabHospital.getProchainRdv()).isEqualTo(UPDATED_PROCHAIN_RDV);
        assertThat(testTabHospital.getLieuRdv()).isEqualTo(UPDATED_LIEU_RDV);
        assertThat(testTabHospital.getConclusionSejour()).isEqualTo(UPDATED_CONCLUSION_SEJOUR);
        assertThat(testTabHospital.getNomConsultant()).isEqualTo(UPDATED_NOM_CONSULTANT);
        assertThat(testTabHospital.getBilanAdmission()).isEqualTo(UPDATED_BILAN_ADMISSION);
        assertThat(testTabHospital.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabHospital() throws Exception {
        int databaseSizeBeforeUpdate = tabHospitalRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabHospitalMockMvc.perform(put("/api/tab-hospitals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabHospital)))
            .andExpect(status().isBadRequest());

        // Validate the TabHospital in the database
        List<TabHospital> tabHospitalList = tabHospitalRepository.findAll();
        assertThat(tabHospitalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabHospital() throws Exception {
        // Initialize the database
        tabHospitalRepository.saveAndFlush(tabHospital);

        int databaseSizeBeforeDelete = tabHospitalRepository.findAll().size();

        // Delete the tabHospital
        restTabHospitalMockMvc.perform(delete("/api/tab-hospitals/{id}", tabHospital.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabHospital> tabHospitalList = tabHospitalRepository.findAll();
        assertThat(tabHospitalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
