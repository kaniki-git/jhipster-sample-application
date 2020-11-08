package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabConsObst;
import com.mycompany.myapp.repository.TabConsObstRepository;

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
 * Integration tests for the {@link TabConsObstResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabConsObstResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_CONSULT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CONSULT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DDR = "AAAAAAAAAA";
    private static final String UPDATED_DDR = "BBBBBBBBBB";

    private static final String DEFAULT_TERME_US = "AAAAAAAAAA";
    private static final String UPDATED_TERME_US = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OVULATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OVULATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_AGE_GESTATIONEL = "AAAAAAAAAA";
    private static final String UPDATED_AGE_GESTATIONEL = "BBBBBBBBBB";

    private static final String DEFAULT_TERM_CORRIGE = "AAAAAAAAAA";
    private static final String UPDATED_TERM_CORRIGE = "BBBBBBBBBB";

    private static final String DEFAULT_PERINE = "AAAAAAAAAA";
    private static final String UPDATED_PERINE = "BBBBBBBBBB";

    private static final String DEFAULT_BASSIN = "AAAAAAAAAA";
    private static final String UPDATED_BASSIN = "BBBBBBBBBB";

    private static final String DEFAULT_OGTT = "AAAAAAAAAA";
    private static final String UPDATED_OGTT = "BBBBBBBBBB";

    private static final String DEFAULT_SUIVI_PAR = "AAAAAAAAAA";
    private static final String UPDATED_SUIVI_PAR = "BBBBBBBBBB";

    private static final String DEFAULT_IMC = "AAAAAAAAAA";
    private static final String UPDATED_IMC = "BBBBBBBBBB";

    private static final Float DEFAULT_POIDS_MERE_INITIAL = 1F;
    private static final Float UPDATED_POIDS_MERE_INITIAL = 2F;

    private static final Float DEFAULT_POIDS_MERE_THEORI_BEBE = 1F;
    private static final Float UPDATED_POIDS_MERE_THEORI_BEBE = 2F;

    private static final Float DEFAULT_TAILLE_MERE = 1F;
    private static final Float UPDATED_TAILLE_MERE = 2F;

    private static final Float DEFAULT_TAILLE_THEORI_BEBE = 1F;
    private static final Float UPDATED_TAILLE_THEORI_BEBE = 2F;

    private static final String DEFAULT_LABO_TRI_21 = "AAAAAAAAAA";
    private static final String UPDATED_LABO_TRI_21 = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME_GROSSESSE = "AAAAAAAAAA";
    private static final String UPDATED_RESUME_GROSSESSE = "BBBBBBBBBB";

    private static final String DEFAULT_CONDUITE_ACCOUCHE = "AAAAAAAAAA";
    private static final String UPDATED_CONDUITE_ACCOUCHE = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabConsObstRepository tabConsObstRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabConsObstMockMvc;

    private TabConsObst tabConsObst;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsObst createEntity(EntityManager em) {
        TabConsObst tabConsObst = new TabConsObst()
            .dateConsult(DEFAULT_DATE_CONSULT)
            .ddr(DEFAULT_DDR)
            .termeUs(DEFAULT_TERME_US)
            .dateOvulation(DEFAULT_DATE_OVULATION)
            .ageGestationel(DEFAULT_AGE_GESTATIONEL)
            .termCorrige(DEFAULT_TERM_CORRIGE)
            .perine(DEFAULT_PERINE)
            .bassin(DEFAULT_BASSIN)
            .ogtt(DEFAULT_OGTT)
            .suiviPar(DEFAULT_SUIVI_PAR)
            .imc(DEFAULT_IMC)
            .poidsMereInitial(DEFAULT_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(DEFAULT_POIDS_MERE_THEORI_BEBE)
            .tailleMere(DEFAULT_TAILLE_MERE)
            .tailleTheoriBebe(DEFAULT_TAILLE_THEORI_BEBE)
            .laboTri21(DEFAULT_LABO_TRI_21)
            .resumeGrossesse(DEFAULT_RESUME_GROSSESSE)
            .conduiteAccouche(DEFAULT_CONDUITE_ACCOUCHE)
            .rapport(DEFAULT_RAPPORT);
        return tabConsObst;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabConsObst createUpdatedEntity(EntityManager em) {
        TabConsObst tabConsObst = new TabConsObst()
            .dateConsult(UPDATED_DATE_CONSULT)
            .ddr(UPDATED_DDR)
            .termeUs(UPDATED_TERME_US)
            .dateOvulation(UPDATED_DATE_OVULATION)
            .ageGestationel(UPDATED_AGE_GESTATIONEL)
            .termCorrige(UPDATED_TERM_CORRIGE)
            .perine(UPDATED_PERINE)
            .bassin(UPDATED_BASSIN)
            .ogtt(UPDATED_OGTT)
            .suiviPar(UPDATED_SUIVI_PAR)
            .imc(UPDATED_IMC)
            .poidsMereInitial(UPDATED_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(UPDATED_POIDS_MERE_THEORI_BEBE)
            .tailleMere(UPDATED_TAILLE_MERE)
            .tailleTheoriBebe(UPDATED_TAILLE_THEORI_BEBE)
            .laboTri21(UPDATED_LABO_TRI_21)
            .resumeGrossesse(UPDATED_RESUME_GROSSESSE)
            .conduiteAccouche(UPDATED_CONDUITE_ACCOUCHE)
            .rapport(UPDATED_RAPPORT);
        return tabConsObst;
    }

    @BeforeEach
    public void initTest() {
        tabConsObst = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabConsObst() throws Exception {
        int databaseSizeBeforeCreate = tabConsObstRepository.findAll().size();
        // Create the TabConsObst
        restTabConsObstMockMvc.perform(post("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isCreated());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeCreate + 1);
        TabConsObst testTabConsObst = tabConsObstList.get(tabConsObstList.size() - 1);
        assertThat(testTabConsObst.getDateConsult()).isEqualTo(DEFAULT_DATE_CONSULT);
        assertThat(testTabConsObst.getDdr()).isEqualTo(DEFAULT_DDR);
        assertThat(testTabConsObst.getTermeUs()).isEqualTo(DEFAULT_TERME_US);
        assertThat(testTabConsObst.getDateOvulation()).isEqualTo(DEFAULT_DATE_OVULATION);
        assertThat(testTabConsObst.getAgeGestationel()).isEqualTo(DEFAULT_AGE_GESTATIONEL);
        assertThat(testTabConsObst.getTermCorrige()).isEqualTo(DEFAULT_TERM_CORRIGE);
        assertThat(testTabConsObst.getPerine()).isEqualTo(DEFAULT_PERINE);
        assertThat(testTabConsObst.getBassin()).isEqualTo(DEFAULT_BASSIN);
        assertThat(testTabConsObst.getOgtt()).isEqualTo(DEFAULT_OGTT);
        assertThat(testTabConsObst.getSuiviPar()).isEqualTo(DEFAULT_SUIVI_PAR);
        assertThat(testTabConsObst.getImc()).isEqualTo(DEFAULT_IMC);
        assertThat(testTabConsObst.getPoidsMereInitial()).isEqualTo(DEFAULT_POIDS_MERE_INITIAL);
        assertThat(testTabConsObst.getPoidsMereTheoriBebe()).isEqualTo(DEFAULT_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabConsObst.getTailleMere()).isEqualTo(DEFAULT_TAILLE_MERE);
        assertThat(testTabConsObst.getTailleTheoriBebe()).isEqualTo(DEFAULT_TAILLE_THEORI_BEBE);
        assertThat(testTabConsObst.getLaboTri21()).isEqualTo(DEFAULT_LABO_TRI_21);
        assertThat(testTabConsObst.getResumeGrossesse()).isEqualTo(DEFAULT_RESUME_GROSSESSE);
        assertThat(testTabConsObst.getConduiteAccouche()).isEqualTo(DEFAULT_CONDUITE_ACCOUCHE);
        assertThat(testTabConsObst.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabConsObstWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabConsObstRepository.findAll().size();

        // Create the TabConsObst with an existing ID
        tabConsObst.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabConsObstMockMvc.perform(post("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabConsObsts() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        // Get all the tabConsObstList
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabConsObst.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateConsult").value(hasItem(sameInstant(DEFAULT_DATE_CONSULT))))
            .andExpect(jsonPath("$.[*].ddr").value(hasItem(DEFAULT_DDR)))
            .andExpect(jsonPath("$.[*].termeUs").value(hasItem(DEFAULT_TERME_US)))
            .andExpect(jsonPath("$.[*].dateOvulation").value(hasItem(sameInstant(DEFAULT_DATE_OVULATION))))
            .andExpect(jsonPath("$.[*].ageGestationel").value(hasItem(DEFAULT_AGE_GESTATIONEL)))
            .andExpect(jsonPath("$.[*].termCorrige").value(hasItem(DEFAULT_TERM_CORRIGE)))
            .andExpect(jsonPath("$.[*].perine").value(hasItem(DEFAULT_PERINE)))
            .andExpect(jsonPath("$.[*].bassin").value(hasItem(DEFAULT_BASSIN)))
            .andExpect(jsonPath("$.[*].ogtt").value(hasItem(DEFAULT_OGTT)))
            .andExpect(jsonPath("$.[*].suiviPar").value(hasItem(DEFAULT_SUIVI_PAR)))
            .andExpect(jsonPath("$.[*].imc").value(hasItem(DEFAULT_IMC)))
            .andExpect(jsonPath("$.[*].poidsMereInitial").value(hasItem(DEFAULT_POIDS_MERE_INITIAL.doubleValue())))
            .andExpect(jsonPath("$.[*].poidsMereTheoriBebe").value(hasItem(DEFAULT_POIDS_MERE_THEORI_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].tailleMere").value(hasItem(DEFAULT_TAILLE_MERE.doubleValue())))
            .andExpect(jsonPath("$.[*].tailleTheoriBebe").value(hasItem(DEFAULT_TAILLE_THEORI_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].laboTri21").value(hasItem(DEFAULT_LABO_TRI_21)))
            .andExpect(jsonPath("$.[*].resumeGrossesse").value(hasItem(DEFAULT_RESUME_GROSSESSE)))
            .andExpect(jsonPath("$.[*].conduiteAccouche").value(hasItem(DEFAULT_CONDUITE_ACCOUCHE)))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @Test
    @Transactional
    public void getTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        // Get the tabConsObst
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts/{id}", tabConsObst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabConsObst.getId().intValue()))
            .andExpect(jsonPath("$.dateConsult").value(sameInstant(DEFAULT_DATE_CONSULT)))
            .andExpect(jsonPath("$.ddr").value(DEFAULT_DDR))
            .andExpect(jsonPath("$.termeUs").value(DEFAULT_TERME_US))
            .andExpect(jsonPath("$.dateOvulation").value(sameInstant(DEFAULT_DATE_OVULATION)))
            .andExpect(jsonPath("$.ageGestationel").value(DEFAULT_AGE_GESTATIONEL))
            .andExpect(jsonPath("$.termCorrige").value(DEFAULT_TERM_CORRIGE))
            .andExpect(jsonPath("$.perine").value(DEFAULT_PERINE))
            .andExpect(jsonPath("$.bassin").value(DEFAULT_BASSIN))
            .andExpect(jsonPath("$.ogtt").value(DEFAULT_OGTT))
            .andExpect(jsonPath("$.suiviPar").value(DEFAULT_SUIVI_PAR))
            .andExpect(jsonPath("$.imc").value(DEFAULT_IMC))
            .andExpect(jsonPath("$.poidsMereInitial").value(DEFAULT_POIDS_MERE_INITIAL.doubleValue()))
            .andExpect(jsonPath("$.poidsMereTheoriBebe").value(DEFAULT_POIDS_MERE_THEORI_BEBE.doubleValue()))
            .andExpect(jsonPath("$.tailleMere").value(DEFAULT_TAILLE_MERE.doubleValue()))
            .andExpect(jsonPath("$.tailleTheoriBebe").value(DEFAULT_TAILLE_THEORI_BEBE.doubleValue()))
            .andExpect(jsonPath("$.laboTri21").value(DEFAULT_LABO_TRI_21))
            .andExpect(jsonPath("$.resumeGrossesse").value(DEFAULT_RESUME_GROSSESSE))
            .andExpect(jsonPath("$.conduiteAccouche").value(DEFAULT_CONDUITE_ACCOUCHE))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabConsObst() throws Exception {
        // Get the tabConsObst
        restTabConsObstMockMvc.perform(get("/api/tab-cons-obsts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        int databaseSizeBeforeUpdate = tabConsObstRepository.findAll().size();

        // Update the tabConsObst
        TabConsObst updatedTabConsObst = tabConsObstRepository.findById(tabConsObst.getId()).get();
        // Disconnect from session so that the updates on updatedTabConsObst are not directly saved in db
        em.detach(updatedTabConsObst);
        updatedTabConsObst
            .dateConsult(UPDATED_DATE_CONSULT)
            .ddr(UPDATED_DDR)
            .termeUs(UPDATED_TERME_US)
            .dateOvulation(UPDATED_DATE_OVULATION)
            .ageGestationel(UPDATED_AGE_GESTATIONEL)
            .termCorrige(UPDATED_TERM_CORRIGE)
            .perine(UPDATED_PERINE)
            .bassin(UPDATED_BASSIN)
            .ogtt(UPDATED_OGTT)
            .suiviPar(UPDATED_SUIVI_PAR)
            .imc(UPDATED_IMC)
            .poidsMereInitial(UPDATED_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(UPDATED_POIDS_MERE_THEORI_BEBE)
            .tailleMere(UPDATED_TAILLE_MERE)
            .tailleTheoriBebe(UPDATED_TAILLE_THEORI_BEBE)
            .laboTri21(UPDATED_LABO_TRI_21)
            .resumeGrossesse(UPDATED_RESUME_GROSSESSE)
            .conduiteAccouche(UPDATED_CONDUITE_ACCOUCHE)
            .rapport(UPDATED_RAPPORT);

        restTabConsObstMockMvc.perform(put("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabConsObst)))
            .andExpect(status().isOk());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeUpdate);
        TabConsObst testTabConsObst = tabConsObstList.get(tabConsObstList.size() - 1);
        assertThat(testTabConsObst.getDateConsult()).isEqualTo(UPDATED_DATE_CONSULT);
        assertThat(testTabConsObst.getDdr()).isEqualTo(UPDATED_DDR);
        assertThat(testTabConsObst.getTermeUs()).isEqualTo(UPDATED_TERME_US);
        assertThat(testTabConsObst.getDateOvulation()).isEqualTo(UPDATED_DATE_OVULATION);
        assertThat(testTabConsObst.getAgeGestationel()).isEqualTo(UPDATED_AGE_GESTATIONEL);
        assertThat(testTabConsObst.getTermCorrige()).isEqualTo(UPDATED_TERM_CORRIGE);
        assertThat(testTabConsObst.getPerine()).isEqualTo(UPDATED_PERINE);
        assertThat(testTabConsObst.getBassin()).isEqualTo(UPDATED_BASSIN);
        assertThat(testTabConsObst.getOgtt()).isEqualTo(UPDATED_OGTT);
        assertThat(testTabConsObst.getSuiviPar()).isEqualTo(UPDATED_SUIVI_PAR);
        assertThat(testTabConsObst.getImc()).isEqualTo(UPDATED_IMC);
        assertThat(testTabConsObst.getPoidsMereInitial()).isEqualTo(UPDATED_POIDS_MERE_INITIAL);
        assertThat(testTabConsObst.getPoidsMereTheoriBebe()).isEqualTo(UPDATED_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabConsObst.getTailleMere()).isEqualTo(UPDATED_TAILLE_MERE);
        assertThat(testTabConsObst.getTailleTheoriBebe()).isEqualTo(UPDATED_TAILLE_THEORI_BEBE);
        assertThat(testTabConsObst.getLaboTri21()).isEqualTo(UPDATED_LABO_TRI_21);
        assertThat(testTabConsObst.getResumeGrossesse()).isEqualTo(UPDATED_RESUME_GROSSESSE);
        assertThat(testTabConsObst.getConduiteAccouche()).isEqualTo(UPDATED_CONDUITE_ACCOUCHE);
        assertThat(testTabConsObst.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabConsObst() throws Exception {
        int databaseSizeBeforeUpdate = tabConsObstRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabConsObstMockMvc.perform(put("/api/tab-cons-obsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabConsObst)))
            .andExpect(status().isBadRequest());

        // Validate the TabConsObst in the database
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabConsObst() throws Exception {
        // Initialize the database
        tabConsObstRepository.saveAndFlush(tabConsObst);

        int databaseSizeBeforeDelete = tabConsObstRepository.findAll().size();

        // Delete the tabConsObst
        restTabConsObstMockMvc.perform(delete("/api/tab-cons-obsts/{id}", tabConsObst.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabConsObst> tabConsObstList = tabConsObstRepository.findAll();
        assertThat(tabConsObstList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
