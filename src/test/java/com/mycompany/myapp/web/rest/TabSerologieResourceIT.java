package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabSerologie;
import com.mycompany.myapp.repository.TabSerologieRepository;

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
 * Integration tests for the {@link TabSerologieResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabSerologieResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_SEROLOGIE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_SEROLOGIE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_GR_SANG = "AAAAAAAAAA";
    private static final String UPDATED_GR_SANG = "BBBBBBBBBB";

    private static final String DEFAULT_GR_SANG_GENI = "AAAAAAAAAA";
    private static final String UPDATED_GR_SANG_GENI = "BBBBBBBBBB";

    private static final String DEFAULT_CARYOTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CARYOTYPE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TARIF_SEROLOGIE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TARIF_SEROLOGIE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_AUTRES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabSerologieRepository tabSerologieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabSerologieMockMvc;

    private TabSerologie tabSerologie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabSerologie createEntity(EntityManager em) {
        TabSerologie tabSerologie = new TabSerologie()
            .dateSerologie(DEFAULT_DATE_SEROLOGIE)
            .grSang(DEFAULT_GR_SANG)
            .grSangGeni(DEFAULT_GR_SANG_GENI)
            .caryotype(DEFAULT_CARYOTYPE)
            .tarifSerologie(DEFAULT_TARIF_SEROLOGIE)
            .autres(DEFAULT_AUTRES)
            .rapport(DEFAULT_RAPPORT);
        return tabSerologie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabSerologie createUpdatedEntity(EntityManager em) {
        TabSerologie tabSerologie = new TabSerologie()
            .dateSerologie(UPDATED_DATE_SEROLOGIE)
            .grSang(UPDATED_GR_SANG)
            .grSangGeni(UPDATED_GR_SANG_GENI)
            .caryotype(UPDATED_CARYOTYPE)
            .tarifSerologie(UPDATED_TARIF_SEROLOGIE)
            .autres(UPDATED_AUTRES)
            .rapport(UPDATED_RAPPORT);
        return tabSerologie;
    }

    @BeforeEach
    public void initTest() {
        tabSerologie = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabSerologie() throws Exception {
        int databaseSizeBeforeCreate = tabSerologieRepository.findAll().size();
        // Create the TabSerologie
        restTabSerologieMockMvc.perform(post("/api/tab-serologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSerologie)))
            .andExpect(status().isCreated());

        // Validate the TabSerologie in the database
        List<TabSerologie> tabSerologieList = tabSerologieRepository.findAll();
        assertThat(tabSerologieList).hasSize(databaseSizeBeforeCreate + 1);
        TabSerologie testTabSerologie = tabSerologieList.get(tabSerologieList.size() - 1);
        assertThat(testTabSerologie.getDateSerologie()).isEqualTo(DEFAULT_DATE_SEROLOGIE);
        assertThat(testTabSerologie.getGrSang()).isEqualTo(DEFAULT_GR_SANG);
        assertThat(testTabSerologie.getGrSangGeni()).isEqualTo(DEFAULT_GR_SANG_GENI);
        assertThat(testTabSerologie.getCaryotype()).isEqualTo(DEFAULT_CARYOTYPE);
        assertThat(testTabSerologie.getTarifSerologie()).isEqualTo(DEFAULT_TARIF_SEROLOGIE);
        assertThat(testTabSerologie.getAutres()).isEqualTo(DEFAULT_AUTRES);
        assertThat(testTabSerologie.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabSerologieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabSerologieRepository.findAll().size();

        // Create the TabSerologie with an existing ID
        tabSerologie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabSerologieMockMvc.perform(post("/api/tab-serologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSerologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabSerologie in the database
        List<TabSerologie> tabSerologieList = tabSerologieRepository.findAll();
        assertThat(tabSerologieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabSerologies() throws Exception {
        // Initialize the database
        tabSerologieRepository.saveAndFlush(tabSerologie);

        // Get all the tabSerologieList
        restTabSerologieMockMvc.perform(get("/api/tab-serologies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabSerologie.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateSerologie").value(hasItem(sameInstant(DEFAULT_DATE_SEROLOGIE))))
            .andExpect(jsonPath("$.[*].grSang").value(hasItem(DEFAULT_GR_SANG)))
            .andExpect(jsonPath("$.[*].grSangGeni").value(hasItem(DEFAULT_GR_SANG_GENI)))
            .andExpect(jsonPath("$.[*].caryotype").value(hasItem(DEFAULT_CARYOTYPE)))
            .andExpect(jsonPath("$.[*].tarifSerologie").value(hasItem(sameInstant(DEFAULT_TARIF_SEROLOGIE))))
            .andExpect(jsonPath("$.[*].autres").value(hasItem(DEFAULT_AUTRES)))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @Test
    @Transactional
    public void getTabSerologie() throws Exception {
        // Initialize the database
        tabSerologieRepository.saveAndFlush(tabSerologie);

        // Get the tabSerologie
        restTabSerologieMockMvc.perform(get("/api/tab-serologies/{id}", tabSerologie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabSerologie.getId().intValue()))
            .andExpect(jsonPath("$.dateSerologie").value(sameInstant(DEFAULT_DATE_SEROLOGIE)))
            .andExpect(jsonPath("$.grSang").value(DEFAULT_GR_SANG))
            .andExpect(jsonPath("$.grSangGeni").value(DEFAULT_GR_SANG_GENI))
            .andExpect(jsonPath("$.caryotype").value(DEFAULT_CARYOTYPE))
            .andExpect(jsonPath("$.tarifSerologie").value(sameInstant(DEFAULT_TARIF_SEROLOGIE)))
            .andExpect(jsonPath("$.autres").value(DEFAULT_AUTRES))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabSerologie() throws Exception {
        // Get the tabSerologie
        restTabSerologieMockMvc.perform(get("/api/tab-serologies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabSerologie() throws Exception {
        // Initialize the database
        tabSerologieRepository.saveAndFlush(tabSerologie);

        int databaseSizeBeforeUpdate = tabSerologieRepository.findAll().size();

        // Update the tabSerologie
        TabSerologie updatedTabSerologie = tabSerologieRepository.findById(tabSerologie.getId()).get();
        // Disconnect from session so that the updates on updatedTabSerologie are not directly saved in db
        em.detach(updatedTabSerologie);
        updatedTabSerologie
            .dateSerologie(UPDATED_DATE_SEROLOGIE)
            .grSang(UPDATED_GR_SANG)
            .grSangGeni(UPDATED_GR_SANG_GENI)
            .caryotype(UPDATED_CARYOTYPE)
            .tarifSerologie(UPDATED_TARIF_SEROLOGIE)
            .autres(UPDATED_AUTRES)
            .rapport(UPDATED_RAPPORT);

        restTabSerologieMockMvc.perform(put("/api/tab-serologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabSerologie)))
            .andExpect(status().isOk());

        // Validate the TabSerologie in the database
        List<TabSerologie> tabSerologieList = tabSerologieRepository.findAll();
        assertThat(tabSerologieList).hasSize(databaseSizeBeforeUpdate);
        TabSerologie testTabSerologie = tabSerologieList.get(tabSerologieList.size() - 1);
        assertThat(testTabSerologie.getDateSerologie()).isEqualTo(UPDATED_DATE_SEROLOGIE);
        assertThat(testTabSerologie.getGrSang()).isEqualTo(UPDATED_GR_SANG);
        assertThat(testTabSerologie.getGrSangGeni()).isEqualTo(UPDATED_GR_SANG_GENI);
        assertThat(testTabSerologie.getCaryotype()).isEqualTo(UPDATED_CARYOTYPE);
        assertThat(testTabSerologie.getTarifSerologie()).isEqualTo(UPDATED_TARIF_SEROLOGIE);
        assertThat(testTabSerologie.getAutres()).isEqualTo(UPDATED_AUTRES);
        assertThat(testTabSerologie.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabSerologie() throws Exception {
        int databaseSizeBeforeUpdate = tabSerologieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabSerologieMockMvc.perform(put("/api/tab-serologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabSerologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabSerologie in the database
        List<TabSerologie> tabSerologieList = tabSerologieRepository.findAll();
        assertThat(tabSerologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabSerologie() throws Exception {
        // Initialize the database
        tabSerologieRepository.saveAndFlush(tabSerologie);

        int databaseSizeBeforeDelete = tabSerologieRepository.findAll().size();

        // Delete the tabSerologie
        restTabSerologieMockMvc.perform(delete("/api/tab-serologies/{id}", tabSerologie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabSerologie> tabSerologieList = tabSerologieRepository.findAll();
        assertThat(tabSerologieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
