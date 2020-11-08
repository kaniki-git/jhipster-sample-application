package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A TabConsObst.
 */
@Entity
@Table(name = "tab_cons_obst")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabConsObst implements Serializable {

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

    @Column(name = "date_ovulation")
    private ZonedDateTime dateOvulation;

    @Column(name = "age_gestationel")
    private String ageGestationel;

    @Column(name = "term_corrige")
    private String termCorrige;

    @Column(name = "perine")
    private String perine;

    @Column(name = "bassin")
    private String bassin;

    @Column(name = "ogtt")
    private String ogtt;

    @Column(name = "suivi_par")
    private String suiviPar;

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

    @Column(name = "labo_tri_21")
    private String laboTri21;

    @Column(name = "resume_grossesse")
    private String resumeGrossesse;

    @Column(name = "conduite_accouche")
    private String conduiteAccouche;

    @Column(name = "rapport")
    private String rapport;

    @OneToOne
    @JoinColumn(unique = true)
    private TabPatient tabPatient;

    @OneToMany(mappedBy = "tabConsObst")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabSerologie> tabSerologies = new HashSet<>();

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

    public TabConsObst dateConsult(ZonedDateTime dateConsult) {
        this.dateConsult = dateConsult;
        return this;
    }

    public void setDateConsult(ZonedDateTime dateConsult) {
        this.dateConsult = dateConsult;
    }

    public String getDdr() {
        return ddr;
    }

    public TabConsObst ddr(String ddr) {
        this.ddr = ddr;
        return this;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public String getTermeUs() {
        return termeUs;
    }

    public TabConsObst termeUs(String termeUs) {
        this.termeUs = termeUs;
        return this;
    }

    public void setTermeUs(String termeUs) {
        this.termeUs = termeUs;
    }

    public ZonedDateTime getDateOvulation() {
        return dateOvulation;
    }

    public TabConsObst dateOvulation(ZonedDateTime dateOvulation) {
        this.dateOvulation = dateOvulation;
        return this;
    }

    public void setDateOvulation(ZonedDateTime dateOvulation) {
        this.dateOvulation = dateOvulation;
    }

    public String getAgeGestationel() {
        return ageGestationel;
    }

    public TabConsObst ageGestationel(String ageGestationel) {
        this.ageGestationel = ageGestationel;
        return this;
    }

    public void setAgeGestationel(String ageGestationel) {
        this.ageGestationel = ageGestationel;
    }

    public String getTermCorrige() {
        return termCorrige;
    }

    public TabConsObst termCorrige(String termCorrige) {
        this.termCorrige = termCorrige;
        return this;
    }

    public void setTermCorrige(String termCorrige) {
        this.termCorrige = termCorrige;
    }

    public String getPerine() {
        return perine;
    }

    public TabConsObst perine(String perine) {
        this.perine = perine;
        return this;
    }

    public void setPerine(String perine) {
        this.perine = perine;
    }

    public String getBassin() {
        return bassin;
    }

    public TabConsObst bassin(String bassin) {
        this.bassin = bassin;
        return this;
    }

    public void setBassin(String bassin) {
        this.bassin = bassin;
    }

    public String getOgtt() {
        return ogtt;
    }

    public TabConsObst ogtt(String ogtt) {
        this.ogtt = ogtt;
        return this;
    }

    public void setOgtt(String ogtt) {
        this.ogtt = ogtt;
    }

    public String getSuiviPar() {
        return suiviPar;
    }

    public TabConsObst suiviPar(String suiviPar) {
        this.suiviPar = suiviPar;
        return this;
    }

    public void setSuiviPar(String suiviPar) {
        this.suiviPar = suiviPar;
    }

    public String getImc() {
        return imc;
    }

    public TabConsObst imc(String imc) {
        this.imc = imc;
        return this;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public Float getPoidsMereInitial() {
        return poidsMereInitial;
    }

    public TabConsObst poidsMereInitial(Float poidsMereInitial) {
        this.poidsMereInitial = poidsMereInitial;
        return this;
    }

    public void setPoidsMereInitial(Float poidsMereInitial) {
        this.poidsMereInitial = poidsMereInitial;
    }

    public Float getPoidsMereTheoriBebe() {
        return poidsMereTheoriBebe;
    }

    public TabConsObst poidsMereTheoriBebe(Float poidsMereTheoriBebe) {
        this.poidsMereTheoriBebe = poidsMereTheoriBebe;
        return this;
    }

    public void setPoidsMereTheoriBebe(Float poidsMereTheoriBebe) {
        this.poidsMereTheoriBebe = poidsMereTheoriBebe;
    }

    public Float getTailleMere() {
        return tailleMere;
    }

    public TabConsObst tailleMere(Float tailleMere) {
        this.tailleMere = tailleMere;
        return this;
    }

    public void setTailleMere(Float tailleMere) {
        this.tailleMere = tailleMere;
    }

    public Float getTailleTheoriBebe() {
        return tailleTheoriBebe;
    }

    public TabConsObst tailleTheoriBebe(Float tailleTheoriBebe) {
        this.tailleTheoriBebe = tailleTheoriBebe;
        return this;
    }

    public void setTailleTheoriBebe(Float tailleTheoriBebe) {
        this.tailleTheoriBebe = tailleTheoriBebe;
    }

    public String getLaboTri21() {
        return laboTri21;
    }

    public TabConsObst laboTri21(String laboTri21) {
        this.laboTri21 = laboTri21;
        return this;
    }

    public void setLaboTri21(String laboTri21) {
        this.laboTri21 = laboTri21;
    }

    public String getResumeGrossesse() {
        return resumeGrossesse;
    }

    public TabConsObst resumeGrossesse(String resumeGrossesse) {
        this.resumeGrossesse = resumeGrossesse;
        return this;
    }

    public void setResumeGrossesse(String resumeGrossesse) {
        this.resumeGrossesse = resumeGrossesse;
    }

    public String getConduiteAccouche() {
        return conduiteAccouche;
    }

    public TabConsObst conduiteAccouche(String conduiteAccouche) {
        this.conduiteAccouche = conduiteAccouche;
        return this;
    }

    public void setConduiteAccouche(String conduiteAccouche) {
        this.conduiteAccouche = conduiteAccouche;
    }

    public String getRapport() {
        return rapport;
    }

    public TabConsObst rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabConsObst tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }

    public Set<TabSerologie> getTabSerologies() {
        return tabSerologies;
    }

    public TabConsObst tabSerologies(Set<TabSerologie> tabSerologies) {
        this.tabSerologies = tabSerologies;
        return this;
    }

    public TabConsObst addTabSerologie(TabSerologie tabSerologie) {
        this.tabSerologies.add(tabSerologie);
        tabSerologie.setTabConsObst(this);
        return this;
    }

    public TabConsObst removeTabSerologie(TabSerologie tabSerologie) {
        this.tabSerologies.remove(tabSerologie);
        tabSerologie.setTabConsObst(null);
        return this;
    }

    public void setTabSerologies(Set<TabSerologie> tabSerologies) {
        this.tabSerologies = tabSerologies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabConsObst)) {
            return false;
        }
        return id != null && id.equals(((TabConsObst) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabConsObst{" +
            "id=" + getId() +
            ", dateConsult='" + getDateConsult() + "'" +
            ", ddr='" + getDdr() + "'" +
            ", termeUs='" + getTermeUs() + "'" +
            ", dateOvulation='" + getDateOvulation() + "'" +
            ", ageGestationel='" + getAgeGestationel() + "'" +
            ", termCorrige='" + getTermCorrige() + "'" +
            ", perine='" + getPerine() + "'" +
            ", bassin='" + getBassin() + "'" +
            ", ogtt='" + getOgtt() + "'" +
            ", suiviPar='" + getSuiviPar() + "'" +
            ", imc='" + getImc() + "'" +
            ", poidsMereInitial=" + getPoidsMereInitial() +
            ", poidsMereTheoriBebe=" + getPoidsMereTheoriBebe() +
            ", tailleMere=" + getTailleMere() +
            ", tailleTheoriBebe=" + getTailleTheoriBebe() +
            ", laboTri21='" + getLaboTri21() + "'" +
            ", resumeGrossesse='" + getResumeGrossesse() + "'" +
            ", conduiteAccouche='" + getConduiteAccouche() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
