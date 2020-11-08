package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabUser;
import com.mycompany.myapp.repository.TabUserRepository;

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
 * Integration tests for the {@link TabUserResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabUserResourceIT {

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_MDP = "AAAAAAAAAA";
    private static final String UPDATED_MDP = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_NAISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_DATE_NAISSANCE = "BBBBBBBBBB";

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EST_ACTIF = false;
    private static final Boolean UPDATED_EST_ACTIF = true;

    private static final String DEFAULT_DMAJ_MDP = "AAAAAAAAAA";
    private static final String UPDATED_DMAJ_MDP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STA_CONNEX = false;
    private static final Boolean UPDATED_STA_CONNEX = true;

    private static final String DEFAULT_MATRICULE_CREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_CREATION = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULE_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATION = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_MODIF = "AAAAAAAAAA";
    private static final String UPDATED_DATE_MODIF = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUE = "BBBBBBBBBB";

    @Autowired
    private TabUserRepository tabUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabUserMockMvc;

    private TabUser tabUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUser createEntity(EntityManager em) {
        TabUser tabUser = new TabUser()
            .login(DEFAULT_LOGIN)
            .mdp(DEFAULT_MDP)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .genre(DEFAULT_GENRE)
            .sexe(DEFAULT_SEXE)
            .telephone(DEFAULT_TELEPHONE)
            .email(DEFAULT_EMAIL)
            .estActif(DEFAULT_EST_ACTIF)
            .dmajMdp(DEFAULT_DMAJ_MDP)
            .staConnex(DEFAULT_STA_CONNEX)
            .matriculeCreation(DEFAULT_MATRICULE_CREATION)
            .matriculeModif(DEFAULT_MATRICULE_MODIF)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModif(DEFAULT_DATE_MODIF)
            .langue(DEFAULT_LANGUE);
        return tabUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabUser createUpdatedEntity(EntityManager em) {
        TabUser tabUser = new TabUser()
            .login(UPDATED_LOGIN)
            .mdp(UPDATED_MDP)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .sexe(UPDATED_SEXE)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .estActif(UPDATED_EST_ACTIF)
            .dmajMdp(UPDATED_DMAJ_MDP)
            .staConnex(UPDATED_STA_CONNEX)
            .matriculeCreation(UPDATED_MATRICULE_CREATION)
            .matriculeModif(UPDATED_MATRICULE_MODIF)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModif(UPDATED_DATE_MODIF)
            .langue(UPDATED_LANGUE);
        return tabUser;
    }

    @BeforeEach
    public void initTest() {
        tabUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabUser() throws Exception {
        int databaseSizeBeforeCreate = tabUserRepository.findAll().size();
        // Create the TabUser
        restTabUserMockMvc.perform(post("/api/tab-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUser)))
            .andExpect(status().isCreated());

        // Validate the TabUser in the database
        List<TabUser> tabUserList = tabUserRepository.findAll();
        assertThat(tabUserList).hasSize(databaseSizeBeforeCreate + 1);
        TabUser testTabUser = tabUserList.get(tabUserList.size() - 1);
        assertThat(testTabUser.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testTabUser.getMdp()).isEqualTo(DEFAULT_MDP);
        assertThat(testTabUser.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTabUser.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testTabUser.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testTabUser.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testTabUser.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testTabUser.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testTabUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTabUser.isEstActif()).isEqualTo(DEFAULT_EST_ACTIF);
        assertThat(testTabUser.getDmajMdp()).isEqualTo(DEFAULT_DMAJ_MDP);
        assertThat(testTabUser.isStaConnex()).isEqualTo(DEFAULT_STA_CONNEX);
        assertThat(testTabUser.getMatriculeCreation()).isEqualTo(DEFAULT_MATRICULE_CREATION);
        assertThat(testTabUser.getMatriculeModif()).isEqualTo(DEFAULT_MATRICULE_MODIF);
        assertThat(testTabUser.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testTabUser.getDateModif()).isEqualTo(DEFAULT_DATE_MODIF);
        assertThat(testTabUser.getLangue()).isEqualTo(DEFAULT_LANGUE);
    }

    @Test
    @Transactional
    public void createTabUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabUserRepository.findAll().size();

        // Create the TabUser with an existing ID
        tabUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabUserMockMvc.perform(post("/api/tab-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUser)))
            .andExpect(status().isBadRequest());

        // Validate the TabUser in the database
        List<TabUser> tabUserList = tabUserRepository.findAll();
        assertThat(tabUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabUsers() throws Exception {
        // Initialize the database
        tabUserRepository.saveAndFlush(tabUser);

        // Get all the tabUserList
        restTabUserMockMvc.perform(get("/api/tab-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].mdp").value(hasItem(DEFAULT_MDP)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].estActif").value(hasItem(DEFAULT_EST_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].dmajMdp").value(hasItem(DEFAULT_DMAJ_MDP)))
            .andExpect(jsonPath("$.[*].staConnex").value(hasItem(DEFAULT_STA_CONNEX.booleanValue())))
            .andExpect(jsonPath("$.[*].matriculeCreation").value(hasItem(DEFAULT_MATRICULE_CREATION)))
            .andExpect(jsonPath("$.[*].matriculeModif").value(hasItem(DEFAULT_MATRICULE_MODIF)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION)))
            .andExpect(jsonPath("$.[*].dateModif").value(hasItem(DEFAULT_DATE_MODIF)))
            .andExpect(jsonPath("$.[*].langue").value(hasItem(DEFAULT_LANGUE)));
    }
    
    @Test
    @Transactional
    public void getTabUser() throws Exception {
        // Initialize the database
        tabUserRepository.saveAndFlush(tabUser);

        // Get the tabUser
        restTabUserMockMvc.perform(get("/api/tab-users/{id}", tabUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabUser.getId().intValue()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.mdp").value(DEFAULT_MDP))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.estActif").value(DEFAULT_EST_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.dmajMdp").value(DEFAULT_DMAJ_MDP))
            .andExpect(jsonPath("$.staConnex").value(DEFAULT_STA_CONNEX.booleanValue()))
            .andExpect(jsonPath("$.matriculeCreation").value(DEFAULT_MATRICULE_CREATION))
            .andExpect(jsonPath("$.matriculeModif").value(DEFAULT_MATRICULE_MODIF))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION))
            .andExpect(jsonPath("$.dateModif").value(DEFAULT_DATE_MODIF))
            .andExpect(jsonPath("$.langue").value(DEFAULT_LANGUE));
    }
    @Test
    @Transactional
    public void getNonExistingTabUser() throws Exception {
        // Get the tabUser
        restTabUserMockMvc.perform(get("/api/tab-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabUser() throws Exception {
        // Initialize the database
        tabUserRepository.saveAndFlush(tabUser);

        int databaseSizeBeforeUpdate = tabUserRepository.findAll().size();

        // Update the tabUser
        TabUser updatedTabUser = tabUserRepository.findById(tabUser.getId()).get();
        // Disconnect from session so that the updates on updatedTabUser are not directly saved in db
        em.detach(updatedTabUser);
        updatedTabUser
            .login(UPDATED_LOGIN)
            .mdp(UPDATED_MDP)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .sexe(UPDATED_SEXE)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .estActif(UPDATED_EST_ACTIF)
            .dmajMdp(UPDATED_DMAJ_MDP)
            .staConnex(UPDATED_STA_CONNEX)
            .matriculeCreation(UPDATED_MATRICULE_CREATION)
            .matriculeModif(UPDATED_MATRICULE_MODIF)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModif(UPDATED_DATE_MODIF)
            .langue(UPDATED_LANGUE);

        restTabUserMockMvc.perform(put("/api/tab-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabUser)))
            .andExpect(status().isOk());

        // Validate the TabUser in the database
        List<TabUser> tabUserList = tabUserRepository.findAll();
        assertThat(tabUserList).hasSize(databaseSizeBeforeUpdate);
        TabUser testTabUser = tabUserList.get(tabUserList.size() - 1);
        assertThat(testTabUser.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testTabUser.getMdp()).isEqualTo(UPDATED_MDP);
        assertThat(testTabUser.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTabUser.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testTabUser.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testTabUser.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testTabUser.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testTabUser.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testTabUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTabUser.isEstActif()).isEqualTo(UPDATED_EST_ACTIF);
        assertThat(testTabUser.getDmajMdp()).isEqualTo(UPDATED_DMAJ_MDP);
        assertThat(testTabUser.isStaConnex()).isEqualTo(UPDATED_STA_CONNEX);
        assertThat(testTabUser.getMatriculeCreation()).isEqualTo(UPDATED_MATRICULE_CREATION);
        assertThat(testTabUser.getMatriculeModif()).isEqualTo(UPDATED_MATRICULE_MODIF);
        assertThat(testTabUser.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testTabUser.getDateModif()).isEqualTo(UPDATED_DATE_MODIF);
        assertThat(testTabUser.getLangue()).isEqualTo(UPDATED_LANGUE);
    }

    @Test
    @Transactional
    public void updateNonExistingTabUser() throws Exception {
        int databaseSizeBeforeUpdate = tabUserRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabUserMockMvc.perform(put("/api/tab-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabUser)))
            .andExpect(status().isBadRequest());

        // Validate the TabUser in the database
        List<TabUser> tabUserList = tabUserRepository.findAll();
        assertThat(tabUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabUser() throws Exception {
        // Initialize the database
        tabUserRepository.saveAndFlush(tabUser);

        int databaseSizeBeforeDelete = tabUserRepository.findAll().size();

        // Delete the tabUser
        restTabUserMockMvc.perform(delete("/api/tab-users/{id}", tabUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabUser> tabUserList = tabUserRepository.findAll();
        assertThat(tabUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
