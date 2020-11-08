package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabRendezvous;
import com.mycompany.myapp.repository.TabRendezvousRepository;

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
 * Integration tests for the {@link TabRendezvousResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabRendezvousResourceIT {

    private static final String DEFAULT_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_TITRE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_DEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_DEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_JOUR_ENTIER = false;
    private static final Boolean UPDATED_JOUR_ENTIER = true;

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIERE_PLAN_COULEUR = "AAAAAAAAAA";
    private static final String UPDATED_ARRIERE_PLAN_COULEUR = "BBBBBBBBBB";

    private static final String DEFAULT_TEXTE_COULEUR = "AAAAAAAAAA";
    private static final String UPDATED_TEXTE_COULEUR = "BBBBBBBBBB";

    private static final String DEFAULT_SALLE = "AAAAAAAAAA";
    private static final String UPDATED_SALLE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabRendezvousRepository tabRendezvousRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabRendezvousMockMvc;

    private TabRendezvous tabRendezvous;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRendezvous createEntity(EntityManager em) {
        TabRendezvous tabRendezvous = new TabRendezvous()
            .titre(DEFAULT_TITRE)
            .ville(DEFAULT_VILLE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .jourEntier(DEFAULT_JOUR_ENTIER)
            .groupId(DEFAULT_GROUP_ID)
            .arrierePlanCouleur(DEFAULT_ARRIERE_PLAN_COULEUR)
            .texteCouleur(DEFAULT_TEXTE_COULEUR)
            .salle(DEFAULT_SALLE)
            .commentaire(DEFAULT_COMMENTAIRE)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabRendezvous;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabRendezvous createUpdatedEntity(EntityManager em) {
        TabRendezvous tabRendezvous = new TabRendezvous()
            .titre(UPDATED_TITRE)
            .ville(UPDATED_VILLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .jourEntier(UPDATED_JOUR_ENTIER)
            .groupId(UPDATED_GROUP_ID)
            .arrierePlanCouleur(UPDATED_ARRIERE_PLAN_COULEUR)
            .texteCouleur(UPDATED_TEXTE_COULEUR)
            .salle(UPDATED_SALLE)
            .commentaire(UPDATED_COMMENTAIRE)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabRendezvous;
    }

    @BeforeEach
    public void initTest() {
        tabRendezvous = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabRendezvous() throws Exception {
        int databaseSizeBeforeCreate = tabRendezvousRepository.findAll().size();
        // Create the TabRendezvous
        restTabRendezvousMockMvc.perform(post("/api/tab-rendezvous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRendezvous)))
            .andExpect(status().isCreated());

        // Validate the TabRendezvous in the database
        List<TabRendezvous> tabRendezvousList = tabRendezvousRepository.findAll();
        assertThat(tabRendezvousList).hasSize(databaseSizeBeforeCreate + 1);
        TabRendezvous testTabRendezvous = tabRendezvousList.get(tabRendezvousList.size() - 1);
        assertThat(testTabRendezvous.getTitre()).isEqualTo(DEFAULT_TITRE);
        assertThat(testTabRendezvous.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testTabRendezvous.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testTabRendezvous.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testTabRendezvous.isJourEntier()).isEqualTo(DEFAULT_JOUR_ENTIER);
        assertThat(testTabRendezvous.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testTabRendezvous.getArrierePlanCouleur()).isEqualTo(DEFAULT_ARRIERE_PLAN_COULEUR);
        assertThat(testTabRendezvous.getTexteCouleur()).isEqualTo(DEFAULT_TEXTE_COULEUR);
        assertThat(testTabRendezvous.getSalle()).isEqualTo(DEFAULT_SALLE);
        assertThat(testTabRendezvous.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testTabRendezvous.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabRendezvous.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabRendezvous.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabRendezvous.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabRendezvousWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabRendezvousRepository.findAll().size();

        // Create the TabRendezvous with an existing ID
        tabRendezvous.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabRendezvousMockMvc.perform(post("/api/tab-rendezvous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRendezvous)))
            .andExpect(status().isBadRequest());

        // Validate the TabRendezvous in the database
        List<TabRendezvous> tabRendezvousList = tabRendezvousRepository.findAll();
        assertThat(tabRendezvousList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabRendezvous() throws Exception {
        // Initialize the database
        tabRendezvousRepository.saveAndFlush(tabRendezvous);

        // Get all the tabRendezvousList
        restTabRendezvousMockMvc.perform(get("/api/tab-rendezvous?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabRendezvous.getId().intValue())))
            .andExpect(jsonPath("$.[*].titre").value(hasItem(DEFAULT_TITRE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(sameInstant(DEFAULT_DATE_DEBUT))))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))))
            .andExpect(jsonPath("$.[*].jourEntier").value(hasItem(DEFAULT_JOUR_ENTIER.booleanValue())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].arrierePlanCouleur").value(hasItem(DEFAULT_ARRIERE_PLAN_COULEUR)))
            .andExpect(jsonPath("$.[*].texteCouleur").value(hasItem(DEFAULT_TEXTE_COULEUR)))
            .andExpect(jsonPath("$.[*].salle").value(hasItem(DEFAULT_SALLE)))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabRendezvous() throws Exception {
        // Initialize the database
        tabRendezvousRepository.saveAndFlush(tabRendezvous);

        // Get the tabRendezvous
        restTabRendezvousMockMvc.perform(get("/api/tab-rendezvous/{id}", tabRendezvous.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabRendezvous.getId().intValue()))
            .andExpect(jsonPath("$.titre").value(DEFAULT_TITRE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.dateDebut").value(sameInstant(DEFAULT_DATE_DEBUT)))
            .andExpect(jsonPath("$.dateFin").value(sameInstant(DEFAULT_DATE_FIN)))
            .andExpect(jsonPath("$.jourEntier").value(DEFAULT_JOUR_ENTIER.booleanValue()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.arrierePlanCouleur").value(DEFAULT_ARRIERE_PLAN_COULEUR))
            .andExpect(jsonPath("$.texteCouleur").value(DEFAULT_TEXTE_COULEUR))
            .andExpect(jsonPath("$.salle").value(DEFAULT_SALLE))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabRendezvous() throws Exception {
        // Get the tabRendezvous
        restTabRendezvousMockMvc.perform(get("/api/tab-rendezvous/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabRendezvous() throws Exception {
        // Initialize the database
        tabRendezvousRepository.saveAndFlush(tabRendezvous);

        int databaseSizeBeforeUpdate = tabRendezvousRepository.findAll().size();

        // Update the tabRendezvous
        TabRendezvous updatedTabRendezvous = tabRendezvousRepository.findById(tabRendezvous.getId()).get();
        // Disconnect from session so that the updates on updatedTabRendezvous are not directly saved in db
        em.detach(updatedTabRendezvous);
        updatedTabRendezvous
            .titre(UPDATED_TITRE)
            .ville(UPDATED_VILLE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .jourEntier(UPDATED_JOUR_ENTIER)
            .groupId(UPDATED_GROUP_ID)
            .arrierePlanCouleur(UPDATED_ARRIERE_PLAN_COULEUR)
            .texteCouleur(UPDATED_TEXTE_COULEUR)
            .salle(UPDATED_SALLE)
            .commentaire(UPDATED_COMMENTAIRE)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabRendezvousMockMvc.perform(put("/api/tab-rendezvous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabRendezvous)))
            .andExpect(status().isOk());

        // Validate the TabRendezvous in the database
        List<TabRendezvous> tabRendezvousList = tabRendezvousRepository.findAll();
        assertThat(tabRendezvousList).hasSize(databaseSizeBeforeUpdate);
        TabRendezvous testTabRendezvous = tabRendezvousList.get(tabRendezvousList.size() - 1);
        assertThat(testTabRendezvous.getTitre()).isEqualTo(UPDATED_TITRE);
        assertThat(testTabRendezvous.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testTabRendezvous.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testTabRendezvous.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testTabRendezvous.isJourEntier()).isEqualTo(UPDATED_JOUR_ENTIER);
        assertThat(testTabRendezvous.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testTabRendezvous.getArrierePlanCouleur()).isEqualTo(UPDATED_ARRIERE_PLAN_COULEUR);
        assertThat(testTabRendezvous.getTexteCouleur()).isEqualTo(UPDATED_TEXTE_COULEUR);
        assertThat(testTabRendezvous.getSalle()).isEqualTo(UPDATED_SALLE);
        assertThat(testTabRendezvous.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testTabRendezvous.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabRendezvous.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabRendezvous.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabRendezvous.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabRendezvous() throws Exception {
        int databaseSizeBeforeUpdate = tabRendezvousRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabRendezvousMockMvc.perform(put("/api/tab-rendezvous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabRendezvous)))
            .andExpect(status().isBadRequest());

        // Validate the TabRendezvous in the database
        List<TabRendezvous> tabRendezvousList = tabRendezvousRepository.findAll();
        assertThat(tabRendezvousList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabRendezvous() throws Exception {
        // Initialize the database
        tabRendezvousRepository.saveAndFlush(tabRendezvous);

        int databaseSizeBeforeDelete = tabRendezvousRepository.findAll().size();

        // Delete the tabRendezvous
        restTabRendezvousMockMvc.perform(delete("/api/tab-rendezvous/{id}", tabRendezvous.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabRendezvous> tabRendezvousList = tabRendezvousRepository.findAll();
        assertThat(tabRendezvousList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
