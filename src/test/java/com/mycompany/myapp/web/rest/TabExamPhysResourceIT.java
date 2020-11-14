package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabExamPhys;
import com.mycompany.myapp.repository.TabExamPhysRepository;

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
 * Integration tests for the {@link TabExamPhysResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabExamPhysResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_EXAM_PHYS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_EXAM_PHYS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NOM_APPAREIL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_APPAREIL = "BBBBBBBBBB";

    private static final String DEFAULT_TETE = "AAAAAAAAAA";
    private static final String UPDATED_TETE = "BBBBBBBBBB";

    private static final String DEFAULT_COU = "AAAAAAAAAA";
    private static final String UPDATED_COU = "BBBBBBBBBB";

    private static final String DEFAULT_BOUCHE = "AAAAAAAAAA";
    private static final String UPDATED_BOUCHE = "BBBBBBBBBB";

    private static final String DEFAULT_THORAX = "AAAAAAAAAA";
    private static final String UPDATED_THORAX = "BBBBBBBBBB";

    private static final String DEFAULT_AUSCULATION_CARD = "AAAAAAAAAA";
    private static final String UPDATED_AUSCULATION_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_AUSCULATION_PULMO = "AAAAAAAAAA";
    private static final String UPDATED_AUSCULATION_PULMO = "BBBBBBBBBB";

    private static final String DEFAULT_SOUFFLE = "AAAAAAAAAA";
    private static final String UPDATED_SOUFFLE = "BBBBBBBBBB";

    private static final String DEFAULT_RATE = "AAAAAAAAAA";
    private static final String UPDATED_RATE = "BBBBBBBBBB";

    private static final String DEFAULT_BONCHOSPAME = "AAAAAAAAAA";
    private static final String UPDATED_BONCHOSPAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERCUSSION_THORAX = "AAAAAAAAAA";
    private static final String UPDATED_PERCUSSION_THORAX = "BBBBBBBBBB";

    private static final String DEFAULT_ABDOMEN = "AAAAAAAAAA";
    private static final String UPDATED_ABDOMEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_POULS_FEMORAL_G = false;
    private static final Boolean UPDATED_POULS_FEMORAL_G = true;

    private static final Boolean DEFAULT_POULS_FEMORAL_D = false;
    private static final Boolean UPDATED_POULS_FEMORAL_D = true;

    private static final Boolean DEFAULT_POULS_POPLITE_G = false;
    private static final Boolean UPDATED_POULS_POPLITE_G = true;

    private static final Boolean DEFAULT_POULS_POPLITE_D = false;
    private static final Boolean UPDATED_POULS_POPLITE_D = true;

    private static final Boolean DEFAULT_POULS_PEDIEUX_G = false;
    private static final Boolean UPDATED_POULS_PEDIEUX_G = true;

    private static final Boolean DEFAULT_POULS_PEDIEUX_D = false;
    private static final Boolean UPDATED_POULS_PEDIEUX_D = true;

    private static final Boolean DEFAULT_POULSTIBIAL_POST_G = false;
    private static final Boolean UPDATED_POULSTIBIAL_POST_G = true;

    private static final Boolean DEFAULT_POULSTIBIAL_POST_D = false;
    private static final Boolean UPDATED_POULSTIBIAL_POST_D = true;

    private static final Boolean DEFAULT_SOUFFLE_ABDO = false;
    private static final Boolean UPDATED_SOUFFLE_ABDO = true;

    private static final Boolean DEFAULT_SOUFFLE_FEMORAL_G = false;
    private static final Boolean UPDATED_SOUFFLE_FEMORAL_G = true;

    private static final Boolean DEFAULT_SOUFFLE_FEMORAL_D = false;
    private static final Boolean UPDATED_SOUFFLE_FEMORAL_D = true;

    private static final String DEFAULT_SPECT_PEAU = "AAAAAAAAAA";
    private static final String UPDATED_SPECT_PEAU = "BBBBBBBBBB";

    private static final String DEFAULT_EXAM_NEURO = "AAAAAAAAAA";
    private static final String UPDATED_EXAM_NEURO = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT = "BBBBBBBBBB";

    @Autowired
    private TabExamPhysRepository tabExamPhysRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabExamPhysMockMvc;

    private TabExamPhys tabExamPhys;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamPhys createEntity(EntityManager em) {
        TabExamPhys tabExamPhys = new TabExamPhys()
            .dateExamPhys(DEFAULT_DATE_EXAM_PHYS)
            .nomAppareil(DEFAULT_NOM_APPAREIL)
            .tete(DEFAULT_TETE)
            .cou(DEFAULT_COU)
            .bouche(DEFAULT_BOUCHE)
            .thorax(DEFAULT_THORAX)
            .ausculationCard(DEFAULT_AUSCULATION_CARD)
            .ausculationPulmo(DEFAULT_AUSCULATION_PULMO)
            .souffle(DEFAULT_SOUFFLE)
            .rate(DEFAULT_RATE)
            .bonchospame(DEFAULT_BONCHOSPAME)
            .percussionThorax(DEFAULT_PERCUSSION_THORAX)
            .abdomen(DEFAULT_ABDOMEN)
            .poulsFemoralG(DEFAULT_POULS_FEMORAL_G)
            .poulsFemoralD(DEFAULT_POULS_FEMORAL_D)
            .poulsPopliteG(DEFAULT_POULS_POPLITE_G)
            .poulsPopliteD(DEFAULT_POULS_POPLITE_D)
            .poulsPedieuxG(DEFAULT_POULS_PEDIEUX_G)
            .poulsPedieuxD(DEFAULT_POULS_PEDIEUX_D)
            .poulstibialPostG(DEFAULT_POULSTIBIAL_POST_G)
            .poulstibialPostD(DEFAULT_POULSTIBIAL_POST_D)
            .souffleAbdo(DEFAULT_SOUFFLE_ABDO)
            .souffleFemoralG(DEFAULT_SOUFFLE_FEMORAL_G)
            .souffleFemoralD(DEFAULT_SOUFFLE_FEMORAL_D)
            .spectPeau(DEFAULT_SPECT_PEAU)
            .examNeuro(DEFAULT_EXAM_NEURO)
            .rapport(DEFAULT_RAPPORT);
        return tabExamPhys;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamPhys createUpdatedEntity(EntityManager em) {
        TabExamPhys tabExamPhys = new TabExamPhys()
            .dateExamPhys(UPDATED_DATE_EXAM_PHYS)
            .nomAppareil(UPDATED_NOM_APPAREIL)
            .tete(UPDATED_TETE)
            .cou(UPDATED_COU)
            .bouche(UPDATED_BOUCHE)
            .thorax(UPDATED_THORAX)
            .ausculationCard(UPDATED_AUSCULATION_CARD)
            .ausculationPulmo(UPDATED_AUSCULATION_PULMO)
            .souffle(UPDATED_SOUFFLE)
            .rate(UPDATED_RATE)
            .bonchospame(UPDATED_BONCHOSPAME)
            .percussionThorax(UPDATED_PERCUSSION_THORAX)
            .abdomen(UPDATED_ABDOMEN)
            .poulsFemoralG(UPDATED_POULS_FEMORAL_G)
            .poulsFemoralD(UPDATED_POULS_FEMORAL_D)
            .poulsPopliteG(UPDATED_POULS_POPLITE_G)
            .poulsPopliteD(UPDATED_POULS_POPLITE_D)
            .poulsPedieuxG(UPDATED_POULS_PEDIEUX_G)
            .poulsPedieuxD(UPDATED_POULS_PEDIEUX_D)
            .poulstibialPostG(UPDATED_POULSTIBIAL_POST_G)
            .poulstibialPostD(UPDATED_POULSTIBIAL_POST_D)
            .souffleAbdo(UPDATED_SOUFFLE_ABDO)
            .souffleFemoralG(UPDATED_SOUFFLE_FEMORAL_G)
            .souffleFemoralD(UPDATED_SOUFFLE_FEMORAL_D)
            .spectPeau(UPDATED_SPECT_PEAU)
            .examNeuro(UPDATED_EXAM_NEURO)
            .rapport(UPDATED_RAPPORT);
        return tabExamPhys;
    }

    @BeforeEach
    public void initTest() {
        tabExamPhys = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabExamPhys() throws Exception {
        int databaseSizeBeforeCreate = tabExamPhysRepository.findAll().size();
        // Create the TabExamPhys
        restTabExamPhysMockMvc.perform(post("/api/tab-exam-phys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamPhys)))
            .andExpect(status().isCreated());

        // Validate the TabExamPhys in the database
        List<TabExamPhys> tabExamPhysList = tabExamPhysRepository.findAll();
        assertThat(tabExamPhysList).hasSize(databaseSizeBeforeCreate + 1);
        TabExamPhys testTabExamPhys = tabExamPhysList.get(tabExamPhysList.size() - 1);
        assertThat(testTabExamPhys.getDateExamPhys()).isEqualTo(DEFAULT_DATE_EXAM_PHYS);
        assertThat(testTabExamPhys.getNomAppareil()).isEqualTo(DEFAULT_NOM_APPAREIL);
        assertThat(testTabExamPhys.getTete()).isEqualTo(DEFAULT_TETE);
        assertThat(testTabExamPhys.getCou()).isEqualTo(DEFAULT_COU);
        assertThat(testTabExamPhys.getBouche()).isEqualTo(DEFAULT_BOUCHE);
        assertThat(testTabExamPhys.getThorax()).isEqualTo(DEFAULT_THORAX);
        assertThat(testTabExamPhys.getAusculationCard()).isEqualTo(DEFAULT_AUSCULATION_CARD);
        assertThat(testTabExamPhys.getAusculationPulmo()).isEqualTo(DEFAULT_AUSCULATION_PULMO);
        assertThat(testTabExamPhys.getSouffle()).isEqualTo(DEFAULT_SOUFFLE);
        assertThat(testTabExamPhys.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testTabExamPhys.getBonchospame()).isEqualTo(DEFAULT_BONCHOSPAME);
        assertThat(testTabExamPhys.getPercussionThorax()).isEqualTo(DEFAULT_PERCUSSION_THORAX);
        assertThat(testTabExamPhys.getAbdomen()).isEqualTo(DEFAULT_ABDOMEN);
        assertThat(testTabExamPhys.isPoulsFemoralG()).isEqualTo(DEFAULT_POULS_FEMORAL_G);
        assertThat(testTabExamPhys.isPoulsFemoralD()).isEqualTo(DEFAULT_POULS_FEMORAL_D);
        assertThat(testTabExamPhys.isPoulsPopliteG()).isEqualTo(DEFAULT_POULS_POPLITE_G);
        assertThat(testTabExamPhys.isPoulsPopliteD()).isEqualTo(DEFAULT_POULS_POPLITE_D);
        assertThat(testTabExamPhys.isPoulsPedieuxG()).isEqualTo(DEFAULT_POULS_PEDIEUX_G);
        assertThat(testTabExamPhys.isPoulsPedieuxD()).isEqualTo(DEFAULT_POULS_PEDIEUX_D);
        assertThat(testTabExamPhys.isPoulstibialPostG()).isEqualTo(DEFAULT_POULSTIBIAL_POST_G);
        assertThat(testTabExamPhys.isPoulstibialPostD()).isEqualTo(DEFAULT_POULSTIBIAL_POST_D);
        assertThat(testTabExamPhys.isSouffleAbdo()).isEqualTo(DEFAULT_SOUFFLE_ABDO);
        assertThat(testTabExamPhys.isSouffleFemoralG()).isEqualTo(DEFAULT_SOUFFLE_FEMORAL_G);
        assertThat(testTabExamPhys.isSouffleFemoralD()).isEqualTo(DEFAULT_SOUFFLE_FEMORAL_D);
        assertThat(testTabExamPhys.getSpectPeau()).isEqualTo(DEFAULT_SPECT_PEAU);
        assertThat(testTabExamPhys.getExamNeuro()).isEqualTo(DEFAULT_EXAM_NEURO);
        assertThat(testTabExamPhys.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabExamPhysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabExamPhysRepository.findAll().size();

        // Create the TabExamPhys with an existing ID
        tabExamPhys.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabExamPhysMockMvc.perform(post("/api/tab-exam-phys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamPhys)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamPhys in the database
        List<TabExamPhys> tabExamPhysList = tabExamPhysRepository.findAll();
        assertThat(tabExamPhysList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabExamPhys() throws Exception {
        // Initialize the database
        tabExamPhysRepository.saveAndFlush(tabExamPhys);

        // Get all the tabExamPhysList
        restTabExamPhysMockMvc.perform(get("/api/tab-exam-phys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabExamPhys.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateExamPhys").value(hasItem(sameInstant(DEFAULT_DATE_EXAM_PHYS))))
            .andExpect(jsonPath("$.[*].nomAppareil").value(hasItem(DEFAULT_NOM_APPAREIL)))
            .andExpect(jsonPath("$.[*].tete").value(hasItem(DEFAULT_TETE)))
            .andExpect(jsonPath("$.[*].cou").value(hasItem(DEFAULT_COU)))
            .andExpect(jsonPath("$.[*].bouche").value(hasItem(DEFAULT_BOUCHE)))
            .andExpect(jsonPath("$.[*].thorax").value(hasItem(DEFAULT_THORAX)))
            .andExpect(jsonPath("$.[*].ausculationCard").value(hasItem(DEFAULT_AUSCULATION_CARD)))
            .andExpect(jsonPath("$.[*].ausculationPulmo").value(hasItem(DEFAULT_AUSCULATION_PULMO)))
            .andExpect(jsonPath("$.[*].souffle").value(hasItem(DEFAULT_SOUFFLE)))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE)))
            .andExpect(jsonPath("$.[*].bonchospame").value(hasItem(DEFAULT_BONCHOSPAME)))
            .andExpect(jsonPath("$.[*].percussionThorax").value(hasItem(DEFAULT_PERCUSSION_THORAX)))
            .andExpect(jsonPath("$.[*].abdomen").value(hasItem(DEFAULT_ABDOMEN)))
            .andExpect(jsonPath("$.[*].poulsFemoralG").value(hasItem(DEFAULT_POULS_FEMORAL_G.booleanValue())))
            .andExpect(jsonPath("$.[*].poulsFemoralD").value(hasItem(DEFAULT_POULS_FEMORAL_D.booleanValue())))
            .andExpect(jsonPath("$.[*].poulsPopliteG").value(hasItem(DEFAULT_POULS_POPLITE_G.booleanValue())))
            .andExpect(jsonPath("$.[*].poulsPopliteD").value(hasItem(DEFAULT_POULS_POPLITE_D.booleanValue())))
            .andExpect(jsonPath("$.[*].poulsPedieuxG").value(hasItem(DEFAULT_POULS_PEDIEUX_G.booleanValue())))
            .andExpect(jsonPath("$.[*].poulsPedieuxD").value(hasItem(DEFAULT_POULS_PEDIEUX_D.booleanValue())))
            .andExpect(jsonPath("$.[*].poulstibialPostG").value(hasItem(DEFAULT_POULSTIBIAL_POST_G.booleanValue())))
            .andExpect(jsonPath("$.[*].poulstibialPostD").value(hasItem(DEFAULT_POULSTIBIAL_POST_D.booleanValue())))
            .andExpect(jsonPath("$.[*].souffleAbdo").value(hasItem(DEFAULT_SOUFFLE_ABDO.booleanValue())))
            .andExpect(jsonPath("$.[*].souffleFemoralG").value(hasItem(DEFAULT_SOUFFLE_FEMORAL_G.booleanValue())))
            .andExpect(jsonPath("$.[*].souffleFemoralD").value(hasItem(DEFAULT_SOUFFLE_FEMORAL_D.booleanValue())))
            .andExpect(jsonPath("$.[*].spectPeau").value(hasItem(DEFAULT_SPECT_PEAU)))
            .andExpect(jsonPath("$.[*].examNeuro").value(hasItem(DEFAULT_EXAM_NEURO)))
            .andExpect(jsonPath("$.[*].rapport").value(hasItem(DEFAULT_RAPPORT)));
    }
    
    @Test
    @Transactional
    public void getTabExamPhys() throws Exception {
        // Initialize the database
        tabExamPhysRepository.saveAndFlush(tabExamPhys);

        // Get the tabExamPhys
        restTabExamPhysMockMvc.perform(get("/api/tab-exam-phys/{id}", tabExamPhys.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabExamPhys.getId().intValue()))
            .andExpect(jsonPath("$.dateExamPhys").value(sameInstant(DEFAULT_DATE_EXAM_PHYS)))
            .andExpect(jsonPath("$.nomAppareil").value(DEFAULT_NOM_APPAREIL))
            .andExpect(jsonPath("$.tete").value(DEFAULT_TETE))
            .andExpect(jsonPath("$.cou").value(DEFAULT_COU))
            .andExpect(jsonPath("$.bouche").value(DEFAULT_BOUCHE))
            .andExpect(jsonPath("$.thorax").value(DEFAULT_THORAX))
            .andExpect(jsonPath("$.ausculationCard").value(DEFAULT_AUSCULATION_CARD))
            .andExpect(jsonPath("$.ausculationPulmo").value(DEFAULT_AUSCULATION_PULMO))
            .andExpect(jsonPath("$.souffle").value(DEFAULT_SOUFFLE))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE))
            .andExpect(jsonPath("$.bonchospame").value(DEFAULT_BONCHOSPAME))
            .andExpect(jsonPath("$.percussionThorax").value(DEFAULT_PERCUSSION_THORAX))
            .andExpect(jsonPath("$.abdomen").value(DEFAULT_ABDOMEN))
            .andExpect(jsonPath("$.poulsFemoralG").value(DEFAULT_POULS_FEMORAL_G.booleanValue()))
            .andExpect(jsonPath("$.poulsFemoralD").value(DEFAULT_POULS_FEMORAL_D.booleanValue()))
            .andExpect(jsonPath("$.poulsPopliteG").value(DEFAULT_POULS_POPLITE_G.booleanValue()))
            .andExpect(jsonPath("$.poulsPopliteD").value(DEFAULT_POULS_POPLITE_D.booleanValue()))
            .andExpect(jsonPath("$.poulsPedieuxG").value(DEFAULT_POULS_PEDIEUX_G.booleanValue()))
            .andExpect(jsonPath("$.poulsPedieuxD").value(DEFAULT_POULS_PEDIEUX_D.booleanValue()))
            .andExpect(jsonPath("$.poulstibialPostG").value(DEFAULT_POULSTIBIAL_POST_G.booleanValue()))
            .andExpect(jsonPath("$.poulstibialPostD").value(DEFAULT_POULSTIBIAL_POST_D.booleanValue()))
            .andExpect(jsonPath("$.souffleAbdo").value(DEFAULT_SOUFFLE_ABDO.booleanValue()))
            .andExpect(jsonPath("$.souffleFemoralG").value(DEFAULT_SOUFFLE_FEMORAL_G.booleanValue()))
            .andExpect(jsonPath("$.souffleFemoralD").value(DEFAULT_SOUFFLE_FEMORAL_D.booleanValue()))
            .andExpect(jsonPath("$.spectPeau").value(DEFAULT_SPECT_PEAU))
            .andExpect(jsonPath("$.examNeuro").value(DEFAULT_EXAM_NEURO))
            .andExpect(jsonPath("$.rapport").value(DEFAULT_RAPPORT));
    }
    @Test
    @Transactional
    public void getNonExistingTabExamPhys() throws Exception {
        // Get the tabExamPhys
        restTabExamPhysMockMvc.perform(get("/api/tab-exam-phys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabExamPhys() throws Exception {
        // Initialize the database
        tabExamPhysRepository.saveAndFlush(tabExamPhys);

        int databaseSizeBeforeUpdate = tabExamPhysRepository.findAll().size();

        // Update the tabExamPhys
        TabExamPhys updatedTabExamPhys = tabExamPhysRepository.findById(tabExamPhys.getId()).get();
        // Disconnect from session so that the updates on updatedTabExamPhys are not directly saved in db
        em.detach(updatedTabExamPhys);
        updatedTabExamPhys
            .dateExamPhys(UPDATED_DATE_EXAM_PHYS)
            .nomAppareil(UPDATED_NOM_APPAREIL)
            .tete(UPDATED_TETE)
            .cou(UPDATED_COU)
            .bouche(UPDATED_BOUCHE)
            .thorax(UPDATED_THORAX)
            .ausculationCard(UPDATED_AUSCULATION_CARD)
            .ausculationPulmo(UPDATED_AUSCULATION_PULMO)
            .souffle(UPDATED_SOUFFLE)
            .rate(UPDATED_RATE)
            .bonchospame(UPDATED_BONCHOSPAME)
            .percussionThorax(UPDATED_PERCUSSION_THORAX)
            .abdomen(UPDATED_ABDOMEN)
            .poulsFemoralG(UPDATED_POULS_FEMORAL_G)
            .poulsFemoralD(UPDATED_POULS_FEMORAL_D)
            .poulsPopliteG(UPDATED_POULS_POPLITE_G)
            .poulsPopliteD(UPDATED_POULS_POPLITE_D)
            .poulsPedieuxG(UPDATED_POULS_PEDIEUX_G)
            .poulsPedieuxD(UPDATED_POULS_PEDIEUX_D)
            .poulstibialPostG(UPDATED_POULSTIBIAL_POST_G)
            .poulstibialPostD(UPDATED_POULSTIBIAL_POST_D)
            .souffleAbdo(UPDATED_SOUFFLE_ABDO)
            .souffleFemoralG(UPDATED_SOUFFLE_FEMORAL_G)
            .souffleFemoralD(UPDATED_SOUFFLE_FEMORAL_D)
            .spectPeau(UPDATED_SPECT_PEAU)
            .examNeuro(UPDATED_EXAM_NEURO)
            .rapport(UPDATED_RAPPORT);

        restTabExamPhysMockMvc.perform(put("/api/tab-exam-phys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabExamPhys)))
            .andExpect(status().isOk());

        // Validate the TabExamPhys in the database
        List<TabExamPhys> tabExamPhysList = tabExamPhysRepository.findAll();
        assertThat(tabExamPhysList).hasSize(databaseSizeBeforeUpdate);
        TabExamPhys testTabExamPhys = tabExamPhysList.get(tabExamPhysList.size() - 1);
        assertThat(testTabExamPhys.getDateExamPhys()).isEqualTo(UPDATED_DATE_EXAM_PHYS);
        assertThat(testTabExamPhys.getNomAppareil()).isEqualTo(UPDATED_NOM_APPAREIL);
        assertThat(testTabExamPhys.getTete()).isEqualTo(UPDATED_TETE);
        assertThat(testTabExamPhys.getCou()).isEqualTo(UPDATED_COU);
        assertThat(testTabExamPhys.getBouche()).isEqualTo(UPDATED_BOUCHE);
        assertThat(testTabExamPhys.getThorax()).isEqualTo(UPDATED_THORAX);
        assertThat(testTabExamPhys.getAusculationCard()).isEqualTo(UPDATED_AUSCULATION_CARD);
        assertThat(testTabExamPhys.getAusculationPulmo()).isEqualTo(UPDATED_AUSCULATION_PULMO);
        assertThat(testTabExamPhys.getSouffle()).isEqualTo(UPDATED_SOUFFLE);
        assertThat(testTabExamPhys.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testTabExamPhys.getBonchospame()).isEqualTo(UPDATED_BONCHOSPAME);
        assertThat(testTabExamPhys.getPercussionThorax()).isEqualTo(UPDATED_PERCUSSION_THORAX);
        assertThat(testTabExamPhys.getAbdomen()).isEqualTo(UPDATED_ABDOMEN);
        assertThat(testTabExamPhys.isPoulsFemoralG()).isEqualTo(UPDATED_POULS_FEMORAL_G);
        assertThat(testTabExamPhys.isPoulsFemoralD()).isEqualTo(UPDATED_POULS_FEMORAL_D);
        assertThat(testTabExamPhys.isPoulsPopliteG()).isEqualTo(UPDATED_POULS_POPLITE_G);
        assertThat(testTabExamPhys.isPoulsPopliteD()).isEqualTo(UPDATED_POULS_POPLITE_D);
        assertThat(testTabExamPhys.isPoulsPedieuxG()).isEqualTo(UPDATED_POULS_PEDIEUX_G);
        assertThat(testTabExamPhys.isPoulsPedieuxD()).isEqualTo(UPDATED_POULS_PEDIEUX_D);
        assertThat(testTabExamPhys.isPoulstibialPostG()).isEqualTo(UPDATED_POULSTIBIAL_POST_G);
        assertThat(testTabExamPhys.isPoulstibialPostD()).isEqualTo(UPDATED_POULSTIBIAL_POST_D);
        assertThat(testTabExamPhys.isSouffleAbdo()).isEqualTo(UPDATED_SOUFFLE_ABDO);
        assertThat(testTabExamPhys.isSouffleFemoralG()).isEqualTo(UPDATED_SOUFFLE_FEMORAL_G);
        assertThat(testTabExamPhys.isSouffleFemoralD()).isEqualTo(UPDATED_SOUFFLE_FEMORAL_D);
        assertThat(testTabExamPhys.getSpectPeau()).isEqualTo(UPDATED_SPECT_PEAU);
        assertThat(testTabExamPhys.getExamNeuro()).isEqualTo(UPDATED_EXAM_NEURO);
        assertThat(testTabExamPhys.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabExamPhys() throws Exception {
        int databaseSizeBeforeUpdate = tabExamPhysRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabExamPhysMockMvc.perform(put("/api/tab-exam-phys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamPhys)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamPhys in the database
        List<TabExamPhys> tabExamPhysList = tabExamPhysRepository.findAll();
        assertThat(tabExamPhysList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabExamPhys() throws Exception {
        // Initialize the database
        tabExamPhysRepository.saveAndFlush(tabExamPhys);

        int databaseSizeBeforeDelete = tabExamPhysRepository.findAll().size();

        // Delete the tabExamPhys
        restTabExamPhysMockMvc.perform(delete("/api/tab-exam-phys/{id}", tabExamPhys.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabExamPhys> tabExamPhysList = tabExamPhysRepository.findAll();
        assertThat(tabExamPhysList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
