package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabCoordonnee;
import com.mycompany.myapp.repository.TabCoordonneeRepository;

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
 * Integration tests for the {@link TabCoordonneeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabCoordonneeResourceIT {

    private static final String DEFAULT_QUARTIER = "AAAAAAAAAA";
    private static final String UPDATED_QUARTIER = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_VILLE = "BBBBBBBBBB";

    private static final String DEFAULT_RUE = "AAAAAAAAAA";
    private static final String UPDATED_RUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_PORTABLE = "AAAAAAAAAA";
    private static final String UPDATED_PORTABLE = "BBBBBBBBBB";

    private static final String DEFAULT_PREVENIR = "AAAAAAAAAA";
    private static final String UPDATED_PREVENIR = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_PREVENIR = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_PREVENIR = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_LIBELLE_LONG = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_LIBELLE_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_MATRICULECREATION = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULECREATION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATECREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATECREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MATRICULEMODIF = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULEMODIF = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATEMODIF = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATEMODIF = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TabCoordonneeRepository tabCoordonneeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabCoordonneeMockMvc;

    private TabCoordonnee tabCoordonnee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabCoordonnee createEntity(EntityManager em) {
        TabCoordonnee tabCoordonnee = new TabCoordonnee()
            .quartier(DEFAULT_QUARTIER)
            .commune(DEFAULT_COMMUNE)
            .ville(DEFAULT_VILLE)
            .codeVille(DEFAULT_CODE_VILLE)
            .rue(DEFAULT_RUE)
            .numero(DEFAULT_NUMERO)
            .telephone(DEFAULT_TELEPHONE)
            .portable(DEFAULT_PORTABLE)
            .prevenir(DEFAULT_PREVENIR)
            .fax(DEFAULT_FAX)
            .adresseMail(DEFAULT_ADRESSE_MAIL)
            .adressePrevenir(DEFAULT_ADRESSE_PREVENIR)
            .adresseLibelleLong(DEFAULT_ADRESSE_LIBELLE_LONG)
            .matriculecreation(DEFAULT_MATRICULECREATION)
            .datecreation(DEFAULT_DATECREATION)
            .matriculemodif(DEFAULT_MATRICULEMODIF)
            .datemodif(DEFAULT_DATEMODIF);
        return tabCoordonnee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabCoordonnee createUpdatedEntity(EntityManager em) {
        TabCoordonnee tabCoordonnee = new TabCoordonnee()
            .quartier(UPDATED_QUARTIER)
            .commune(UPDATED_COMMUNE)
            .ville(UPDATED_VILLE)
            .codeVille(UPDATED_CODE_VILLE)
            .rue(UPDATED_RUE)
            .numero(UPDATED_NUMERO)
            .telephone(UPDATED_TELEPHONE)
            .portable(UPDATED_PORTABLE)
            .prevenir(UPDATED_PREVENIR)
            .fax(UPDATED_FAX)
            .adresseMail(UPDATED_ADRESSE_MAIL)
            .adressePrevenir(UPDATED_ADRESSE_PREVENIR)
            .adresseLibelleLong(UPDATED_ADRESSE_LIBELLE_LONG)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);
        return tabCoordonnee;
    }

    @BeforeEach
    public void initTest() {
        tabCoordonnee = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabCoordonnee() throws Exception {
        int databaseSizeBeforeCreate = tabCoordonneeRepository.findAll().size();
        // Create the TabCoordonnee
        restTabCoordonneeMockMvc.perform(post("/api/tab-coordonnees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabCoordonnee)))
            .andExpect(status().isCreated());

        // Validate the TabCoordonnee in the database
        List<TabCoordonnee> tabCoordonneeList = tabCoordonneeRepository.findAll();
        assertThat(tabCoordonneeList).hasSize(databaseSizeBeforeCreate + 1);
        TabCoordonnee testTabCoordonnee = tabCoordonneeList.get(tabCoordonneeList.size() - 1);
        assertThat(testTabCoordonnee.getQuartier()).isEqualTo(DEFAULT_QUARTIER);
        assertThat(testTabCoordonnee.getCommune()).isEqualTo(DEFAULT_COMMUNE);
        assertThat(testTabCoordonnee.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testTabCoordonnee.getCodeVille()).isEqualTo(DEFAULT_CODE_VILLE);
        assertThat(testTabCoordonnee.getRue()).isEqualTo(DEFAULT_RUE);
        assertThat(testTabCoordonnee.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTabCoordonnee.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testTabCoordonnee.getPortable()).isEqualTo(DEFAULT_PORTABLE);
        assertThat(testTabCoordonnee.getPrevenir()).isEqualTo(DEFAULT_PREVENIR);
        assertThat(testTabCoordonnee.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testTabCoordonnee.getAdresseMail()).isEqualTo(DEFAULT_ADRESSE_MAIL);
        assertThat(testTabCoordonnee.getAdressePrevenir()).isEqualTo(DEFAULT_ADRESSE_PREVENIR);
        assertThat(testTabCoordonnee.getAdresseLibelleLong()).isEqualTo(DEFAULT_ADRESSE_LIBELLE_LONG);
        assertThat(testTabCoordonnee.getMatriculecreation()).isEqualTo(DEFAULT_MATRICULECREATION);
        assertThat(testTabCoordonnee.getDatecreation()).isEqualTo(DEFAULT_DATECREATION);
        assertThat(testTabCoordonnee.getMatriculemodif()).isEqualTo(DEFAULT_MATRICULEMODIF);
        assertThat(testTabCoordonnee.getDatemodif()).isEqualTo(DEFAULT_DATEMODIF);
    }

    @Test
    @Transactional
    public void createTabCoordonneeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabCoordonneeRepository.findAll().size();

        // Create the TabCoordonnee with an existing ID
        tabCoordonnee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabCoordonneeMockMvc.perform(post("/api/tab-coordonnees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabCoordonnee)))
            .andExpect(status().isBadRequest());

        // Validate the TabCoordonnee in the database
        List<TabCoordonnee> tabCoordonneeList = tabCoordonneeRepository.findAll();
        assertThat(tabCoordonneeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabCoordonnees() throws Exception {
        // Initialize the database
        tabCoordonneeRepository.saveAndFlush(tabCoordonnee);

        // Get all the tabCoordonneeList
        restTabCoordonneeMockMvc.perform(get("/api/tab-coordonnees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabCoordonnee.getId().intValue())))
            .andExpect(jsonPath("$.[*].quartier").value(hasItem(DEFAULT_QUARTIER)))
            .andExpect(jsonPath("$.[*].commune").value(hasItem(DEFAULT_COMMUNE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].codeVille").value(hasItem(DEFAULT_CODE_VILLE)))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].portable").value(hasItem(DEFAULT_PORTABLE)))
            .andExpect(jsonPath("$.[*].prevenir").value(hasItem(DEFAULT_PREVENIR)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].adresseMail").value(hasItem(DEFAULT_ADRESSE_MAIL)))
            .andExpect(jsonPath("$.[*].adressePrevenir").value(hasItem(DEFAULT_ADRESSE_PREVENIR)))
            .andExpect(jsonPath("$.[*].adresseLibelleLong").value(hasItem(DEFAULT_ADRESSE_LIBELLE_LONG)))
            .andExpect(jsonPath("$.[*].matriculecreation").value(hasItem(DEFAULT_MATRICULECREATION)))
            .andExpect(jsonPath("$.[*].datecreation").value(hasItem(sameInstant(DEFAULT_DATECREATION))))
            .andExpect(jsonPath("$.[*].matriculemodif").value(hasItem(DEFAULT_MATRICULEMODIF)))
            .andExpect(jsonPath("$.[*].datemodif").value(hasItem(sameInstant(DEFAULT_DATEMODIF))));
    }
    
    @Test
    @Transactional
    public void getTabCoordonnee() throws Exception {
        // Initialize the database
        tabCoordonneeRepository.saveAndFlush(tabCoordonnee);

        // Get the tabCoordonnee
        restTabCoordonneeMockMvc.perform(get("/api/tab-coordonnees/{id}", tabCoordonnee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabCoordonnee.getId().intValue()))
            .andExpect(jsonPath("$.quartier").value(DEFAULT_QUARTIER))
            .andExpect(jsonPath("$.commune").value(DEFAULT_COMMUNE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.codeVille").value(DEFAULT_CODE_VILLE))
            .andExpect(jsonPath("$.rue").value(DEFAULT_RUE))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.portable").value(DEFAULT_PORTABLE))
            .andExpect(jsonPath("$.prevenir").value(DEFAULT_PREVENIR))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.adresseMail").value(DEFAULT_ADRESSE_MAIL))
            .andExpect(jsonPath("$.adressePrevenir").value(DEFAULT_ADRESSE_PREVENIR))
            .andExpect(jsonPath("$.adresseLibelleLong").value(DEFAULT_ADRESSE_LIBELLE_LONG))
            .andExpect(jsonPath("$.matriculecreation").value(DEFAULT_MATRICULECREATION))
            .andExpect(jsonPath("$.datecreation").value(sameInstant(DEFAULT_DATECREATION)))
            .andExpect(jsonPath("$.matriculemodif").value(DEFAULT_MATRICULEMODIF))
            .andExpect(jsonPath("$.datemodif").value(sameInstant(DEFAULT_DATEMODIF)));
    }
    @Test
    @Transactional
    public void getNonExistingTabCoordonnee() throws Exception {
        // Get the tabCoordonnee
        restTabCoordonneeMockMvc.perform(get("/api/tab-coordonnees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabCoordonnee() throws Exception {
        // Initialize the database
        tabCoordonneeRepository.saveAndFlush(tabCoordonnee);

        int databaseSizeBeforeUpdate = tabCoordonneeRepository.findAll().size();

        // Update the tabCoordonnee
        TabCoordonnee updatedTabCoordonnee = tabCoordonneeRepository.findById(tabCoordonnee.getId()).get();
        // Disconnect from session so that the updates on updatedTabCoordonnee are not directly saved in db
        em.detach(updatedTabCoordonnee);
        updatedTabCoordonnee
            .quartier(UPDATED_QUARTIER)
            .commune(UPDATED_COMMUNE)
            .ville(UPDATED_VILLE)
            .codeVille(UPDATED_CODE_VILLE)
            .rue(UPDATED_RUE)
            .numero(UPDATED_NUMERO)
            .telephone(UPDATED_TELEPHONE)
            .portable(UPDATED_PORTABLE)
            .prevenir(UPDATED_PREVENIR)
            .fax(UPDATED_FAX)
            .adresseMail(UPDATED_ADRESSE_MAIL)
            .adressePrevenir(UPDATED_ADRESSE_PREVENIR)
            .adresseLibelleLong(UPDATED_ADRESSE_LIBELLE_LONG)
            .matriculecreation(UPDATED_MATRICULECREATION)
            .datecreation(UPDATED_DATECREATION)
            .matriculemodif(UPDATED_MATRICULEMODIF)
            .datemodif(UPDATED_DATEMODIF);

        restTabCoordonneeMockMvc.perform(put("/api/tab-coordonnees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabCoordonnee)))
            .andExpect(status().isOk());

        // Validate the TabCoordonnee in the database
        List<TabCoordonnee> tabCoordonneeList = tabCoordonneeRepository.findAll();
        assertThat(tabCoordonneeList).hasSize(databaseSizeBeforeUpdate);
        TabCoordonnee testTabCoordonnee = tabCoordonneeList.get(tabCoordonneeList.size() - 1);
        assertThat(testTabCoordonnee.getQuartier()).isEqualTo(UPDATED_QUARTIER);
        assertThat(testTabCoordonnee.getCommune()).isEqualTo(UPDATED_COMMUNE);
        assertThat(testTabCoordonnee.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testTabCoordonnee.getCodeVille()).isEqualTo(UPDATED_CODE_VILLE);
        assertThat(testTabCoordonnee.getRue()).isEqualTo(UPDATED_RUE);
        assertThat(testTabCoordonnee.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTabCoordonnee.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testTabCoordonnee.getPortable()).isEqualTo(UPDATED_PORTABLE);
        assertThat(testTabCoordonnee.getPrevenir()).isEqualTo(UPDATED_PREVENIR);
        assertThat(testTabCoordonnee.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testTabCoordonnee.getAdresseMail()).isEqualTo(UPDATED_ADRESSE_MAIL);
        assertThat(testTabCoordonnee.getAdressePrevenir()).isEqualTo(UPDATED_ADRESSE_PREVENIR);
        assertThat(testTabCoordonnee.getAdresseLibelleLong()).isEqualTo(UPDATED_ADRESSE_LIBELLE_LONG);
        assertThat(testTabCoordonnee.getMatriculecreation()).isEqualTo(UPDATED_MATRICULECREATION);
        assertThat(testTabCoordonnee.getDatecreation()).isEqualTo(UPDATED_DATECREATION);
        assertThat(testTabCoordonnee.getMatriculemodif()).isEqualTo(UPDATED_MATRICULEMODIF);
        assertThat(testTabCoordonnee.getDatemodif()).isEqualTo(UPDATED_DATEMODIF);
    }

    @Test
    @Transactional
    public void updateNonExistingTabCoordonnee() throws Exception {
        int databaseSizeBeforeUpdate = tabCoordonneeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabCoordonneeMockMvc.perform(put("/api/tab-coordonnees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabCoordonnee)))
            .andExpect(status().isBadRequest());

        // Validate the TabCoordonnee in the database
        List<TabCoordonnee> tabCoordonneeList = tabCoordonneeRepository.findAll();
        assertThat(tabCoordonneeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabCoordonnee() throws Exception {
        // Initialize the database
        tabCoordonneeRepository.saveAndFlush(tabCoordonnee);

        int databaseSizeBeforeDelete = tabCoordonneeRepository.findAll().size();

        // Delete the tabCoordonnee
        restTabCoordonneeMockMvc.perform(delete("/api/tab-coordonnees/{id}", tabCoordonnee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabCoordonnee> tabCoordonneeList = tabCoordonneeRepository.findAll();
        assertThat(tabCoordonneeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
