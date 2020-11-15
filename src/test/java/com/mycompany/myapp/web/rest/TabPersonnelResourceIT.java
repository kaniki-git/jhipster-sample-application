package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabPersonnel;
import com.mycompany.myapp.repository.TabPersonnelRepository;

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
 * Integration tests for the {@link TabPersonnelResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabPersonnelResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_CIVIL = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_CIVIL = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_PERSONNEL = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_PERSONNEL = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEENTREESERVICE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEENTREESERVICE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NOMSTATUT = "AAAAAAAAAA";
    private static final String UPDATED_NOMSTATUT = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabPersonnelRepository tabPersonnelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabPersonnelMockMvc;

    private TabPersonnel tabPersonnel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabPersonnel createEntity(EntityManager em) {
        TabPersonnel tabPersonnel = new TabPersonnel()
            .matricule(DEFAULT_MATRICULE)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .typePersonnel(DEFAULT_TYPE_PERSONNEL)
            .activite(DEFAULT_ACTIVITE)
            .grade(DEFAULT_GRADE)
            .dateentreeservice(DEFAULT_DATEENTREESERVICE)
            .nomstatut(DEFAULT_NOMSTATUT)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabPersonnel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabPersonnel createUpdatedEntity(EntityManager em) {
        TabPersonnel tabPersonnel = new TabPersonnel()
            .matricule(UPDATED_MATRICULE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .typePersonnel(UPDATED_TYPE_PERSONNEL)
            .activite(UPDATED_ACTIVITE)
            .grade(UPDATED_GRADE)
            .dateentreeservice(UPDATED_DATEENTREESERVICE)
            .nomstatut(UPDATED_NOMSTATUT)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabPersonnel;
    }

    @BeforeEach
    public void initTest() {
        tabPersonnel = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabPersonnel() throws Exception {
        int databaseSizeBeforeCreate = tabPersonnelRepository.findAll().size();
        // Create the TabPersonnel
        restTabPersonnelMockMvc.perform(post("/api/tab-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPersonnel)))
            .andExpect(status().isCreated());

        // Validate the TabPersonnel in the database
        List<TabPersonnel> tabPersonnelList = tabPersonnelRepository.findAll();
        assertThat(tabPersonnelList).hasSize(databaseSizeBeforeCreate + 1);
        TabPersonnel testTabPersonnel = tabPersonnelList.get(tabPersonnelList.size() - 1);
        assertThat(testTabPersonnel.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testTabPersonnel.getEtatCivil()).isEqualTo(DEFAULT_ETAT_CIVIL);
        assertThat(testTabPersonnel.getTypePersonnel()).isEqualTo(DEFAULT_TYPE_PERSONNEL);
        assertThat(testTabPersonnel.getActivite()).isEqualTo(DEFAULT_ACTIVITE);
        assertThat(testTabPersonnel.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testTabPersonnel.getDateentreeservice()).isEqualTo(DEFAULT_DATEENTREESERVICE);
        assertThat(testTabPersonnel.getNomstatut()).isEqualTo(DEFAULT_NOMSTATUT);
        assertThat(testTabPersonnel.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabPersonnel.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabPersonnel.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabPersonnel.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabPersonnelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabPersonnelRepository.findAll().size();

        // Create the TabPersonnel with an existing ID
        tabPersonnel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabPersonnelMockMvc.perform(post("/api/tab-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the TabPersonnel in the database
        List<TabPersonnel> tabPersonnelList = tabPersonnelRepository.findAll();
        assertThat(tabPersonnelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabPersonnels() throws Exception {
        // Initialize the database
        tabPersonnelRepository.saveAndFlush(tabPersonnel);

        // Get all the tabPersonnelList
        restTabPersonnelMockMvc.perform(get("/api/tab-personnels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabPersonnel.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL)))
            .andExpect(jsonPath("$.[*].typePersonnel").value(hasItem(DEFAULT_TYPE_PERSONNEL)))
            .andExpect(jsonPath("$.[*].activite").value(hasItem(DEFAULT_ACTIVITE)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].dateentreeservice").value(hasItem(sameInstant(DEFAULT_DATEENTREESERVICE))))
            .andExpect(jsonPath("$.[*].nomstatut").value(hasItem(DEFAULT_NOMSTATUT)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabPersonnel() throws Exception {
        // Initialize the database
        tabPersonnelRepository.saveAndFlush(tabPersonnel);

        // Get the tabPersonnel
        restTabPersonnelMockMvc.perform(get("/api/tab-personnels/{id}", tabPersonnel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabPersonnel.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL))
            .andExpect(jsonPath("$.typePersonnel").value(DEFAULT_TYPE_PERSONNEL))
            .andExpect(jsonPath("$.activite").value(DEFAULT_ACTIVITE))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.dateentreeservice").value(sameInstant(DEFAULT_DATEENTREESERVICE)))
            .andExpect(jsonPath("$.nomstatut").value(DEFAULT_NOMSTATUT))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabPersonnel() throws Exception {
        // Get the tabPersonnel
        restTabPersonnelMockMvc.perform(get("/api/tab-personnels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabPersonnel() throws Exception {
        // Initialize the database
        tabPersonnelRepository.saveAndFlush(tabPersonnel);

        int databaseSizeBeforeUpdate = tabPersonnelRepository.findAll().size();

        // Update the tabPersonnel
        TabPersonnel updatedTabPersonnel = tabPersonnelRepository.findById(tabPersonnel.getId()).get();
        // Disconnect from session so that the updates on updatedTabPersonnel are not directly saved in db
        em.detach(updatedTabPersonnel);
        updatedTabPersonnel
            .matricule(UPDATED_MATRICULE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .typePersonnel(UPDATED_TYPE_PERSONNEL)
            .activite(UPDATED_ACTIVITE)
            .grade(UPDATED_GRADE)
            .dateentreeservice(UPDATED_DATEENTREESERVICE)
            .nomstatut(UPDATED_NOMSTATUT)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabPersonnelMockMvc.perform(put("/api/tab-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabPersonnel)))
            .andExpect(status().isOk());

        // Validate the TabPersonnel in the database
        List<TabPersonnel> tabPersonnelList = tabPersonnelRepository.findAll();
        assertThat(tabPersonnelList).hasSize(databaseSizeBeforeUpdate);
        TabPersonnel testTabPersonnel = tabPersonnelList.get(tabPersonnelList.size() - 1);
        assertThat(testTabPersonnel.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testTabPersonnel.getEtatCivil()).isEqualTo(UPDATED_ETAT_CIVIL);
        assertThat(testTabPersonnel.getTypePersonnel()).isEqualTo(UPDATED_TYPE_PERSONNEL);
        assertThat(testTabPersonnel.getActivite()).isEqualTo(UPDATED_ACTIVITE);
        assertThat(testTabPersonnel.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testTabPersonnel.getDateentreeservice()).isEqualTo(UPDATED_DATEENTREESERVICE);
        assertThat(testTabPersonnel.getNomstatut()).isEqualTo(UPDATED_NOMSTATUT);
        assertThat(testTabPersonnel.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabPersonnel.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabPersonnel.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabPersonnel.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabPersonnel() throws Exception {
        int databaseSizeBeforeUpdate = tabPersonnelRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabPersonnelMockMvc.perform(put("/api/tab-personnels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabPersonnel)))
            .andExpect(status().isBadRequest());

        // Validate the TabPersonnel in the database
        List<TabPersonnel> tabPersonnelList = tabPersonnelRepository.findAll();
        assertThat(tabPersonnelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabPersonnel() throws Exception {
        // Initialize the database
        tabPersonnelRepository.saveAndFlush(tabPersonnel);

        int databaseSizeBeforeDelete = tabPersonnelRepository.findAll().size();

        // Delete the tabPersonnel
        restTabPersonnelMockMvc.perform(delete("/api/tab-personnels/{id}", tabPersonnel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabPersonnel> tabPersonnelList = tabPersonnelRepository.findAll();
        assertThat(tabPersonnelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
