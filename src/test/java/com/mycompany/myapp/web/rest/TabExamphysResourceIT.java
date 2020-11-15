package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.TabExamphys;
import com.mycompany.myapp.repository.TabExamphysRepository;

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
 * Integration tests for the {@link TabExamphysResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TabExamphysResourceIT {

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
    private TabExamphysRepository tabExamphysRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTabExamphysMockMvc;

    private TabExamphys tabExamphys;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamphys createEntity(EntityManager em) {
        TabExamphys tabExamphys = new TabExamphys()
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
        return tabExamphys;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TabExamphys createUpdatedEntity(EntityManager em) {
        TabExamphys tabExamphys = new TabExamphys()
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
        return tabExamphys;
    }

    @BeforeEach
    public void initTest() {
        tabExamphys = createEntity(em);
    }

    @Test
    @Transactional
    public void createTabExamphys() throws Exception {
        int databaseSizeBeforeCreate = tabExamphysRepository.findAll().size();
        // Create the TabExamphys
        restTabExamphysMockMvc.perform(post("/api/tab-examphys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamphys)))
            .andExpect(status().isCreated());

        // Validate the TabExamphys in the database
        List<TabExamphys> tabExamphysList = tabExamphysRepository.findAll();
        assertThat(tabExamphysList).hasSize(databaseSizeBeforeCreate + 1);
        TabExamphys testTabExamphys = tabExamphysList.get(tabExamphysList.size() - 1);
        assertThat(testTabExamphys.getDateExamPhys()).isEqualTo(DEFAULT_DATE_EXAM_PHYS);
        assertThat(testTabExamphys.getNomAppareil()).isEqualTo(DEFAULT_NOM_APPAREIL);
        assertThat(testTabExamphys.getTete()).isEqualTo(DEFAULT_TETE);
        assertThat(testTabExamphys.getCou()).isEqualTo(DEFAULT_COU);
        assertThat(testTabExamphys.getBouche()).isEqualTo(DEFAULT_BOUCHE);
        assertThat(testTabExamphys.getThorax()).isEqualTo(DEFAULT_THORAX);
        assertThat(testTabExamphys.getAusculationCard()).isEqualTo(DEFAULT_AUSCULATION_CARD);
        assertThat(testTabExamphys.getAusculationPulmo()).isEqualTo(DEFAULT_AUSCULATION_PULMO);
        assertThat(testTabExamphys.getSouffle()).isEqualTo(DEFAULT_SOUFFLE);
        assertThat(testTabExamphys.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testTabExamphys.getBonchospame()).isEqualTo(DEFAULT_BONCHOSPAME);
        assertThat(testTabExamphys.getPercussionThorax()).isEqualTo(DEFAULT_PERCUSSION_THORAX);
        assertThat(testTabExamphys.getAbdomen()).isEqualTo(DEFAULT_ABDOMEN);
        assertThat(testTabExamphys.isPoulsFemoralG()).isEqualTo(DEFAULT_POULS_FEMORAL_G);
        assertThat(testTabExamphys.isPoulsFemoralD()).isEqualTo(DEFAULT_POULS_FEMORAL_D);
        assertThat(testTabExamphys.isPoulsPopliteG()).isEqualTo(DEFAULT_POULS_POPLITE_G);
        assertThat(testTabExamphys.isPoulsPopliteD()).isEqualTo(DEFAULT_POULS_POPLITE_D);
        assertThat(testTabExamphys.isPoulsPedieuxG()).isEqualTo(DEFAULT_POULS_PEDIEUX_G);
        assertThat(testTabExamphys.isPoulsPedieuxD()).isEqualTo(DEFAULT_POULS_PEDIEUX_D);
        assertThat(testTabExamphys.isPoulstibialPostG()).isEqualTo(DEFAULT_POULSTIBIAL_POST_G);
        assertThat(testTabExamphys.isPoulstibialPostD()).isEqualTo(DEFAULT_POULSTIBIAL_POST_D);
        assertThat(testTabExamphys.isSouffleAbdo()).isEqualTo(DEFAULT_SOUFFLE_ABDO);
        assertThat(testTabExamphys.isSouffleFemoralG()).isEqualTo(DEFAULT_SOUFFLE_FEMORAL_G);
        assertThat(testTabExamphys.isSouffleFemoralD()).isEqualTo(DEFAULT_SOUFFLE_FEMORAL_D);
        assertThat(testTabExamphys.getSpectPeau()).isEqualTo(DEFAULT_SPECT_PEAU);
        assertThat(testTabExamphys.getExamNeuro()).isEqualTo(DEFAULT_EXAM_NEURO);
        assertThat(testTabExamphys.getRapport()).isEqualTo(DEFAULT_RAPPORT);
    }

    @Test
    @Transactional
    public void createTabExamphysWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tabExamphysRepository.findAll().size();

        // Create the TabExamphys with an existing ID
        tabExamphys.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTabExamphysMockMvc.perform(post("/api/tab-examphys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamphys)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamphys in the database
        List<TabExamphys> tabExamphysList = tabExamphysRepository.findAll();
        assertThat(tabExamphysList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTabExamphys() throws Exception {
        // Initialize the database
        tabExamphysRepository.saveAndFlush(tabExamphys);

        // Get all the tabExamphysList
        restTabExamphysMockMvc.perform(get("/api/tab-examphys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tabExamphys.getId().intValue())))
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
    public void getTabExamphys() throws Exception {
        // Initialize the database
        tabExamphysRepository.saveAndFlush(tabExamphys);

        // Get the tabExamphys
        restTabExamphysMockMvc.perform(get("/api/tab-examphys/{id}", tabExamphys.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tabExamphys.getId().intValue()))
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
    public void getNonExistingTabExamphys() throws Exception {
        // Get the tabExamphys
        restTabExamphysMockMvc.perform(get("/api/tab-examphys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTabExamphys() throws Exception {
        // Initialize the database
        tabExamphysRepository.saveAndFlush(tabExamphys);

        int databaseSizeBeforeUpdate = tabExamphysRepository.findAll().size();

        // Update the tabExamphys
        TabExamphys updatedTabExamphys = tabExamphysRepository.findById(tabExamphys.getId()).get();
        // Disconnect from session so that the updates on updatedTabExamphys are not directly saved in db
        em.detach(updatedTabExamphys);
        updatedTabExamphys
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

        restTabExamphysMockMvc.perform(put("/api/tab-examphys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTabExamphys)))
            .andExpect(status().isOk());

        // Validate the TabExamphys in the database
        List<TabExamphys> tabExamphysList = tabExamphysRepository.findAll();
        assertThat(tabExamphysList).hasSize(databaseSizeBeforeUpdate);
        TabExamphys testTabExamphys = tabExamphysList.get(tabExamphysList.size() - 1);
        assertThat(testTabExamphys.getDateExamPhys()).isEqualTo(UPDATED_DATE_EXAM_PHYS);
        assertThat(testTabExamphys.getNomAppareil()).isEqualTo(UPDATED_NOM_APPAREIL);
        assertThat(testTabExamphys.getTete()).isEqualTo(UPDATED_TETE);
        assertThat(testTabExamphys.getCou()).isEqualTo(UPDATED_COU);
        assertThat(testTabExamphys.getBouche()).isEqualTo(UPDATED_BOUCHE);
        assertThat(testTabExamphys.getThorax()).isEqualTo(UPDATED_THORAX);
        assertThat(testTabExamphys.getAusculationCard()).isEqualTo(UPDATED_AUSCULATION_CARD);
        assertThat(testTabExamphys.getAusculationPulmo()).isEqualTo(UPDATED_AUSCULATION_PULMO);
        assertThat(testTabExamphys.getSouffle()).isEqualTo(UPDATED_SOUFFLE);
        assertThat(testTabExamphys.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testTabExamphys.getBonchospame()).isEqualTo(UPDATED_BONCHOSPAME);
        assertThat(testTabExamphys.getPercussionThorax()).isEqualTo(UPDATED_PERCUSSION_THORAX);
        assertThat(testTabExamphys.getAbdomen()).isEqualTo(UPDATED_ABDOMEN);
        assertThat(testTabExamphys.isPoulsFemoralG()).isEqualTo(UPDATED_POULS_FEMORAL_G);
        assertThat(testTabExamphys.isPoulsFemoralD()).isEqualTo(UPDATED_POULS_FEMORAL_D);
        assertThat(testTabExamphys.isPoulsPopliteG()).isEqualTo(UPDATED_POULS_POPLITE_G);
        assertThat(testTabExamphys.isPoulsPopliteD()).isEqualTo(UPDATED_POULS_POPLITE_D);
        assertThat(testTabExamphys.isPoulsPedieuxG()).isEqualTo(UPDATED_POULS_PEDIEUX_G);
        assertThat(testTabExamphys.isPoulsPedieuxD()).isEqualTo(UPDATED_POULS_PEDIEUX_D);
        assertThat(testTabExamphys.isPoulstibialPostG()).isEqualTo(UPDATED_POULSTIBIAL_POST_G);
        assertThat(testTabExamphys.isPoulstibialPostD()).isEqualTo(UPDATED_POULSTIBIAL_POST_D);
        assertThat(testTabExamphys.isSouffleAbdo()).isEqualTo(UPDATED_SOUFFLE_ABDO);
        assertThat(testTabExamphys.isSouffleFemoralG()).isEqualTo(UPDATED_SOUFFLE_FEMORAL_G);
        assertThat(testTabExamphys.isSouffleFemoralD()).isEqualTo(UPDATED_SOUFFLE_FEMORAL_D);
        assertThat(testTabExamphys.getSpectPeau()).isEqualTo(UPDATED_SPECT_PEAU);
        assertThat(testTabExamphys.getExamNeuro()).isEqualTo(UPDATED_EXAM_NEURO);
        assertThat(testTabExamphys.getRapport()).isEqualTo(UPDATED_RAPPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingTabExamphys() throws Exception {
        int databaseSizeBeforeUpdate = tabExamphysRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTabExamphysMockMvc.perform(put("/api/tab-examphys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tabExamphys)))
            .andExpect(status().isBadRequest());

        // Validate the TabExamphys in the database
        List<TabExamphys> tabExamphysList = tabExamphysRepository.findAll();
        assertThat(tabExamphysList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTabExamphys() throws Exception {
        // Initialize the database
        tabExamphysRepository.saveAndFlush(tabExamphys);

        int databaseSizeBeforeDelete = tabExamphysRepository.findAll().size();

        // Delete the tabExamphys
        restTabExamphysMockMvc.perform(delete("/api/tab-examphys/{id}", tabExamphys.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TabExamphys> tabExamphysList = tabExamphysRepository.findAll();
        assertThat(tabExamphysList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
