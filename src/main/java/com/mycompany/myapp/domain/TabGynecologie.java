package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A TabGynecologie.
 */
@Entity
@Table(name = "tab_gynecologie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabGynecologie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_consult")
    private ZonedDateTime dateConsult;

    @Column(name = "ddr")
    private String ddr;

    @Column(name = "terme_us")
    private String termeUs;

    @Column(name = "term_corrige")
    private String termCorrige;

    @Column(name = "term_ddr")
    private String termDdr;

    @Column(name = "cycle")
    private String cycle;

    @Column(name = "date_ovulation")
    private ZonedDateTime dateOvulation;

    @Column(name = "age_gestationel")
    private String ageGestationel;

    @Column(name = "date_fin")
    private ZonedDateTime dateFin;

    @Column(name = "test_peri")
    private String testPeri;

    @Column(name = "ecouvillon")
    private String ecouvillon;

    @Column(name = "perine")
    private String perine;

    @Column(name = "bassin")
    private String bassin;

    @Column(name = "ogtt")
    private String ogtt;

    @Column(name = "imc")
    private String imc;

    @Column(name = "poids_mere_initial")
    private Float poidsMereInitial;

    @Column(name = "poids_mere_theori_bebe")
    private Float poidsMereTheoriBebe;

    @Column(name = "taille_mere")
    private Float tailleMere;

    @Column(name = "taille_theori_bebe")
    private Float tailleTheoriBebe;

    @Column(name = "g_sg_mari")
    private String gSgMari;

    @Column(name = "labo_tri_21")
    private String laboTri21;

    @Column(name = "caryotype")
    private String caryotype;

    @Column(name = "suivi_par")
    private String suiviPar;

    @Column(name = "pediatre")
    private String pediatre;

    @Column(name = "risque_orl")
    private String risqueOrl;

    @Column(name = "resume_grossesse")
    private String resumeGrossesse;

    @Column(name = "conduite_accouche")
    private String conduiteAccouche;

    @Column(name = "rapport")
    private String rapport;

    @OneToMany(mappedBy = "tabGynecologie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabParamVitaux> tabParamVitauxes = new HashSet<>();

    @OneToMany(mappedBy = "tabGynecologie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabBiologie> tabBiologies = new HashSet<>();

    @OneToMany(mappedBy = "tabGynecologie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabConsObst> tabConsObsts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "tabGynecologies", allowSetters = true)
    private TabAccouchement tabAccouchement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateConsult() {
        return dateConsult;
    }

    public TabGynecologie dateConsult(ZonedDateTime dateConsult) {
        this.dateConsult = dateConsult;
        return this;
    }

    public void setDateConsult(ZonedDateTime dateConsult) {
        this.dateConsult = dateConsult;
    }

    public String getDdr() {
        return ddr;
    }

    public TabGynecologie ddr(String ddr) {
        this.ddr = ddr;
        return this;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public String getTermeUs() {
        return termeUs;
    }

    public TabGynecologie termeUs(String termeUs) {
        this.termeUs = termeUs;
        return this;
    }

    public void setTermeUs(String termeUs) {
        this.termeUs = termeUs;
    }

    public String getTermCorrige() {
        return termCorrige;
    }

    public TabGynecologie termCorrige(String termCorrige) {
        this.termCorrige = termCorrige;
        return this;
    }

    public void setTermCorrige(String termCorrige) {
        this.termCorrige = termCorrige;
    }

    public String getTermDdr() {
        return termDdr;
    }

    public TabGynecologie termDdr(String termDdr) {
        this.termDdr = termDdr;
        return this;
    }

    public void setTermDdr(String termDdr) {
        this.termDdr = termDdr;
    }

    public String getCycle() {
        return cycle;
    }

    public TabGynecologie cycle(String cycle) {
        this.cycle = cycle;
        return this;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public ZonedDateTime getDateOvulation() {
        return dateOvulation;
    }

    public TabGynecologie dateOvulation(ZonedDateTime dateOvulation) {
        this.dateOvulation = dateOvulation;
        return this;
    }

    public void setDateOvulation(ZonedDateTime dateOvulation) {
        this.dateOvulation = dateOvulation;
    }

    public String getAgeGestationel() {
        return ageGestationel;
    }

    public TabGynecologie ageGestationel(String ageGestationel) {
        this.ageGestationel = ageGestationel;
        return this;
    }

    public void setAgeGestationel(String ageGestationel) {
        this.ageGestationel = ageGestationel;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public TabGynecologie dateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getTestPeri() {
        return testPeri;
    }

    public TabGynecologie testPeri(String testPeri) {
        this.testPeri = testPeri;
        return this;
    }

    public void setTestPeri(String testPeri) {
        this.testPeri = testPeri;
    }

    public String getEcouvillon() {
        return ecouvillon;
    }

    public TabGynecologie ecouvillon(String ecouvillon) {
        this.ecouvillon = ecouvillon;
        return this;
    }

    public void setEcouvillon(String ecouvillon) {
        this.ecouvillon = ecouvillon;
    }

    public String getPerine() {
        return perine;
    }

    public TabGynecologie perine(String perine) {
        this.perine = perine;
        return this;
    }

    public void setPerine(String perine) {
        this.perine = perine;
    }

    public String getBassin() {
        return bassin;
    }

    public TabGynecologie bassin(String bassin) {
        this.bassin = bassin;
        return this;
    }

    public void setBassin(String bassin) {
        this.bassin = bassin;
    }

    public String getOgtt() {
        return ogtt;
    }

    public TabGynecologie ogtt(String ogtt) {
        this.ogtt = ogtt;
        return this;
    }

    public void setOgtt(String ogtt) {
        this.ogtt = ogtt;
    }

    public String getImc() {
        return imc;
    }

    public TabGynecologie imc(String imc) {
        this.imc = imc;
        return this;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public Float getPoidsMereInitial() {
        return poidsMereInitial;
    }

    public TabGynecologie poidsMereInitial(Float poidsMereInitial) {
        this.poidsMereInitial = poidsMereInitial;
        return this;
    }

    public void setPoidsMereInitial(Float poidsMereInitial) {
        this.poidsMereInitial = poidsMereInitial;
    }

    public Float getPoidsMereTheoriBebe() {
        return poidsMereTheoriBebe;
    }

    public TabGynecologie poidsMereTheoriBebe(Float poidsMereTheoriBebe) {
        this.poidsMereTheoriBebe = poidsMereTheoriBebe;
        return this;
    }

    public void setPoidsMereTheoriBebe(Float poidsMereTheoriBebe) {
        this.poidsMereTheoriBebe = poidsMereTheoriBebe;
    }

    public Float getTailleMere() {
        return tailleMere;
    }

    public TabGynecologie tailleMere(Float tailleMere) {
        this.tailleMere = tailleMere;
        return this;
    }

    public void setTailleMere(Float tailleMere) {
        this.tailleMere = tailleMere;
    }

    public Float getTailleTheoriBebe() {
        return tailleTheoriBebe;
    }

    public TabGynecologie tailleTheoriBebe(Float tailleTheoriBebe) {
        this.tailleTheoriBebe = tailleTheoriBebe;
        return this;
    }

    public void setTailleTheoriBebe(Float tailleTheoriBebe) {
        this.tailleTheoriBebe = tailleTheoriBebe;
    }

    public String getgSgMari() {
        return gSgMari;
    }

    public TabGynecologie gSgMari(String gSgMari) {
        this.gSgMari = gSgMari;
        return this;
    }

    public void setgSgMari(String gSgMari) {
        this.gSgMari = gSgMari;
    }

    public String getLaboTri21() {
        return laboTri21;
    }

    public TabGynecologie laboTri21(String laboTri21) {
        this.laboTri21 = laboTri21;
        return this;
    }

    public void setLaboTri21(String laboTri21) {
        this.laboTri21 = laboTri21;
    }

    public String getCaryotype() {
        return caryotype;
    }

    public TabGynecologie caryotype(String caryotype) {
        this.caryotype = caryotype;
        return this;
    }

    public void setCaryotype(String caryotype) {
        this.caryotype = caryotype;
    }

    public String getSuiviPar() {
        return suiviPar;
    }

    public TabGynecologie suiviPar(String suiviPar) {
        this.suiviPar = suiviPar;
        return this;
    }

    public void setSuiviPar(String suiviPar) {
        this.suiviPar = suiviPar;
    }

    public String getPediatre() {
        return pediatre;
    }

    public TabGynecologie pediatre(String pediatre) {
        this.pediatre = pediatre;
        return this;
    }

    public void setPediatre(String pediatre) {
        this.pediatre = pediatre;
    }

    public String getRisqueOrl() {
        return risqueOrl;
    }

    public TabGynecologie risqueOrl(String risqueOrl) {
        this.risqueOrl = risqueOrl;
        return this;
    }

    public void setRisqueOrl(String risqueOrl) {
        this.risqueOrl = risqueOrl;
    }

    public String getResumeGrossesse() {
        return resumeGrossesse;
    }

    public TabGynecologie resumeGrossesse(String resumeGrossesse) {
        this.resumeGrossesse = resumeGrossesse;
        return this;
    }

    public void setResumeGrossesse(String resumeGrossesse) {
        this.resumeGrossesse = resumeGrossesse;
    }

    public String getConduiteAccouche() {
        return conduiteAccouche;
    }

    public TabGynecologie conduiteAccouche(String conduiteAccouche) {
        this.conduiteAccouche = conduiteAccouche;
        return this;
    }

    public void setConduiteAccouche(String conduiteAccouche) {
        this.conduiteAccouche = conduiteAccouche;
    }

    public String getRapport() {
        return rapport;
    }

    public TabGynecologie rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Set<TabParamVitaux> getTabParamVitauxes() {
        return tabParamVitauxes;
    }

    public TabGynecologie tabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
        return this;
    }

    public TabGynecologie addTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.add(tabParamVitaux);
        tabParamVitaux.setTabGynecologie(this);
        return this;
    }

    public TabGynecologie removeTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.remove(tabParamVitaux);
        tabParamVitaux.setTabGynecologie(null);
        return this;
    }

    public void setTabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
    }

    public Set<TabBiologie> getTabBiologies() {
        return tabBiologies;
    }

    public TabGynecologie tabBiologies(Set<TabBiologie> tabBiologies) {
        this.tabBiologies = tabBiologies;
        return this;
    }

    public TabGynecologie addTabBiologie(TabBiologie tabBiologie) {
        this.tabBiologies.add(tabBiologie);
        tabBiologie.setTabGynecologie(this);
        return this;
    }

    public TabGynecologie removeTabBiologie(TabBiologie tabBiologie) {
        this.tabBiologies.remove(tabBiologie);
        tabBiologie.setTabGynecologie(null);
        return this;
    }

    public void setTabBiologies(Set<TabBiologie> tabBiologies) {
        this.tabBiologies = tabBiologies;
    }

    public Set<TabConsObst> getTabConsObsts() {
        return tabConsObsts;
    }

    public TabGynecologie tabConsObsts(Set<TabConsObst> tabConsObsts) {
        this.tabConsObsts = tabConsObsts;
        return this;
    }

    public TabGynecologie addTabConsObst(TabConsObst tabConsObst) {
        this.tabConsObsts.add(tabConsObst);
        tabConsObst.setTabGynecologie(this);
        return this;
    }

    public TabGynecologie removeTabConsObst(TabConsObst tabConsObst) {
        this.tabConsObsts.remove(tabConsObst);
        tabConsObst.setTabGynecologie(null);
        return this;
    }

    public void setTabConsObsts(Set<TabConsObst> tabConsObsts) {
        this.tabConsObsts = tabConsObsts;
    }

    public TabAccouchement getTabAccouchement() {
        return tabAccouchement;
    }

    public TabGynecologie tabAccouchement(TabAccouchement tabAccouchement) {
        this.tabAccouchement = tabAccouchement;
        return this;
    }

    public void setTabAccouchement(TabAccouchement tabAccouchement) {
        this.tabAccouchement = tabAccouchement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabGynecologie)) {
            return false;
        }
        return id != null && id.equals(((TabGynecologie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabGynecologie{" +
            "id=" + getId() +
            ", dateConsult='" + getDateConsult() + "'" +
            ", ddr='" + getDdr() + "'" +
            ", termeUs='" + getTermeUs() + "'" +
            ", termCorrige='" + getTermCorrige() + "'" +
            ", termDdr='" + getTermDdr() + "'" +
            ", cycle='" + getCycle() + "'" +
            ", dateOvulation='" + getDateOvulation() + "'" +
            ", ageGestationel='" + getAgeGestationel() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", testPeri='" + getTestPeri() + "'" +
            ", ecouvillon='" + getEcouvillon() + "'" +
            ", perine='" + getPerine() + "'" +
            ", bassin='" + getBassin() + "'" +
            ", ogtt='" + getOgtt() + "'" +
            ", imc='" + getImc() + "'" +
            ", poidsMereInitial=" + getPoidsMereInitial() +
            ", poidsMereTheoriBebe=" + getPoidsMereTheoriBebe() +
            ", tailleMere=" + getTailleMere() +
            ", tailleTheoriBebe=" + getTailleTheoriBebe() +
            ", gSgMari='" + getgSgMari() + "'" +
            ", laboTri21='" + getLaboTri21() + "'" +
            ", caryotype='" + getCaryotype() + "'" +
            ", suiviPar='" + getSuiviPar() + "'" +
            ", pediatre='" + getPediatre() + "'" +
            ", risqueOrl='" + getRisqueOrl() + "'" +
            ", resumeGrossesse='" + getResumeGrossesse() + "'" +
            ", conduiteAccouche='" + getConduiteAccouche() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
