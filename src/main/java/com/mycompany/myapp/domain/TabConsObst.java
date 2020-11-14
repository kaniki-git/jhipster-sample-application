package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

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

    @Column(name = "date_cons_obst")
    private ZonedDateTime dateConsObst;

    @Column(name = "age_patient")
    private String agePatient;

    @Column(name = "poids")
    private Float poids;

    @Column(name = "tas")
    private Float tas;

    @Column(name = "tad")
    private Float tad;

    @Column(name = "a")
    private Float a;

    @Column(name = "s")
    private String s;

    @Column(name = "n")
    private String n;

    @Column(name = "hu")
    private String hu;

    @Column(name = "bc")
    private String bc;

    @Column(name = "sb")
    private String sb;

    @Column(name = "oe")
    private String oe;

    @Column(name = "pres")
    private String pres;

    @Column(name = "conclusion")
    private String conclusion;

    @Column(name = "traitement")
    private String traitement;

    @Column(name = "suivi_par")
    private String suiviPar;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabConsObsts", allowSetters = true)
    private TabGynecologie tabGynecologie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateConsObst() {
        return dateConsObst;
    }

    public TabConsObst dateConsObst(ZonedDateTime dateConsObst) {
        this.dateConsObst = dateConsObst;
        return this;
    }

    public void setDateConsObst(ZonedDateTime dateConsObst) {
        this.dateConsObst = dateConsObst;
    }

    public String getAgePatient() {
        return agePatient;
    }

    public TabConsObst agePatient(String agePatient) {
        this.agePatient = agePatient;
        return this;
    }

    public void setAgePatient(String agePatient) {
        this.agePatient = agePatient;
    }

    public Float getPoids() {
        return poids;
    }

    public TabConsObst poids(Float poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public Float getTas() {
        return tas;
    }

    public TabConsObst tas(Float tas) {
        this.tas = tas;
        return this;
    }

    public void setTas(Float tas) {
        this.tas = tas;
    }

    public Float getTad() {
        return tad;
    }

    public TabConsObst tad(Float tad) {
        this.tad = tad;
        return this;
    }

    public void setTad(Float tad) {
        this.tad = tad;
    }

    public Float getA() {
        return a;
    }

    public TabConsObst a(Float a) {
        this.a = a;
        return this;
    }

    public void setA(Float a) {
        this.a = a;
    }

    public String getS() {
        return s;
    }

    public TabConsObst s(String s) {
        this.s = s;
        return this;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getN() {
        return n;
    }

    public TabConsObst n(String n) {
        this.n = n;
        return this;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getHu() {
        return hu;
    }

    public TabConsObst hu(String hu) {
        this.hu = hu;
        return this;
    }

    public void setHu(String hu) {
        this.hu = hu;
    }

    public String getBc() {
        return bc;
    }

    public TabConsObst bc(String bc) {
        this.bc = bc;
        return this;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getSb() {
        return sb;
    }

    public TabConsObst sb(String sb) {
        this.sb = sb;
        return this;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getOe() {
        return oe;
    }

    public TabConsObst oe(String oe) {
        this.oe = oe;
        return this;
    }

    public void setOe(String oe) {
        this.oe = oe;
    }

    public String getPres() {
        return pres;
    }

    public TabConsObst pres(String pres) {
        this.pres = pres;
        return this;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getConclusion() {
        return conclusion;
    }

    public TabConsObst conclusion(String conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getTraitement() {
        return traitement;
    }

    public TabConsObst traitement(String traitement) {
        this.traitement = traitement;
        return this;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
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

    public TabGynecologie getTabGynecologie() {
        return tabGynecologie;
    }

    public TabConsObst tabGynecologie(TabGynecologie tabGynecologie) {
        this.tabGynecologie = tabGynecologie;
        return this;
    }

    public void setTabGynecologie(TabGynecologie tabGynecologie) {
        this.tabGynecologie = tabGynecologie;
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
            ", dateConsObst='" + getDateConsObst() + "'" +
            ", agePatient='" + getAgePatient() + "'" +
            ", poids=" + getPoids() +
            ", tas=" + getTas() +
            ", tad=" + getTad() +
            ", a=" + getA() +
            ", s='" + getS() + "'" +
            ", n='" + getN() + "'" +
            ", hu='" + getHu() + "'" +
            ", bc='" + getBc() + "'" +
            ", sb='" + getSb() + "'" +
            ", oe='" + getOe() + "'" +
            ", pres='" + getPres() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            ", traitement='" + getTraitement() + "'" +
            ", suiviPar='" + getSuiviPar() + "'" +
            "}";
    }
}
