package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabGrossesse;
import com.mycompany.myapp.repository.TabGrossesseRepository;

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
 * Integration tests for the {@link TabGrossesseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabGrossesseResourceIT {

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
    private TabGrossesseRepository tabGrossesseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabGrossesseMockMvc;

    private TabGrossesse tabGrossesse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabGrossesse createEntity(EntityManager em) {
        TabGrossesse tabGrossesse = new TabGrossesse()
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
        return tabGrossesse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabGrossesse createUpdatedEntity(EntityManager em) {
        TabGrossesse tabGrossesse = new TabGrossesse()
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
        return tabGrossesse;
    }

    @BeforeEach
    public void initTest() {
        tabGrossesse = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabGrossesse() throws Exception {
        int databaseSizeBeforeCreate = tabGrossesseRepository.findAll().size();
        // Create the TabGrossesse
        restTabGrossesseMockMvc.perform(post("/api/tab-grossesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGrossesse)))
            .andExpect(status().isCreated());

        // Validate the TabGrossesse in the database
        List<TabGrossesse> tabGrossesseList = tabGrossesseRepository.findAll();
        assertThat(tabGrossesseList).hasSize(databaseSizeBeforeCreate + 1);
        TabGrossesse testTabGrossesse = tabGrossesseList.get(tabGrossesseList.size() - 1);
        assertThat(testTabGrossesse.getDateConsult()).isEqualTo(DEFAULT_DATE_CONSULT);
        assertThat(testTabGrossesse.getDdr()).isEqualTo(DEFAULT_DDR);
        assertThat(testTabGrossesse.getTermeUs()).isEqualTo(DEFAULT_TERME_US);
        assertThat(testTabGrossesse.getDateOvulation()).isEqualTo(DEFAULT_DATE_OVULATION);
        assertThat(testTabGrossesse.getAgeGestationel()).isEqualTo(DEFAULT_AGE_GESTATIONEL);
        assertThat(testTabGrossesse.getTermCorrige()).isEqualTo(DEFAULT_TERM_CORRIGE);
        assertThat(testTabGrossesse.getPerine()).isEqualTo(DEFAULT_PERINE);
        assertThat(testTabGrossesse.getBassin()).isEqualTo(DEFAULT_BASSIN);
        assertThat(testTabGrossesse.getOgtt()).isEqualTo(DEFAULT_OGTT);
        assertThat(testTabGrossesse.getSuiviPar()).isEqualTo(DEFAULT_SUIVI_PAR);
        assertThat(testTabGrossesse.getImc()).isEqualTo(DEFAULT_IMC);
        assertThat(testTabGrossesse.getPoidsMereInitial()).isEqualTo(DEFAULT_POIDS_MERE_INITIAL);
        assertThat(testTabGrossesse.getPoidsMereTheoriBebe()).isEqualTo(DEFAULT_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabGrossesse.getTailleMere()).isEqualTo(DEFAULT_TAILLE_MERE);
        assertThat(testTabGrossesse.getTailleTheoriBebe()).isEqualTo(DEFAULT_TAILLE_THEORI_BEBE);
        assertThat(testTabGrossesse.getLaboTri21()).isEqualTo(DEFAULT_LABO_TRI_21);
        assertThat(testTabGrossesse.getResumeGrossesse()).isEqualTo(DEFAULT_RESUME_GROSSESSE);
        assertThat(testTabGrossesse.getConduiteAccouche()).isEqualTo(DEFAULT_CONDUITE_ACCOUCHE);
        assertThat(testTabGrossesse.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabGrossesseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabGrossesseRepository.findAll().size();

        // Create the TabGrossesse with an existing ID
        tabGrossesse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabGrossesseMockMvc.perform(post("/api/tab-grossesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGrossesse)))
            .andExpect(status().isBadRequest());

        // Validate the TabGrossesse in the database
        List<TabGrossesse> tabGrossesseList = tabGrossesseRepository.findAll();
        assertThat(tabGrossesseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabGrossesses() throws Exception {
        // Initialize the database
        tabGrossesseRepository.saveAndFlush(tabGrossesse);

        // Get all the tabGrossesseList
        restTabGrossesseMockMvc.perform(get("/api/tab-grossesses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabGrossesse.getId().intValue())))
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
    public void getTabGrossesse() throws Exception {
        // Initialize the database
        tabGrossesseRepository.saveAndFlush(tabGrossesse);

        // Get the tabGrossesse
        restTabGrossesseMockMvc.perform(get("/api/tab-grossesses/{id}", tabGrossesse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabGrossesse.getId().intValue()))
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
    public void getNonExistingTabGrossesse() throws Exception {
        // Get the tabGrossesse
        restTabGrossesseMockMvc.perform(get("/api/tab-grossesses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabGrossesse() throws Exception {
        // Initialize the database
        tabGrossesseRepository.saveAndFlush(tabGrossesse);

        int databaseSizeBeforeUpdate = tabGrossesseRepository.findAll().size();

        // Update the tabGrossesse
        TabGrossesse updatedTabGrossesse = tabGrossesseRepository.findById(tabGrossesse.getId()).get();
        // Disconnect from session so that the updates on updatedTabGrossesse are not directly saved in db
        em.detach(updatedTabGrossesse);
        updatedTabGrossesse
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

        restTabGrossesseMockMvc.perform(put("/api/tab-grossesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabGrossesse)))
            .andExpect(status().isOk());

        // Validate the TabGrossesse in the database
        List<TabGrossesse> tabGrossesseList = tabGrossesseRepository.findAll();
        assertThat(tabGrossesseList).hasSize(databaseSizeBeforeUpdate);
        TabGrossesse testTabGrossesse = tabGrossesseList.get(tabGrossesseList.size() - 1);
        assertThat(testTabGrossesse.getDateConsult()).isEqualTo(UPDATED_DATE_CONSULT);
        assertThat(testTabGrossesse.getDdr()).isEqualTo(UPDATED_DDR);
        assertThat(testTabGrossesse.getTermeUs()).isEqualTo(UPDATED_TERME_US);
        assertThat(testTabGrossesse.getDateOvulation()).isEqualTo(UPDATED_DATE_OVULATION);
        assertThat(testTabGrossesse.getAgeGestationel()).isEqualTo(UPDATED_AGE_GESTATIONEL);
        assertThat(testTabGrossesse.getTermCorrige()).isEqualTo(UPDATED_TERM_CORRIGE);
        assertThat(testTabGrossesse.getPerine()).isEqualTo(UPDATED_PERINE);
        assertThat(testTabGrossesse.getBassin()).isEqualTo(UPDATED_BASSIN);
        assertThat(testTabGrossesse.getOgtt()).isEqualTo(UPDATED_OGTT);
        assertThat(testTabGrossesse.getSuiviPar()).isEqualTo(UPDATED_SUIVI_PAR);
        assertThat(testTabGrossesse.getImc()).isEqualTo(UPDATED_IMC);
        assertThat(testTabGrossesse.getPoidsMereInitial()).isEqualTo(UPDATED_POIDS_MERE_INITIAL);
        assertThat(testTabGrossesse.getPoidsMereTheoriBebe()).isEqualTo(UPDATED_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabGrossesse.getTailleMere()).isEqualTo(UPDATED_TAILLE_MERE);
        assertThat(testTabGrossesse.getTailleTheoriBebe()).isEqualTo(UPDATED_TAILLE_THEORI_BEBE);
        assertThat(testTabGrossesse.getLaboTri21()).isEqualTo(UPDATED_LABO_TRI_21);
        assertThat(testTabGrossesse.getResumeGrossesse()).isEqualTo(UPDATED_RESUME_GROSSESSE);
        assertThat(testTabGrossesse.getConduiteAccouche()).isEqualTo(UPDATED_CONDUITE_ACCOUCHE);
        assertThat(testTabGrossesse.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabGrossesse() throws Exception {
        int databaseSizeBeforeUpdate = tabGrossesseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabGrossesseMockMvc.perform(put("/api/tab-grossesses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGrossesse)))
            .andExpect(status().isBadRequest());

        // Validate the TabGrossesse in the database
        List<TabGrossesse> tabGrossesseList = tabGrossesseRepository.findAll();
        assertThat(tabGrossesseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabGrossesse() throws Exception {
        // Initialize the database
        tabGrossesseRepository.saveAndFlush(tabGrossesse);

        int databaseSizeBeforeDelete = tabGrossesseRepository.findAll().size();

        // Delete the tabGrossesse
        restTabGrossesseMockMvc.perform(delete("/api/tab-grossesses/{id}", tabGrossesse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabGrossesse> tabGrossesseList = tabGrossesseRepository.findAll();
        assertThat(tabGrossesseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
