package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabComptabilite;
import com.mycompany.myapp.repository.TabComptabiliteRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TabComptabiliteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabComptabiliteResourceIT {

    private static final String DEFAULT_NOM_APPAREIL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_APPAREIL = "BBBBBBBBBB";

    private static final Float DEFAULT_TARIF_APPAREIL = 1F;
    private static final Float UPDATED_TARIF_APPAREIL = 2F;

    private static final Float DEFAULT_TARIF_SPECIALITE = 1F;
    private static final Float UPDATED_TARIF_SPECIALITE = 2F;

    private static final Float DEFAULT_TARIF_CONSULTATION = 1F;
    private static final Float UPDATED_TARIF_CONSULTATION = 2F;

    private static final Float DEFAULT_TARIF_CHAMBRE = 1F;
    private static final Float UPDATED_TARIF_CHAMBRE = 2F;

    private static final Float DEFAULT_TARIF_SEJOUR = 1F;
    private static final Float UPDATED_TARIF_SEJOUR = 2F;

    private static final Float DEFAULT_FACTURE_PATIENT = 1F;
    private static final Float UPDATED_FACTURE_PATIENT = 2F;

    private static final Float DEFAULT_RECETTE = 1F;
    private static final Float UPDATED_RECETTE = 2F;

    @Autowired
    private TabComptabiliteRepository tabComptabiliteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabComptabiliteMockMvc;

    private TabComptabilite tabComptabilite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabComptabilite createEntity(EntityManager em) {
        TabComptabilite tabComptabilite = new TabComptabilite()
            .nomAppareil(DEFAULT_NOM_APPAREIL)
            .tarifAppareil(DEFAULT_TARIF_APPAREIL)
            .tarifSpecialite(DEFAULT_TARIF_SPECIALITE)
            .tarifConsultation(DEFAULT_TARIF_CONSULTATION)
            .tarifChambre(DEFAULT_TARIF_CHAMBRE)
            .tarifSejour(DEFAULT_TARIF_SEJOUR)
            .facturePatient(DEFAULT_FACTURE_PATIENT)
            .recette(DEFAULT_RECETTE);
        return tabComptabilite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabComptabilite createUpdatedEntity(EntityManager em) {
        TabComptabilite tabComptabilite = new TabComptabilite()
            .nomAppareil(UPDATED_NOM_APPAREIL)
            .tarifAppareil(UPDATED_TARIF_APPAREIL)
            .tarifSpecialite(UPDATED_TARIF_SPECIALITE)
            .tarifConsultation(UPDATED_TARIF_CONSULTATION)
            .tarifChambre(UPDATED_TARIF_CHAMBRE)
            .tarifSejour(UPDATED_TARIF_SEJOUR)
            .facturePatient(UPDATED_FACTURE_PATIENT)
            .recette(UPDATED_RECETTE);
        return tabComptabilite;
    }

    @BeforeEach
    public void initTest() {
        tabComptabilite = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabComptabilite() throws Exception {
        int databaseSizeBeforeCreate = tabComptabiliteRepository.findAll().size();
        // Create the TabComptabilite
        restTabComptabiliteMockMvc.perform(post("/api/tab-comptabilites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabComptabilite)))
            .andExpect(status().isCreated());

        // Validate the TabComptabilite in the database
        List<TabComptabilite> tabComptabiliteList = tabComptabiliteRepository.findAll();
        assertThat(tabComptabiliteList).hasSize(databaseSizeBeforeCreate + 1);
        TabComptabilite testTabComptabilite = tabComptabiliteList.get(tabComptabiliteList.size() - 1);
        assertThat(testTabComptabilite.getNomAppareil()).isEqualTo(DEFAULT_NOM_APPAREIL);
        assertThat(testTabComptabilite.getTarifAppareil()).isEqualTo(DEFAULT_TARIF_APPAREIL);
        assertThat(testTabComptabilite.getTarifSpecialite()).isEqualTo(DEFAULT_TARIF_SPECIALITE);
        assertThat(testTabComptabilite.getTarifConsultation()).isEqualTo(DEFAULT_TARIF_CONSULTATION);
        assertThat(testTabComptabilite.getTarifChambre()).isEqualTo(DEFAULT_TARIF_CHAMBRE);
        assertThat(testTabComptabilite.getTarifSejour()).isEqualTo(DEFAULT_TARIF_SEJOUR);
        assertThat(testTabComptabilite.getFacturePatient()).isEqualTo(DEFAULT_FACTURE_PATIENT);
        assertThat(testTabComptabilite.getRecette()).isEqualTo(DEFAULT_RECETTE);
    }

    @Test
    @Transactional
    public void createTabComptabiliteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabComptabiliteRepository.findAll().size();

        // Create the TabComptabilite with an existing ID
        tabComptabilite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabComptabiliteMockMvc.perform(post("/api/tab-comptabilites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabComptabilite)))
            .andExpect(status().isBadRequest());

        // Validate the TabComptabilite in the database
        List<TabComptabilite> tabComptabiliteList = tabComptabiliteRepository.findAll();
        assertThat(tabComptabiliteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabComptabilites() throws Exception {
        // Initialize the database
        tabComptabiliteRepository.saveAndFlush(tabComptabilite);

        // Get all the tabComptabiliteList
        restTabComptabiliteMockMvc.perform(get("/api/tab-comptabilites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabComptabilite.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomAppareil").value(hasItem(DEFAULT_NOM_APPAREIL)))
            .andExpect(jsonPath("$.[*].tarifAppareil").value(hasItem(DEFAULT_TARIF_APPAREIL.doubleValue())))
            .andExpect(jsonPath("$.[*].tarifSpecialite").value(hasItem(DEFAULT_TARIF_SPECIALITE.doubleValue())))
            .andExpect(jsonPath("$.[*].tarifConsultation").value(hasItem(DEFAULT_TARIF_CONSULTATION.doubleValue())))
            .andExpect(jsonPath("$.[*].tarifChambre").value(hasItem(DEFAULT_TARIF_CHAMBRE.doubleValue())))
            .andExpect(jsonPath("$.[*].tarifSejour").value(hasItem(DEFAULT_TARIF_SEJOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].facturePatient").value(hasItem(DEFAULT_FACTURE_PATIENT.doubleValue())))
            .andExpect(jsonPath("$.[*].recette").value(hasItem(DEFAULT_RECETTE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTabComptabilite() throws Exception {
        // Initialize the database
        tabComptabiliteRepository.saveAndFlush(tabComptabilite);

        // Get the tabComptabilite
        restTabComptabiliteMockMvc.perform(get("/api/tab-comptabilites/{id}", tabComptabilite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabComptabilite.getId().intValue()))
            .andExpect(jsonPath("$.nomAppareil").value(DEFAULT_NOM_APPAREIL))
            .andExpect(jsonPath("$.tarifAppareil").value(DEFAULT_TARIF_APPAREIL.doubleValue()))
            .andExpect(jsonPath("$.tarifSpecialite").value(DEFAULT_TARIF_SPECIALITE.doubleValue()))
            .andExpect(jsonPath("$.tarifConsultation").value(DEFAULT_TARIF_CONSULTATION.doubleValue()))
            .andExpect(jsonPath("$.tarifChambre").value(DEFAULT_TARIF_CHAMBRE.doubleValue()))
            .andExpect(jsonPath("$.tarifSejour").value(DEFAULT_TARIF_SEJOUR.doubleValue()))
            .andExpect(jsonPath("$.facturePatient").value(DEFAULT_FACTURE_PATIENT.doubleValue()))
            .andExpect(jsonPath("$.recette").value(DEFAULT_RECETTE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTabComptabilite() throws Exception {
        // Get the tabComptabilite
        restTabComptabiliteMockMvc.perform(get("/api/tab-comptabilites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabComptabilite() throws Exception {
        // Initialize the database
        tabComptabiliteRepository.saveAndFlush(tabComptabilite);

        int databaseSizeBeforeUpdate = tabComptabiliteRepository.findAll().size();

        // Update the tabComptabilite
        TabComptabilite updatedTabComptabilite = tabComptabiliteRepository.findById(tabComptabilite.getId()).get();
        // Disconnect from session so that the updates on updatedTabComptabilite are not directly saved in db
        em.detach(updatedTabComptabilite);
        updatedTabComptabilite
            .nomAppareil(UPDATED_NOM_APPAREIL)
            .tarifAppareil(UPDATED_TARIF_APPAREIL)
            .tarifSpecialite(UPDATED_TARIF_SPECIALITE)
            .tarifConsultation(UPDATED_TARIF_CONSULTATION)
            .tarifChambre(UPDATED_TARIF_CHAMBRE)
            .tarifSejour(UPDATED_TARIF_SEJOUR)
            .facturePatient(UPDATED_FACTURE_PATIENT)
            .recette(UPDATED_RECETTE);

        restTabComptabiliteMockMvc.perform(put("/api/tab-comptabilites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabComptabilite)))
            .andExpect(status().isOk());

        // Validate the TabComptabilite in the database
        List<TabComptabilite> tabComptabiliteList = tabComptabiliteRepository.findAll();
        assertThat(tabComptabiliteList).hasSize(databaseSizeBeforeUpdate);
        TabComptabilite testTabComptabilite = tabComptabiliteList.get(tabComptabiliteList.size() - 1);
        assertThat(testTabComptabilite.getNomAppareil()).isEqualTo(UPDATED_NOM_APPAREIL);
        assertThat(testTabComptabilite.getTarifAppareil()).isEqualTo(UPDATED_TARIF_APPAREIL);
        assertThat(testTabComptabilite.getTarifSpecialite()).isEqualTo(UPDATED_TARIF_SPECIALITE);
        assertThat(testTabComptabilite.getTarifConsultation()).isEqualTo(UPDATED_TARIF_CONSULTATION);
        assertThat(testTabComptabilite.getTarifChambre()).isEqualTo(UPDATED_TARIF_CHAMBRE);
        assertThat(testTabComptabilite.getTarifSejour()).isEqualTo(UPDATED_TARIF_SEJOUR);
        assertThat(testTabComptabilite.getFacturePatient()).isEqualTo(UPDATED_FACTURE_PATIENT);
        assertThat(testTabComptabilite.getRecette()).isEqualTo(UPDATED_RECETTE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabComptabilite() throws Exception {
        int databaseSizeBeforeUpdate = tabComptabiliteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabComptabiliteMockMvc.perform(put("/api/tab-comptabilites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabComptabilite)))
            .andExpect(status().isBadRequest());

        // Validate the TabComptabilite in the database
        List<TabComptabilite> tabComptabiliteList = tabComptabiliteRepository.findAll();
        assertThat(tabComptabiliteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabComptabilite() throws Exception {
        // Initialize the database
        tabComptabiliteRepository.saveAndFlush(tabComptabilite);

        int databaseSizeBeforeDelete = tabComptabiliteRepository.findAll().size();

        // Delete the tabComptabilite
        restTabComptabiliteMockMvc.perform(delete("/api/tab-comptabilites/{id}", tabComptabilite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabComptabilite> tabComptabiliteList = tabComptabiliteRepository.findAll();
        assertThat(tabComptabiliteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
