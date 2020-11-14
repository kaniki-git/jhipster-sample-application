package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabBiologie;
import com.mycompany.myapp.repository.TabBiologieRepository;

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
 * Integration tests for the {@link TabBiologieResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabBiologieResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_BIOLOGIE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_BIOLOGIE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NOM_BIOLOGIE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_BIOLOGIE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SEROLOGIE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SEROLOGIE = "BBBBBBBBBB";

    private static final String DEFAULT_G_SANGUIN = "AAAAAAAAAA";
    private static final String UPDATED_G_SANGUIN = "BBBBBBBBBB";

    private static final String DEFAULT_GR_SANG_GENI = "AAAAAAAAAA";
    private static final String UPDATED_GR_SANG_GENI = "BBBBBBBBBB";

    private static final String DEFAULT_CARYOTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CARYOTYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_TARIF_BIOLOGIE = 1F;
    private static final Float UPDATED_TARIF_BIOLOGIE = 2F;

    private static final String DEFAULT_AUTRES_BIOLOGIE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_BIOLOGIE = "BBBBBBBBBB";

    @Autowired
    private TabBiologieRepository tabBiologieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabBiologieMockMvc;

    private TabBiologie tabBiologie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabBiologie createEntity(EntityManager em) {
        TabBiologie tabBiologie = new TabBiologie()
            .dateBiologie(DEFAULT_DATE_BIOLOGIE)
            .nomBiologie(DEFAULT_NOM_BIOLOGIE)
            .nomSerologie(DEFAULT_NOM_SEROLOGIE)
            .gSanguin(DEFAULT_G_SANGUIN)
            .grSangGeni(DEFAULT_GR_SANG_GENI)
            .caryotype(DEFAULT_CARYOTYPE)
            .tarifBiologie(DEFAULT_TARIF_BIOLOGIE)
            .autresBiologie(DEFAULT_AUTRES_BIOLOGIE);
        return tabBiologie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabBiologie createUpdatedEntity(EntityManager em) {
        TabBiologie tabBiologie = new TabBiologie()
            .dateBiologie(UPDATED_DATE_BIOLOGIE)
            .nomBiologie(UPDATED_NOM_BIOLOGIE)
            .nomSerologie(UPDATED_NOM_SEROLOGIE)
            .gSanguin(UPDATED_G_SANGUIN)
            .grSangGeni(UPDATED_GR_SANG_GENI)
            .caryotype(UPDATED_CARYOTYPE)
            .tarifBiologie(UPDATED_TARIF_BIOLOGIE)
            .autresBiologie(UPDATED_AUTRES_BIOLOGIE);
        return tabBiologie;
    }

    @BeforeEach
    public void initTest() {
        tabBiologie = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabBiologie() throws Exception {
        int databaseSizeBeforeCreate = tabBiologieRepository.findAll().size();
        // Create the TabBiologie
        restTabBiologieMockMvc.perform(post("/api/tab-biologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabBiologie)))
            .andExpect(status().isCreated());

        // Validate the TabBiologie in the database
        List<TabBiologie> tabBiologieList = tabBiologieRepository.findAll();
        assertThat(tabBiologieList).hasSize(databaseSizeBeforeCreate + 1);
        TabBiologie testTabBiologie = tabBiologieList.get(tabBiologieList.size() - 1);
        assertThat(testTabBiologie.getDateBiologie()).isEqualTo(DEFAULT_DATE_BIOLOGIE);
        assertThat(testTabBiologie.getNomBiologie()).isEqualTo(DEFAULT_NOM_BIOLOGIE);
        assertThat(testTabBiologie.getNomSerologie()).isEqualTo(DEFAULT_NOM_SEROLOGIE);
        assertThat(testTabBiologie.getgSanguin()).isEqualTo(DEFAULT_G_SANGUIN);
        assertThat(testTabBiologie.getGrSangGeni()).isEqualTo(DEFAULT_GR_SANG_GENI);
        assertThat(testTabBiologie.getCaryotype()).isEqualTo(DEFAULT_CARYOTYPE);
        assertThat(testTabBiologie.getTarifBiologie()).isEqualTo(DEFAULT_TARIF_BIOLOGIE);
        assertThat(testTabBiologie.getAutresBiologie()).isEqualTo(DEFAULT_AUTRES_BIOLOGIE);
    }

    @Test
    @Transactional
    public void createTabBiologieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabBiologieRepository.findAll().size();

        // Create the TabBiologie with an existing ID
        tabBiologie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabBiologieMockMvc.perform(post("/api/tab-biologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabBiologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabBiologie in the database
        List<TabBiologie> tabBiologieList = tabBiologieRepository.findAll();
        assertThat(tabBiologieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabBiologies() throws Exception {
        // Initialize the database
        tabBiologieRepository.saveAndFlush(tabBiologie);

        // Get all the tabBiologieList
        restTabBiologieMockMvc.perform(get("/api/tab-biologies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabBiologie.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateBiologie").value(hasItem(sameInstant(DEFAULT_DATE_BIOLOGIE))))
            .andExpect(jsonPath("$.[*].nomBiologie").value(hasItem(DEFAULT_NOM_BIOLOGIE)))
            .andExpect(jsonPath("$.[*].nomSerologie").value(hasItem(DEFAULT_NOM_SEROLOGIE)))
            .andExpect(jsonPath("$.[*].gSanguin").value(hasItem(DEFAULT_G_SANGUIN)))
            .andExpect(jsonPath("$.[*].grSangGeni").value(hasItem(DEFAULT_GR_SANG_GENI)))
            .andExpect(jsonPath("$.[*].caryotype").value(hasItem(DEFAULT_CARYOTYPE)))
            .andExpect(jsonPath("$.[*].tarifBiologie").value(hasItem(DEFAULT_TARIF_BIOLOGIE.doubleValue())))
            .andExpect(jsonPath("$.[*].autresBiologie").value(hasItem(DEFAULT_AUTRES_BIOLOGIE)));
    }
    
    @Test
    @Transactional
    public void getTabBiologie() throws Exception {
        // Initialize the database
        tabBiologieRepository.saveAndFlush(tabBiologie);

        // Get the tabBiologie
        restTabBiologieMockMvc.perform(get("/api/tab-biologies/{id}", tabBiologie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabBiologie.getId().intValue()))
            .andExpect(jsonPath("$.dateBiologie").value(sameInstant(DEFAULT_DATE_BIOLOGIE)))
            .andExpect(jsonPath("$.nomBiologie").value(DEFAULT_NOM_BIOLOGIE))
            .andExpect(jsonPath("$.nomSerologie").value(DEFAULT_NOM_SEROLOGIE))
            .andExpect(jsonPath("$.gSanguin").value(DEFAULT_G_SANGUIN))
            .andExpect(jsonPath("$.grSangGeni").value(DEFAULT_GR_SANG_GENI))
            .andExpect(jsonPath("$.caryotype").value(DEFAULT_CARYOTYPE))
            .andExpect(jsonPath("$.tarifBiologie").value(DEFAULT_TARIF_BIOLOGIE.doubleValue()))
            .andExpect(jsonPath("$.autresBiologie").value(DEFAULT_AUTRES_BIOLOGIE));
    }
    @Test
    @Transactional
    public void getNonExistingTabBiologie() throws Exception {
        // Get the tabBiologie
        restTabBiologieMockMvc.perform(get("/api/tab-biologies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabBiologie() throws Exception {
        // Initialize the database
        tabBiologieRepository.saveAndFlush(tabBiologie);

        int databaseSizeBeforeUpdate = tabBiologieRepository.findAll().size();

        // Update the tabBiologie
        TabBiologie updatedTabBiologie = tabBiologieRepository.findById(tabBiologie.getId()).get();
        // Disconnect from session so that the updates on updatedTabBiologie are not directly saved in db
        em.detach(updatedTabBiologie);
        updatedTabBiologie
            .dateBiologie(UPDATED_DATE_BIOLOGIE)
            .nomBiologie(UPDATED_NOM_BIOLOGIE)
            .nomSerologie(UPDATED_NOM_SEROLOGIE)
            .gSanguin(UPDATED_G_SANGUIN)
            .grSangGeni(UPDATED_GR_SANG_GENI)
            .caryotype(UPDATED_CARYOTYPE)
            .tarifBiologie(UPDATED_TARIF_BIOLOGIE)
            .autresBiologie(UPDATED_AUTRES_BIOLOGIE);

        restTabBiologieMockMvc.perform(put("/api/tab-biologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabBiologie)))
            .andExpect(status().isOk());

        // Validate the TabBiologie in the database
        List<TabBiologie> tabBiologieList = tabBiologieRepository.findAll();
        assertThat(tabBiologieList).hasSize(databaseSizeBeforeUpdate);
        TabBiologie testTabBiologie = tabBiologieList.get(tabBiologieList.size() - 1);
        assertThat(testTabBiologie.getDateBiologie()).isEqualTo(UPDATED_DATE_BIOLOGIE);
        assertThat(testTabBiologie.getNomBiologie()).isEqualTo(UPDATED_NOM_BIOLOGIE);
        assertThat(testTabBiologie.getNomSerologie()).isEqualTo(UPDATED_NOM_SEROLOGIE);
        assertThat(testTabBiologie.getgSanguin()).isEqualTo(UPDATED_G_SANGUIN);
        assertThat(testTabBiologie.getGrSangGeni()).isEqualTo(UPDATED_GR_SANG_GENI);
        assertThat(testTabBiologie.getCaryotype()).isEqualTo(UPDATED_CARYOTYPE);
        assertThat(testTabBiologie.getTarifBiologie()).isEqualTo(UPDATED_TARIF_BIOLOGIE);
        assertThat(testTabBiologie.getAutresBiologie()).isEqualTo(UPDATED_AUTRES_BIOLOGIE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabBiologie() throws Exception {
        int databaseSizeBeforeUpdate = tabBiologieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabBiologieMockMvc.perform(put("/api/tab-biologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabBiologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabBiologie in the database
        List<TabBiologie> tabBiologieList = tabBiologieRepository.findAll();
        assertThat(tabBiologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabBiologie() throws Exception {
        // Initialize the database
        tabBiologieRepository.saveAndFlush(tabBiologie);

        int databaseSizeBeforeDelete = tabBiologieRepository.findAll().size();

        // Delete the tabBiologie
        restTabBiologieMockMvc.perform(delete("/api/tab-biologies/{id}", tabBiologie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabBiologie> tabBiologieList = tabBiologieRepository.findAll();
        assertThat(tabBiologieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
