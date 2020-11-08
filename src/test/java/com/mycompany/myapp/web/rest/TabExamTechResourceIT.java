package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabExamTech;
import com.mycompany.myapp.repository.TabExamTechRepository;

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
 * Integration tests for the {@link TabExamTechResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabExamTechResourceIT {

    private static final String DEFAULT_TYPE_EXAM_TECH = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_EXAM_TECH = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_EXAM_TECH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_EXAM_TECH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TARIF_EXAM_TECH = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TARIF_EXAM_TECH = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CONCLUSION_EXAM_TECH = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION_EXAM_TECH = "BBBBBBBBBB";

    @Autowired
    private TabExamTechRepository tabExamTechRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabExamTechMockMvc;

    private TabExamTech tabExamTech;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamTech createEntity(EntityManager em) {
        TabExamTech tabExamTech = new TabExamTech()
            .typeExamTech(DEFAULT_TYPE_EXAM_TECH)
            .dateExamTech(DEFAULT_DATE_EXAM_TECH)
            .tarifExamTech(DEFAULT_TARIF_EXAM_TECH)
            .conclusionExamTech(DEFAULT_CONCLUSION_EXAM_TECH);
        return tabExamTech;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamTech createUpdatedEntity(EntityManager em) {
        TabExamTech tabExamTech = new TabExamTech()
            .typeExamTech(UPDATED_TYPE_EXAM_TECH)
            .dateExamTech(UPDATED_DATE_EXAM_TECH)
            .tarifExamTech(UPDATED_TARIF_EXAM_TECH)
            .conclusionExamTech(UPDATED_CONCLUSION_EXAM_TECH);
        return tabExamTech;
    }

    @BeforeEach
    public void initTest() {
        tabExamTech = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabExamTech() throws Exception {
        int databaseSizeBeforeCreate = tabExamTechRepository.findAll().size();
        // Create the TabExamTech
        restTabExamTechMockMvc.perform(post("/api/tab-exam-teches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamTech)))
            .andExpect(status().isCreated());

        // Validate the TabExamTech in the database
        List<TabExamTech> tabExamTechList = tabExamTechRepository.findAll();
        assertThat(tabExamTechList).hasSize(databaseSizeBeforeCreate + 1);
        TabExamTech testTabExamTech = tabExamTechList.get(tabExamTechList.size() - 1);
        assertThat(testTabExamTech.getTypeExamTech()).isEqualTo(DEFAULT_TYPE_EXAM_TECH);
        assertThat(testTabExamTech.getDateExamTech()).isEqualTo(DEFAULT_DATE_EXAM_TECH);
        assertThat(testTabExamTech.getTarifExamTech()).isEqualTo(DEFAULT_TARIF_EXAM_TECH);
        assertThat(testTabExamTech.getConclusionExamTech()).isEqualTo(DEFAULT_CONCLUSION_EXAM_TECH);
    }

    @Test
    @Transactional
    public void createTabExamTechWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabExamTechRepository.findAll().size();

        // Create the TabExamTech with an existing ID
        tabExamTech.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabExamTechMockMvc.perform(post("/api/tab-exam-teches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamTech)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamTech in the database
        List<TabExamTech> tabExamTechList = tabExamTechRepository.findAll();
        assertThat(tabExamTechList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabExamTeches() throws Exception {
        // Initialize the database
        tabExamTechRepository.saveAndFlush(tabExamTech);

        // Get all the tabExamTechList
        restTabExamTechMockMvc.perform(get("/api/tab-exam-teches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabExamTech.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeExamTech").value(hasItem(DEFAULT_TYPE_EXAM_TECH)))
            .andExpect(jsonPath("$.[*].dateExamTech").value(hasItem(sameInstant(DEFAULT_DATE_EXAM_TECH))))
            .andExpect(jsonPath("$.[*].tarifExamTech").value(hasItem(sameInstant(DEFAULT_TARIF_EXAM_TECH))))
            .andExpect(jsonPath("$.[*].conclusionExamTech").value(hasItem(DEFAULT_CONCLUSION_EXAM_TECH)));
    }
    
    @Test
    @Transactional
    public void getTabExamTech() throws Exception {
        // Initialize the database
        tabExamTechRepository.saveAndFlush(tabExamTech);

        // Get the tabExamTech
        restTabExamTechMockMvc.perform(get("/api/tab-exam-teches/{id}", tabExamTech.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabExamTech.getId().intValue()))
            .andExpect(jsonPath("$.typeExamTech").value(DEFAULT_TYPE_EXAM_TECH))
            .andExpect(jsonPath("$.dateExamTech").value(sameInstant(DEFAULT_DATE_EXAM_TECH)))
            .andExpect(jsonPath("$.tarifExamTech").value(sameInstant(DEFAULT_TARIF_EXAM_TECH)))
            .andExpect(jsonPath("$.conclusionExamTech").value(DEFAULT_CONCLUSION_EXAM_TECH));
    }
    @Test
    @Transactional
    public void getNonExistingTabExamTech() throws Exception {
        // Get the tabExamTech
        restTabExamTechMockMvc.perform(get("/api/tab-exam-teches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabExamTech() throws Exception {
        // Initialize the database
        tabExamTechRepository.saveAndFlush(tabExamTech);

        int databaseSizeBeforeUpdate = tabExamTechRepository.findAll().size();

        // Update the tabExamTech
        TabExamTech updatedTabExamTech = tabExamTechRepository.findById(tabExamTech.getId()).get();
        // Disconnect from session so that the updates on updatedTabExamTech are not directly saved in db
        em.detach(updatedTabExamTech);
        updatedTabExamTech
            .typeExamTech(UPDATED_TYPE_EXAM_TECH)
            .dateExamTech(UPDATED_DATE_EXAM_TECH)
            .tarifExamTech(UPDATED_TARIF_EXAM_TECH)
            .conclusionExamTech(UPDATED_CONCLUSION_EXAM_TECH);

        restTabExamTechMockMvc.perform(put("/api/tab-exam-teches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabExamTech)))
            .andExpect(status().isOk());

        // Validate the TabExamTech in the database
        List<TabExamTech> tabExamTechList = tabExamTechRepository.findAll();
        assertThat(tabExamTechList).hasSize(databaseSizeBeforeUpdate);
        TabExamTech testTabExamTech = tabExamTechList.get(tabExamTechList.size() - 1);
        assertThat(testTabExamTech.getTypeExamTech()).isEqualTo(UPDATED_TYPE_EXAM_TECH);
        assertThat(testTabExamTech.getDateExamTech()).isEqualTo(UPDATED_DATE_EXAM_TECH);
        assertThat(testTabExamTech.getTarifExamTech()).isEqualTo(UPDATED_TARIF_EXAM_TECH);
        assertThat(testTabExamTech.getConclusionExamTech()).isEqualTo(UPDATED_CONCLUSION_EXAM_TECH);
    }

    @Test
    @Transactional
    public void updateNonExistingTabExamTech() throws Exception {
        int databaseSizeBeforeUpdate = tabExamTechRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabExamTechMockMvc.perform(put("/api/tab-exam-teches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamTech)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamTech in the database
        List<TabExamTech> tabExamTechList = tabExamTechRepository.findAll();
        assertThat(tabExamTechList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabExamTech() throws Exception {
        // Initialize the database
        tabExamTechRepository.saveAndFlush(tabExamTech);

        int databaseSizeBeforeDelete = tabExamTechRepository.findAll().size();

        // Delete the tabExamTech
        restTabExamTechMockMvc.perform(delete("/api/tab-exam-teches/{id}", tabExamTech.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabExamTech> tabExamTechList = tabExamTechRepository.findAll();
        assertThat(tabExamTechList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
