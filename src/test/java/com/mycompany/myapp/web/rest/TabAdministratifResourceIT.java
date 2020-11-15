package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabAdministratif;
import com.mycompany.myapp.repository.TabAdministratifRepository;

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
/**
 * Integration tests for the {@link TabAdministratifResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabAdministratifResourceIT {

    private static final ProvenancePatient DEFAULT_PROVENANCE_PATIENT = ProvenancePatient.Domicile;
    private static final ProvenancePatient UPDATED_PROVENANCE_PATIENT = ProvenancePatient.Urgence;

    private static final String DEFAULT_NUMERO_CHAMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CHAMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_BATIMENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_ENTREE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_ENTREE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_SORTIE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_SORTIE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMEROSECU = "AAAAAAAAAA";
    private static final String UPDATED_NUMEROSECU = "BBBBBBBBBB";

    private static final String DEFAULT_NOMASSURRANCE = "AAAAAAAAAA";
    private static final String UPDATED_NOMASSURRANCE = "BBBBBBBBBB";

    private static final String DEFAULT_COORDONNEESECU = "AAAAAAAAAA";
    private static final String UPDATED_COORDONNEESECU = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_MEDECIN_PERSO = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MEDECIN_PERSO = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabAdministratifRepository tabAdministratifRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabAdministratifMockMvc;

    private TabAdministratif tabAdministratif;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabAdministratif createEntity(EntityManager em) {
        TabAdministratif tabAdministratif = new TabAdministratif()
            .provenancePatient(DEFAULT_PROVENANCE_PATIENT)
            .numeroChambre(DEFAULT_NUMERO_CHAMBRE)
            .batiment(DEFAULT_BATIMENT)
            .dateEntree(DEFAULT_DATE_ENTREE)
            .dateSortie(DEFAULT_DATE_SORTIE)
            .commentaire(DEFAULT_COMMENTAIRE)
            .couverture(DEFAULT_COUVERTURE)
            .numerosecu(DEFAULT_NUMEROSECU)
            .nomassurrance(DEFAULT_NOMASSURRANCE)
            .coordonneesecu(DEFAULT_COORDONNEESECU)
            .nomMedecinPerso(DEFAULT_NOM_MEDECIN_PERSO)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabAdministratif;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabAdministratif createUpdatedEntity(EntityManager em) {
        TabAdministratif tabAdministratif = new TabAdministratif()
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .numeroChambre(UPDATED_NUMERO_CHAMBRE)
            .batiment(UPDATED_BATIMENT)
            .dateEntree(UPDATED_DATE_ENTREE)
            .dateSortie(UPDATED_DATE_SORTIE)
            .commentaire(UPDATED_COMMENTAIRE)
            .couverture(UPDATED_COUVERTURE)
            .numerosecu(UPDATED_NUMEROSECU)
            .nomassurrance(UPDATED_NOMASSURRANCE)
            .coordonneesecu(UPDATED_COORDONNEESECU)
            .nomMedecinPerso(UPDATED_NOM_MEDECIN_PERSO)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabAdministratif;
    }

    @BeforeEach
    public void initTest() {
        tabAdministratif = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabAdministratif() throws Exception {
        int databaseSizeBeforeCreate = tabAdministratifRepository.findAll().size();
        // Create the TabAdministratif
        restTabAdministratifMockMvc.perform(post("/api/tab-administratifs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAdministratif)))
            .andExpect(status().isCreated());

        // Validate the TabAdministratif in the database
        List<TabAdministratif> tabAdministratifList = tabAdministratifRepository.findAll();
        assertThat(tabAdministratifList).hasSize(databaseSizeBeforeCreate + 1);
        TabAdministratif testTabAdministratif = tabAdministratifList.get(tabAdministratifList.size() - 1);
        assertThat(testTabAdministratif.getProvenancePatient()).isEqualTo(DEFAULT_PROVENANCE_PATIENT);
        assertThat(testTabAdministratif.getNumeroChambre()).isEqualTo(DEFAULT_NUMERO_CHAMBRE);
        assertThat(testTabAdministratif.getBatiment()).isEqualTo(DEFAULT_BATIMENT);
        assertThat(testTabAdministratif.getDateEntree()).isEqualTo(DEFAULT_DATE_ENTREE);
        assertThat(testTabAdministratif.getDateSortie()).isEqualTo(DEFAULT_DATE_SORTIE);
        assertThat(testTabAdministratif.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testTabAdministratif.getCouverture()).isEqualTo(DEFAULT_COUVERTURE);
        assertThat(testTabAdministratif.getNumerosecu()).isEqualTo(DEFAULT_NUMEROSECU);
        assertThat(testTabAdministratif.getNomassurrance()).isEqualTo(DEFAULT_NOMASSURRANCE);
        assertThat(testTabAdministratif.getCoordonneesecu()).isEqualTo(DEFAULT_COORDONNEESECU);
        assertThat(testTabAdministratif.getNomMedecinPerso()).isEqualTo(DEFAULT_NOM_MEDECIN_PERSO);
        assertThat(testTabAdministratif.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabAdministratif.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabAdministratif.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabAdministratif.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabAdministratifWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabAdministratifRepository.findAll().size();

        // Create the TabAdministratif with an existing ID
        tabAdministratif.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabAdministratifMockMvc.perform(post("/api/tab-administratifs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAdministratif)))
            .andExpect(status().isBadRequest());

        // Validate the TabAdministratif in the database
        List<TabAdministratif> tabAdministratifList = tabAdministratifRepository.findAll();
        assertThat(tabAdministratifList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabAdministratifs() throws Exception {
        // Initialize the database
        tabAdministratifRepository.saveAndFlush(tabAdministratif);

        // Get all the tabAdministratifList
        restTabAdministratifMockMvc.perform(get("/api/tab-administratifs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabAdministratif.getId().intValue())))
            .andExpect(jsonPath("$.[*].provenancePatient").value(hasItem(DEFAULT_PROVENANCE_PATIENT.toString())))
            .andExpect(jsonPath("$.[*].numeroChambre").value(hasItem(DEFAULT_NUMERO_CHAMBRE)))
            .andExpect(jsonPath("$.[*].batiment").value(hasItem(DEFAULT_BATIMENT)))
            .andExpect(jsonPath("$.[*].dateEntree").value(hasItem(sameInstant(DEFAULT_DATE_ENTREE))))
            .andExpect(jsonPath("$.[*].dateSortie").value(hasItem(sameInstant(DEFAULT_DATE_SORTIE))))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].couverture").value(hasItem(DEFAULT_COUVERTURE)))
            .andExpect(jsonPath("$.[*].numerosecu").value(hasItem(DEFAULT_NUMEROSECU)))
            .andExpect(jsonPath("$.[*].nomassurrance").value(hasItem(DEFAULT_NOMASSURRANCE)))
            .andExpect(jsonPath("$.[*].coordonneesecu").value(hasItem(DEFAULT_COORDONNEESECU)))
            .andExpect(jsonPath("$.[*].nomMedecinPerso").value(hasItem(DEFAULT_NOM_MEDECIN_PERSO)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabAdministratif() throws Exception {
        // Initialize the database
        tabAdministratifRepository.saveAndFlush(tabAdministratif);

        // Get the tabAdministratif
        restTabAdministratifMockMvc.perform(get("/api/tab-administratifs/{id}", tabAdministratif.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabAdministratif.getId().intValue()))
            .andExpect(jsonPath("$.provenancePatient").value(DEFAULT_PROVENANCE_PATIENT.toString()))
            .andExpect(jsonPath("$.numeroChambre").value(DEFAULT_NUMERO_CHAMBRE))
            .andExpect(jsonPath("$.batiment").value(DEFAULT_BATIMENT))
            .andExpect(jsonPath("$.dateEntree").value(sameInstant(DEFAULT_DATE_ENTREE)))
            .andExpect(jsonPath("$.dateSortie").value(sameInstant(DEFAULT_DATE_SORTIE)))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.couverture").value(DEFAULT_COUVERTURE))
            .andExpect(jsonPath("$.numerosecu").value(DEFAULT_NUMEROSECU))
            .andExpect(jsonPath("$.nomassurrance").value(DEFAULT_NOMASSURRANCE))
            .andExpect(jsonPath("$.coordonneesecu").value(DEFAULT_COORDONNEESECU))
            .andExpect(jsonPath("$.nomMedecinPerso").value(DEFAULT_NOM_MEDECIN_PERSO))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabAdministratif() throws Exception {
        // Get the tabAdministratif
        restTabAdministratifMockMvc.perform(get("/api/tab-administratifs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabAdministratif() throws Exception {
        // Initialize the database
        tabAdministratifRepository.saveAndFlush(tabAdministratif);

        int databaseSizeBeforeUpdate = tabAdministratifRepository.findAll().size();

        // Update the tabAdministratif
        TabAdministratif updatedTabAdministratif = tabAdministratifRepository.findById(tabAdministratif.getId()).get();
        // Disconnect from session so that the updates on updatedTabAdministratif are not directly saved in db
        em.detach(updatedTabAdministratif);
        updatedTabAdministratif
            .provenancePatient(UPDATED_PROVENANCE_PATIENT)
            .numeroChambre(UPDATED_NUMERO_CHAMBRE)
            .batiment(UPDATED_BATIMENT)
            .dateEntree(UPDATED_DATE_ENTREE)
            .dateSortie(UPDATED_DATE_SORTIE)
            .commentaire(UPDATED_COMMENTAIRE)
            .couverture(UPDATED_COUVERTURE)
            .numerosecu(UPDATED_NUMEROSECU)
            .nomassurrance(UPDATED_NOMASSURRANCE)
            .coordonneesecu(UPDATED_COORDONNEESECU)
            .nomMedecinPerso(UPDATED_NOM_MEDECIN_PERSO)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabAdministratifMockMvc.perform(put("/api/tab-administratifs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabAdministratif)))
            .andExpect(status().isOk());

        // Validate the TabAdministratif in the database
        List<TabAdministratif> tabAdministratifList = tabAdministratifRepository.findAll();
        assertThat(tabAdministratifList).hasSize(databaseSizeBeforeUpdate);
        TabAdministratif testTabAdministratif = tabAdministratifList.get(tabAdministratifList.size() - 1);
        assertThat(testTabAdministratif.getProvenancePatient()).isEqualTo(UPDATED_PROVENANCE_PATIENT);
        assertThat(testTabAdministratif.getNumeroChambre()).isEqualTo(UPDATED_NUMERO_CHAMBRE);
        assertThat(testTabAdministratif.getBatiment()).isEqualTo(UPDATED_BATIMENT);
        assertThat(testTabAdministratif.getDateEntree()).isEqualTo(UPDATED_DATE_ENTREE);
        assertThat(testTabAdministratif.getDateSortie()).isEqualTo(UPDATED_DATE_SORTIE);
        assertThat(testTabAdministratif.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testTabAdministratif.getCouverture()).isEqualTo(UPDATED_COUVERTURE);
        assertThat(testTabAdministratif.getNumerosecu()).isEqualTo(UPDATED_NUMEROSECU);
        assertThat(testTabAdministratif.getNomassurrance()).isEqualTo(UPDATED_NOMASSURRANCE);
        assertThat(testTabAdministratif.getCoordonneesecu()).isEqualTo(UPDATED_COORDONNEESECU);
        assertThat(testTabAdministratif.getNomMedecinPerso()).isEqualTo(UPDATED_NOM_MEDECIN_PERSO);
        assertThat(testTabAdministratif.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabAdministratif.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabAdministratif.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabAdministratif.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabAdministratif() throws Exception {
        int databaseSizeBeforeUpdate = tabAdministratifRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabAdministratifMockMvc.perform(put("/api/tab-administratifs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabAdministratif)))
            .andExpect(status().isBadRequest());

        // Validate the TabAdministratif in the database
        List<TabAdministratif> tabAdministratifList = tabAdministratifRepository.findAll();
        assertThat(tabAdministratifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabAdministratif() throws Exception {
        // Initialize the database
        tabAdministratifRepository.saveAndFlush(tabAdministratif);

        int databaseSizeBeforeDelete = tabAdministratifRepository.findAll().size();

        // Delete the tabAdministratif
        restTabAdministratifMockMvc.perform(delete("/api/tab-administratifs/{id}", tabAdministratif.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabAdministratif> tabAdministratifList = tabAdministratifRepository.findAll();
        assertThat(tabAdministratifList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
