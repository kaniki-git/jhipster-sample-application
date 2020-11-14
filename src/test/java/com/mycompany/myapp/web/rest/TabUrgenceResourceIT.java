package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabUrgence;
import com.mycompany.myapp.repository.TabUrgenceRepository;

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

import com.mycompany.myapp.domain.enumeration.ProvenancePatient;
import com.mycompany.myapp.domain.enumeration.LedevenirPatient;
/**
 * Integration tests for the {@link TabUrgenceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabUrgenceResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_ARRIVEE_URGENCE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_ARRIVEE_URGENCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ProvenancePatient DEFAULT_PROVENANCE_PATIENT = ProvenancePatient.Domicile;
    private static final ProvenancePatient UPDATED_PROVENANCE_PATIENT = ProvenancePatient.Urgence;

    private static final LedevenirPatient DEFAULT_LEDEVENIR_PATIENT = LedevenirPatient.TransHopi;
    private static final LedevenirPatient UPDATED_LEDEVENIR_PATIENT = LedevenirPatient.TransAutreHopi;

    private static final String DEFAULT_RAPPORT_URGENCE = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT_URGENCE = "BBBBBBBBBB";

    @Autowired
    private TabUrgenceRepository tabUrgenceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabUrgenceMockMvc;

    private TabUrgence tabUrgence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUrgence createEntity(EntityManager em) {
        TabUrgence tabUrgence = new TabUrgence()
            .dateArriveeUrgence(DEFAULT_DATE_ARRIVEE_URGENCE)
            .provenancePatient(DEFAULT_PROVENANCE_PATIENT)
            .ledevenirPatient(DEFAULT_LEDEVENIR_PATIENT)
            .rapportUrgence(DEFAULT_RAPPORT_URGENCE);
        return tabUrgence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUrgence createUpdatedEntity(EntityManager em) {
        TabUrgence tabUrgence = new TabUrgence()
            .dateArriveeUrgence(UPDATED_DATE_ARRIVEE_URGENCE)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .rapportUrgence(UPDATED_RAPPORT_URGENCE);
        return tabUrgence;
    }

    @BeforeEach
    public void initTest() {
        tabUrgence = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabUrgence() throws Exception {
        int databaseSizeBeforeCreate = tabUrgenceRepository.findAll().size();
        // Create the TabUrgence
        restTabUrgenceMockMvc.perform(post("/api/tab-urgences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUrgence)))
            .andExpect(status().isCreated());

        // Validate the TabUrgence in the database
        List<TabUrgence> tabUrgenceList = tabUrgenceRepository.findAll();
        assertThat(tabUrgenceList).hasSize(databaseSizeBeforeCreate + 1);
        TabUrgence testTabUrgence = tabUrgenceList.get(tabUrgenceList.size() - 1);
        assertThat(testTabUrgence.getDateArriveeUrgence()).isEqualTo(DEFAULT_DATE_ARRIVEE_URGENCE);
        assertThat(testTabUrgence.getProvenancePatient()).isEqualTo(DEFAULT_PROVENANCE_PATIENT);
        assertThat(testTabUrgence.getLedevenirPatient()).isEqualTo(DEFAULT_LEDEVENIR_PATIENT);
        assertThat(testTabUrgence.getRapportUrgence()).isEqualTo(DEFAULT_RAPPORT_URGENCE);
    }

    @Test
    @Transactional
    public void createTabUrgenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabUrgenceRepository.findAll().size();

        // Create the TabUrgence with an existing ID
        tabUrgence.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabUrgenceMockMvc.perform(post("/api/tab-urgences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUrgence)))
            .andExpect(status().isBadRequest());

        // Validate the TabUrgence in the database
        List<TabUrgence> tabUrgenceList = tabUrgenceRepository.findAll();
        assertThat(tabUrgenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabUrgences() throws Exception {
        // Initialize the database
        tabUrgenceRepository.saveAndFlush(tabUrgence);

        // Get all the tabUrgenceList
        restTabUrgenceMockMvc.perform(get("/api/tab-urgences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabUrgence.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateArriveeUrgence").value(hasItem(sameInstant(DEFAULT_DATE_ARRIVEE_URGENCE))))
            .andExpect(jsonPath("$.[*].provenancePatient").value(hasItem(DEFAULT_PROVENANCE_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].ledevenirPatient").value(hasItem(DEFAULT_LEDEVENIR_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].rapportUrgence").value(hasItem(DEFAULT_RAPPORT_URGENCE)));
    }
    
    @Test
    @Transactional
    public void getTabUrgence() throws Exception {
        // Initialize the database
        tabUrgenceRepository.saveAndFlush(tabUrgence);

        // Get the tabUrgence
        restTabUrgenceMockMvc.perform(get("/api/tab-urgences/{id}", tabUrgence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabUrgence.getId().intValue()))
            .andExpect(jsonPath("$.dateArriveeUrgence").value(sameInstant(DEFAULT_DATE_ARRIVEE_URGENCE)))
            .andExpect(jsonPath("$.provenancePatient").value(DEFAULT_PROVENANCE_PATIENT.toString()))
            .andExpect(jsonPath("$.ledevenirPatient").value(DEFAULT_LEDEVENIR_PATIENT.toString()))
            .andExpect(jsonPath("$.rapportUrgence").value(DEFAULT_RAPPORT_URGENCE));
    }
    @Test
    @Transactional
    public void getNonExistingTabUrgence() throws Exception {
        // Get the tabUrgence
        restTabUrgenceMockMvc.perform(get("/api/tab-urgences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabUrgence() throws Exception {
        // Initialize the database
        tabUrgenceRepository.saveAndFlush(tabUrgence);

        int databaseSizeBeforeUpdate = tabUrgenceRepository.findAll().size();

        // Update the tabUrgence
        TabUrgence updatedTabUrgence = tabUrgenceRepository.findById(tabUrgence.getId()).get();
        // Disconnect from session so that the updates on updatedTabUrgence are not directly saved in db
        em.detach(updatedTabUrgence);
        updatedTabUrgence
            .dateArriveeUrgence(UPDATED_DATE_ARRIVEE_URGENCE)
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .ledevenirPatient(UPDATED_LEDEVENIR_PATIENT)
            .rapportUrgence(UPDATED_RAPPORT_URGENCE);

        restTabUrgenceMockMvc.perform(put("/api/tab-urgences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabUrgence)))
            .andExpect(status().isOk());

        // Validate the TabUrgence in the database
        List<TabUrgence> tabUrgenceList = tabUrgenceRepository.findAll();
        assertThat(tabUrgenceList).hasSize(databaseSizeBeforeUpdate);
        TabUrgence testTabUrgence = tabUrgenceList.get(tabUrgenceList.size() - 1);
        assertThat(testTabUrgence.getDateArriveeUrgence()).isEqualTo(UPDATED_DATE_ARRIVEE_URGENCE);
        assertThat(testTabUrgence.getProvenancePatient()).isEqualTo(UPDATED_PROVENANCE_PATIENT);
        assertThat(testTabUrgence.getLedevenirPatient()).isEqualTo(UPDATED_LEDEVENIR_PATIENT);
        assertThat(testTabUrgence.getRapportUrgence()).isEqualTo(UPDATED_RAPPORT_URGENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabUrgence() throws Exception {
        int databaseSizeBeforeUpdate = tabUrgenceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabUrgenceMockMvc.perform(put("/api/tab-urgences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUrgence)))
            .andExpect(status().isBadRequest());

        // Validate the TabUrgence in the database
        List<TabUrgence> tabUrgenceList = tabUrgenceRepository.findAll();
        assertThat(tabUrgenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabUrgence() throws Exception {
        // Initialize the database
        tabUrgenceRepository.saveAndFlush(tabUrgence);

        int databaseSizeBeforeDelete = tabUrgenceRepository.findAll().size();

        // Delete the tabUrgence
        restTabUrgenceMockMvc.perform(delete("/api/tab-urgences/{id}", tabUrgence.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabUrgence> tabUrgenceList = tabUrgenceRepository.findAll();
        assertThat(tabUrgenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
