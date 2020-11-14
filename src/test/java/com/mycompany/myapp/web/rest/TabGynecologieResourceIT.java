package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabGynecologie;
import com.mycompany.myapp.repository.TabGynecologieRepository;

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
 * Integration tests for the {@link TabGynecologieResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabGynecologieResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_CONSULT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CONSULT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DDR = "AAAAAAAAAA";
    private static final String UPDATED_DDR = "BBBBBBBBBB";

    private static final String DEFAULT_TERME_US = "AAAAAAAAAA";
    private static final String UPDATED_TERME_US = "BBBBBBBBBB";

    private static final String DEFAULT_TERM_CORRIGE = "AAAAAAAAAA";
    private static final String UPDATED_TERM_CORRIGE = "BBBBBBBBBB";

    private static final String DEFAULT_TERM_DDR = "AAAAAAAAAA";
    private static final String UPDATED_TERM_DDR = "BBBBBBBBBB";

    private static final String DEFAULT_CYCLE = "AAAAAAAAAA";
    private static final String UPDATED_CYCLE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OVULATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OVULATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_AGE_GESTATIONEL = "AAAAAAAAAA";
    private static final String UPDATED_AGE_GESTATIONEL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TEST_PERI = "AAAAAAAAAA";
    private static final String UPDATED_TEST_PERI = "BBBBBBBBBB";

    private static final String DEFAULT_ECOUVILLON = "AAAAAAAAAA";
    private static final String UPDATED_ECOUVILLON = "BBBBBBBBBB";

    private static final String DEFAULT_PERINE = "AAAAAAAAAA";
    private static final String UPDATED_PERINE = "BBBBBBBBBB";

    private static final String DEFAULT_BASSIN = "AAAAAAAAAA";
    private static final String UPDATED_BASSIN = "BBBBBBBBBB";

    private static final String DEFAULT_OGTT = "AAAAAAAAAA";
    private static final String UPDATED_OGTT = "BBBBBBBBBB";

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

    private static final String DEFAULT_G_SG_MARI = "AAAAAAAAAA";
    private static final String UPDATED_G_SG_MARI = "BBBBBBBBBB";

    private static final String DEFAULT_LABO_TRI_21 = "AAAAAAAAAA";
    private static final String UPDATED_LABO_TRI_21 = "BBBBBBBBBB";

    private static final String DEFAULT_CARYOTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CARYOTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUIVI_PAR = "AAAAAAAAAA";
    private static final String UPDATED_SUIVI_PAR = "BBBBBBBBBB";

    private static final String DEFAULT_PEDIATRE = "AAAAAAAAAA";
    private static final String UPDATED_PEDIATRE = "BBBBBBBBBB";

    private static final String DEFAULT_RISQUE_ORL = "AAAAAAAAAA";
    private static final String UPDATED_RISQUE_ORL = "BBBBBBBBBB";

    private static final String DEFAULT_RESUME_GROSSESSE = "AAAAAAAAAA";
    private static final String UPDATED_RESUME_GROSSESSE = "BBBBBBBBBB";

    private static final String DEFAULT_CONDUITE_ACCOUCHE = "AAAAAAAAAA";
    private static final String UPDATED_CONDUITE_ACCOUCHE = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabGynecologieRepository tabGynecologieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabGynecologieMockMvc;

    private TabGynecologie tabGynecologie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabGynecologie createEntity(EntityManager em) {
        TabGynecologie tabGynecologie = new TabGynecologie()
            .dateConsult(DEFAULT_DATE_CONSULT)
            .ddr(DEFAULT_DDR)
            .termeUs(DEFAULT_TERME_US)
            .termCorrige(DEFAULT_TERM_CORRIGE)
            .termDdr(DEFAULT_TERM_DDR)
            .cycle(DEFAULT_CYCLE)
            .dateOvulation(DEFAULT_DATE_OVULATION)
            .ageGestationel(DEFAULT_AGE_GESTATIONEL)
            .dateFin(DEFAULT_DATE_FIN)
            .testPeri(DEFAULT_TEST_PERI)
            .ecouvillon(DEFAULT_ECOUVILLON)
            .perine(DEFAULT_PERINE)
            .bassin(DEFAULT_BASSIN)
            .ogtt(DEFAULT_OGTT)
            .imc(DEFAULT_IMC)
            .poidsMereInitial(DEFAULT_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(DEFAULT_POIDS_MERE_THEORI_BEBE)
            .tailleMere(DEFAULT_TAILLE_MERE)
            .tailleTheoriBebe(DEFAULT_TAILLE_THEORI_BEBE)
            .gSgMari(DEFAULT_G_SG_MARI)
            .laboTri21(DEFAULT_LABO_TRI_21)
            .caryotype(DEFAULT_CARYOTYPE)
            .suiviPar(DEFAULT_SUIVI_PAR)
            .pediatre(DEFAULT_PEDIATRE)
            .risqueOrl(DEFAULT_RISQUE_ORL)
            .resumeGrossesse(DEFAULT_RESUME_GROSSESSE)
            .conduiteAccouche(DEFAULT_CONDUITE_ACCOUCHE)
            .rapport(DEFAULT_RAPPORT);
        return tabGynecologie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabGynecologie createUpdatedEntity(EntityManager em) {
        TabGynecologie tabGynecologie = new TabGynecologie()
            .dateConsult(UPDATED_DATE_CONSULT)
            .ddr(UPDATED_DDR)
            .termeUs(UPDATED_TERME_US)
            .termCorrige(UPDATED_TERM_CORRIGE)
            .termDdr(UPDATED_TERM_DDR)
            .cycle(UPDATED_CYCLE)
            .dateOvulation(UPDATED_DATE_OVULATION)
            .ageGestationel(UPDATED_AGE_GESTATIONEL)
            .dateFin(UPDATED_DATE_FIN)
            .testPeri(UPDATED_TEST_PERI)
            .ecouvillon(UPDATED_ECOUVILLON)
            .perine(UPDATED_PERINE)
            .bassin(UPDATED_BASSIN)
            .ogtt(UPDATED_OGTT)
            .imc(UPDATED_IMC)
            .poidsMereInitial(UPDATED_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(UPDATED_POIDS_MERE_THEORI_BEBE)
            .tailleMere(UPDATED_TAILLE_MERE)
            .tailleTheoriBebe(UPDATED_TAILLE_THEORI_BEBE)
            .gSgMari(UPDATED_G_SG_MARI)
            .laboTri21(UPDATED_LABO_TRI_21)
            .caryotype(UPDATED_CARYOTYPE)
            .suiviPar(UPDATED_SUIVI_PAR)
            .pediatre(UPDATED_PEDIATRE)
            .risqueOrl(UPDATED_RISQUE_ORL)
            .resumeGrossesse(UPDATED_RESUME_GROSSESSE)
            .conduiteAccouche(UPDATED_CONDUITE_ACCOUCHE)
            .rapport(UPDATED_RAPPORT);
        return tabGynecologie;
    }

    @BeforeEach
    public void initTest() {
        tabGynecologie = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabGynecologie() throws Exception {
        int databaseSizeBeforeCreate = tabGynecologieRepository.findAll().size();
        // Create the TabGynecologie
        restTabGynecologieMockMvc.perform(post("/api/tab-gynecologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGynecologie)))
            .andExpect(status().isCreated());

        // Validate the TabGynecologie in the database
        List<TabGynecologie> tabGynecologieList = tabGynecologieRepository.findAll();
        assertThat(tabGynecologieList).hasSize(databaseSizeBeforeCreate + 1);
        TabGynecologie testTabGynecologie = tabGynecologieList.get(tabGynecologieList.size() - 1);
        assertThat(testTabGynecologie.getDateConsult()).isEqualTo(DEFAULT_DATE_CONSULT);
        assertThat(testTabGynecologie.getDdr()).isEqualTo(DEFAULT_DDR);
        assertThat(testTabGynecologie.getTermeUs()).isEqualTo(DEFAULT_TERME_US);
        assertThat(testTabGynecologie.getTermCorrige()).isEqualTo(DEFAULT_TERM_CORRIGE);
        assertThat(testTabGynecologie.getTermDdr()).isEqualTo(DEFAULT_TERM_DDR);
        assertThat(testTabGynecologie.getCycle()).isEqualTo(DEFAULT_CYCLE);
        assertThat(testTabGynecologie.getDateOvulation()).isEqualTo(DEFAULT_DATE_OVULATION);
        assertThat(testTabGynecologie.getAgeGestationel()).isEqualTo(DEFAULT_AGE_GESTATIONEL);
        assertThat(testTabGynecologie.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testTabGynecologie.getTestPeri()).isEqualTo(DEFAULT_TEST_PERI);
        assertThat(testTabGynecologie.getEcouvillon()).isEqualTo(DEFAULT_ECOUVILLON);
        assertThat(testTabGynecologie.getPerine()).isEqualTo(DEFAULT_PERINE);
        assertThat(testTabGynecologie.getBassin()).isEqualTo(DEFAULT_BASSIN);
        assertThat(testTabGynecologie.getOgtt()).isEqualTo(DEFAULT_OGTT);
        assertThat(testTabGynecologie.getImc()).isEqualTo(DEFAULT_IMC);
        assertThat(testTabGynecologie.getPoidsMereInitial()).isEqualTo(DEFAULT_POIDS_MERE_INITIAL);
        assertThat(testTabGynecologie.getPoidsMereTheoriBebe()).isEqualTo(DEFAULT_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabGynecologie.getTailleMere()).isEqualTo(DEFAULT_TAILLE_MERE);
        assertThat(testTabGynecologie.getTailleTheoriBebe()).isEqualTo(DEFAULT_TAILLE_THEORI_BEBE);
        assertThat(testTabGynecologie.getgSgMari()).isEqualTo(DEFAULT_G_SG_MARI);
        assertThat(testTabGynecologie.getLaboTri21()).isEqualTo(DEFAULT_LABO_TRI_21);
        assertThat(testTabGynecologie.getCaryotype()).isEqualTo(DEFAULT_CARYOTYPE);
        assertThat(testTabGynecologie.getSuiviPar()).isEqualTo(DEFAULT_SUIVI_PAR);
        assertThat(testTabGynecologie.getPediatre()).isEqualTo(DEFAULT_PEDIATRE);
        assertThat(testTabGynecologie.getRisqueOrl()).isEqualTo(DEFAULT_RISQUE_ORL);
        assertThat(testTabGynecologie.getResumeGrossesse()).isEqualTo(DEFAULT_RESUME_GROSSESSE);
        assertThat(testTabGynecologie.getConduiteAccouche()).isEqualTo(DEFAULT_CONDUITE_ACCOUCHE);
        assertThat(testTabGynecologie.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabGynecologieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabGynecologieRepository.findAll().size();

        // Create the TabGynecologie with an existing ID
        tabGynecologie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabGynecologieMockMvc.perform(post("/api/tab-gynecologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGynecologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabGynecologie in the database
        List<TabGynecologie> tabGynecologieList = tabGynecologieRepository.findAll();
        assertThat(tabGynecologieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabGynecologies() throws Exception {
        // Initialize the database
        tabGynecologieRepository.saveAndFlush(tabGynecologie);

        // Get all the tabGynecologieList
        restTabGynecologieMockMvc.perform(get("/api/tab-gynecologies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabGynecologie.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateConsult").value(hasItem(sameInstant(DEFAULT_DATE_CONSULT))))
            .andExpect(jsonPath("$.[*].ddr").value(hasItem(DEFAULT_DDR)))
            .andExpect(jsonPath("$.[*].termeUs").value(hasItem(DEFAULT_TERME_US)))
            .andExpect(jsonPath("$.[*].termCorrige").value(hasItem(DEFAULT_TERM_CORRIGE)))
            .andExpect(jsonPath("$.[*].termDdr").value(hasItem(DEFAULT_TERM_DDR)))
            .andExpect(jsonPath("$.[*].cycle").value(hasItem(DEFAULT_CYCLE)))
            .andExpect(jsonPath("$.[*].dateOvulation").value(hasItem(sameInstant(DEFAULT_DATE_OVULATION))))
            .andExpect(jsonPath("$.[*].ageGestationel").value(hasItem(DEFAULT_AGE_GESTATIONEL)))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(sameInstant(DEFAULT_DATE_FIN))))
            .andExpect(jsonPath("$.[*].testPeri").value(hasItem(DEFAULT_TEST_PERI)))
            .andExpect(jsonPath("$.[*].ecouvillon").value(hasItem(DEFAULT_ECOUVILLON)))
            .andExpect(jsonPath("$.[*].perine").value(hasItem(DEFAULT_PERINE)))
            .andExpect(jsonPath("$.[*].bassin").value(hasItem(DEFAULT_BASSIN)))
            .andExpect(jsonPath("$.[*].ogtt").value(hasItem(DEFAULT_OGTT)))
            .andExpect(jsonPath("$.[*].imc").value(hasItem(DEFAULT_IMC)))
            .andExpect(jsonPath("$.[*].poidsMereInitial").value(hasItem(DEFAULT_POIDS_MERE_INITIAL.doubleValue())))
            .andExpect(jsonPath("$.[*].poidsMereTheoriBebe").value(hasItem(DEFAULT_POIDS_MERE_THEORI_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].tailleMere").value(hasItem(DEFAULT_TAILLE_MERE.doubleValue())))
            .andExpect(jsonPath("$.[*].tailleTheoriBebe").value(hasItem(DEFAULT_TAILLE_THEORI_BEBE.doubleValue())))
            .andExpect(jsonPath("$.[*].gSgMari").value(hasItem(DEFAULT_G_SG_MARI)))
            .andExpect(jsonPath("$.[*].laboTri21").value(hasItem(DEFAULT_LABO_TRI_21)))
            .andExpect(jsonPath("$.[*].caryotype").value(hasItem(DEFAULT_CARYOTYPE)))
            .andExpect(jsonPath("$.[*].suiviPar").value(hasItem(DEFAULT_SUIVI_PAR)))
            .andExpect(jsonPath("$.[*].pediatre").value(hasItem(DEFAULT_PEDIATRE)))
            .andExpect(jsonPath("$.[*].risqueOrl").value(hasItem(DEFAULT_RISQUE_ORL)))
            .andExpect(jsonPath("$.[*].resumeGrossesse").value(hasItem(DEFAULT_RESUME_GROSSESSE)))
            .andExpect(jsonPath("$.[*].conduiteAccouche").value(hasItem(DEFAULT_CONDUITE_ACCOUCHE)))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @Test
    @Transactional
    public void getTabGynecologie() throws Exception {
        // Initialize the database
        tabGynecologieRepository.saveAndFlush(tabGynecologie);

        // Get the tabGynecologie
        restTabGynecologieMockMvc.perform(get("/api/tab-gynecologies/{id}", tabGynecologie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabGynecologie.getId().intValue()))
            .andExpect(jsonPath("$.dateConsult").value(sameInstant(DEFAULT_DATE_CONSULT)))
            .andExpect(jsonPath("$.ddr").value(DEFAULT_DDR))
            .andExpect(jsonPath("$.termeUs").value(DEFAULT_TERME_US))
            .andExpect(jsonPath("$.termCorrige").value(DEFAULT_TERM_CORRIGE))
            .andExpect(jsonPath("$.termDdr").value(DEFAULT_TERM_DDR))
            .andExpect(jsonPath("$.cycle").value(DEFAULT_CYCLE))
            .andExpect(jsonPath("$.dateOvulation").value(sameInstant(DEFAULT_DATE_OVULATION)))
            .andExpect(jsonPath("$.ageGestationel").value(DEFAULT_AGE_GESTATIONEL))
            .andExpect(jsonPath("$.dateFin").value(sameInstant(DEFAULT_DATE_FIN)))
            .andExpect(jsonPath("$.testPeri").value(DEFAULT_TEST_PERI))
            .andExpect(jsonPath("$.ecouvillon").value(DEFAULT_ECOUVILLON))
            .andExpect(jsonPath("$.perine").value(DEFAULT_PERINE))
            .andExpect(jsonPath("$.bassin").value(DEFAULT_BASSIN))
            .andExpect(jsonPath("$.ogtt").value(DEFAULT_OGTT))
            .andExpect(jsonPath("$.imc").value(DEFAULT_IMC))
            .andExpect(jsonPath("$.poidsMereInitial").value(DEFAULT_POIDS_MERE_INITIAL.doubleValue()))
            .andExpect(jsonPath("$.poidsMereTheoriBebe").value(DEFAULT_POIDS_MERE_THEORI_BEBE.doubleValue()))
            .andExpect(jsonPath("$.tailleMere").value(DEFAULT_TAILLE_MERE.doubleValue()))
            .andExpect(jsonPath("$.tailleTheoriBebe").value(DEFAULT_TAILLE_THEORI_BEBE.doubleValue()))
            .andExpect(jsonPath("$.gSgMari").value(DEFAULT_G_SG_MARI))
            .andExpect(jsonPath("$.laboTri21").value(DEFAULT_LABO_TRI_21))
            .andExpect(jsonPath("$.caryotype").value(DEFAULT_CARYOTYPE))
            .andExpect(jsonPath("$.suiviPar").value(DEFAULT_SUIVI_PAR))
            .andExpect(jsonPath("$.pediatre").value(DEFAULT_PEDIATRE))
            .andExpect(jsonPath("$.risqueOrl").value(DEFAULT_RISQUE_ORL))
            .andExpect(jsonPath("$.resumeGrossesse").value(DEFAULT_RESUME_GROSSESSE))
            .andExpect(jsonPath("$.conduiteAccouche").value(DEFAULT_CONDUITE_ACCOUCHE))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabGynecologie() throws Exception {
        // Get the tabGynecologie
        restTabGynecologieMockMvc.perform(get("/api/tab-gynecologies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabGynecologie() throws Exception {
        // Initialize the database
        tabGynecologieRepository.saveAndFlush(tabGynecologie);

        int databaseSizeBeforeUpdate = tabGynecologieRepository.findAll().size();

        // Update the tabGynecologie
        TabGynecologie updatedTabGynecologie = tabGynecologieRepository.findById(tabGynecologie.getId()).get();
        // Disconnect from session so that the updates on updatedTabGynecologie are not directly saved in db
        em.detach(updatedTabGynecologie);
        updatedTabGynecologie
            .dateConsult(UPDATED_DATE_CONSULT)
            .ddr(UPDATED_DDR)
            .termeUs(UPDATED_TERME_US)
            .termCorrige(UPDATED_TERM_CORRIGE)
            .termDdr(UPDATED_TERM_DDR)
            .cycle(UPDATED_CYCLE)
            .dateOvulation(UPDATED_DATE_OVULATION)
            .ageGestationel(UPDATED_AGE_GESTATIONEL)
            .dateFin(UPDATED_DATE_FIN)
            .testPeri(UPDATED_TEST_PERI)
            .ecouvillon(UPDATED_ECOUVILLON)
            .perine(UPDATED_PERINE)
            .bassin(UPDATED_BASSIN)
            .ogtt(UPDATED_OGTT)
            .imc(UPDATED_IMC)
            .poidsMereInitial(UPDATED_POIDS_MERE_INITIAL)
            .poidsMereTheoriBebe(UPDATED_POIDS_MERE_THEORI_BEBE)
            .tailleMere(UPDATED_TAILLE_MERE)
            .tailleTheoriBebe(UPDATED_TAILLE_THEORI_BEBE)
            .gSgMari(UPDATED_G_SG_MARI)
            .laboTri21(UPDATED_LABO_TRI_21)
            .caryotype(UPDATED_CARYOTYPE)
            .suiviPar(UPDATED_SUIVI_PAR)
            .pediatre(UPDATED_PEDIATRE)
            .risqueOrl(UPDATED_RISQUE_ORL)
            .resumeGrossesse(UPDATED_RESUME_GROSSESSE)
            .conduiteAccouche(UPDATED_CONDUITE_ACCOUCHE)
            .rapport(UPDATED_RAPPORT);

        restTabGynecologieMockMvc.perform(put("/api/tab-gynecologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabGynecologie)))
            .andExpect(status().isOk());

        // Validate the TabGynecologie in the database
        List<TabGynecologie> tabGynecologieList = tabGynecologieRepository.findAll();
        assertThat(tabGynecologieList).hasSize(databaseSizeBeforeUpdate);
        TabGynecologie testTabGynecologie = tabGynecologieList.get(tabGynecologieList.size() - 1);
        assertThat(testTabGynecologie.getDateConsult()).isEqualTo(UPDATED_DATE_CONSULT);
        assertThat(testTabGynecologie.getDdr()).isEqualTo(UPDATED_DDR);
        assertThat(testTabGynecologie.getTermeUs()).isEqualTo(UPDATED_TERME_US);
        assertThat(testTabGynecologie.getTermCorrige()).isEqualTo(UPDATED_TERM_CORRIGE);
        assertThat(testTabGynecologie.getTermDdr()).isEqualTo(UPDATED_TERM_DDR);
        assertThat(testTabGynecologie.getCycle()).isEqualTo(UPDATED_CYCLE);
        assertThat(testTabGynecologie.getDateOvulation()).isEqualTo(UPDATED_DATE_OVULATION);
        assertThat(testTabGynecologie.getAgeGestationel()).isEqualTo(UPDATED_AGE_GESTATIONEL);
        assertThat(testTabGynecologie.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testTabGynecologie.getTestPeri()).isEqualTo(UPDATED_TEST_PERI);
        assertThat(testTabGynecologie.getEcouvillon()).isEqualTo(UPDATED_ECOUVILLON);
        assertThat(testTabGynecologie.getPerine()).isEqualTo(UPDATED_PERINE);
        assertThat(testTabGynecologie.getBassin()).isEqualTo(UPDATED_BASSIN);
        assertThat(testTabGynecologie.getOgtt()).isEqualTo(UPDATED_OGTT);
        assertThat(testTabGynecologie.getImc()).isEqualTo(UPDATED_IMC);
        assertThat(testTabGynecologie.getPoidsMereInitial()).isEqualTo(UPDATED_POIDS_MERE_INITIAL);
        assertThat(testTabGynecologie.getPoidsMereTheoriBebe()).isEqualTo(UPDATED_POIDS_MERE_THEORI_BEBE);
        assertThat(testTabGynecologie.getTailleMere()).isEqualTo(UPDATED_TAILLE_MERE);
        assertThat(testTabGynecologie.getTailleTheoriBebe()).isEqualTo(UPDATED_TAILLE_THEORI_BEBE);
        assertThat(testTabGynecologie.getgSgMari()).isEqualTo(UPDATED_G_SG_MARI);
        assertThat(testTabGynecologie.getLaboTri21()).isEqualTo(UPDATED_LABO_TRI_21);
        assertThat(testTabGynecologie.getCaryotype()).isEqualTo(UPDATED_CARYOTYPE);
        assertThat(testTabGynecologie.getSuiviPar()).isEqualTo(UPDATED_SUIVI_PAR);
        assertThat(testTabGynecologie.getPediatre()).isEqualTo(UPDATED_PEDIATRE);
        assertThat(testTabGynecologie.getRisqueOrl()).isEqualTo(UPDATED_RISQUE_ORL);
        assertThat(testTabGynecologie.getResumeGrossesse()).isEqualTo(UPDATED_RESUME_GROSSESSE);
        assertThat(testTabGynecologie.getConduiteAccouche()).isEqualTo(UPDATED_CONDUITE_ACCOUCHE);
        assertThat(testTabGynecologie.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabGynecologie() throws Exception {
        int databaseSizeBeforeUpdate = tabGynecologieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabGynecologieMockMvc.perform(put("/api/tab-gynecologies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabGynecologie)))
            .andExpect(status().isBadRequest());

        // Validate the TabGynecologie in the database
        List<TabGynecologie> tabGynecologieList = tabGynecologieRepository.findAll();
        assertThat(tabGynecologieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabGynecologie() throws Exception {
        // Initialize the database
        tabGynecologieRepository.saveAndFlush(tabGynecologie);

        int databaseSizeBeforeDelete = tabGynecologieRepository.findAll().size();

        // Delete the tabGynecologie
        restTabGynecologieMockMvc.perform(delete("/api/tab-gynecologies/{id}", tabGynecologie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabGynecologie> tabGynecologieList = tabGynecologieRepository.findAll();
        assertThat(tabGynecologieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
