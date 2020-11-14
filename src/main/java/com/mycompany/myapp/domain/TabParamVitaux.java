package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabParamVitaux.
 */
@Entity
@Table(name = "tab_param_vitaux")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabParamVitaux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_exam")
    private ZonedDateTime dateExam;

    @Column(name = "ta_1")
    private Float ta1;

    @Column(name = "ta_2")
    private Float ta2;

    @Column(name = "pouls")
    private Float pouls;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "frequence")
    private Float frequence;

    @Column(name = "taille")
    private Float taille;

    @Column(name = "sa_02")
    private Float sa02;

    @Column(name = "sous")
    private Float sous;

    @Column(name = "poids")
    private Float poids;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabParamVitauxes", allowSetters = true)
    private TabConsultation tabConsultation;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabParamVitauxes", allowSetters = true)
    private TabGynecologie tabGynecologie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateExam() {
        return dateExam;
    }

    public TabParamVitaux dateExam(ZonedDateTime dateExam) {
        this.dateExam = dateExam;
        return this;
    }

    public void setDateExam(ZonedDateTime dateExam) {
        this.dateExam = dateExam;
    }

    public Float getTa1() {
        return ta1;
    }

    public TabParamVitaux ta1(Float ta1) {
        this.ta1 = ta1;
        return this;
    }

    public void setTa1(Float ta1) {
        this.ta1 = ta1;
    }

    public Float getTa2() {
        return ta2;
    }

    public TabParamVitaux ta2(Float ta2) {
        this.ta2 = ta2;
        return this;
    }

    public void setTa2(Float ta2) {
        this.ta2 = ta2;
    }

    public Float getPouls() {
        return pouls;
    }

    public TabParamVitaux pouls(Float pouls) {
        this.pouls = pouls;
        return this;
    }

    public void setPouls(Float pouls) {
        this.pouls = pouls;
    }

    public Float getTemperature() {
        return temperature;
    }

    public TabParamVitaux temperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getFrequence() {
        return frequence;
    }

    public TabParamVitaux frequence(Float frequence) {
        this.frequence = frequence;
        return this;
    }

    public void setFrequence(Float frequence) {
        this.frequence = frequence;
    }

    public Float getTaille() {
        return taille;
    }

    public TabParamVitaux taille(Float taille) {
        this.taille = taille;
        return this;
    }

    public void setTaille(Float taille) {
        this.taille = taille;
    }

    public Float getSa02() {
        return sa02;
    }

    public TabParamVitaux sa02(Float sa02) {
        this.sa02 = sa02;
        return this;
    }

    public void setSa02(Float sa02) {
        this.sa02 = sa02;
    }

    public Float getSous() {
        return sous;
    }

    public TabParamVitaux sous(Float sous) {
        this.sous = sous;
        return this;
    }

    public void setSous(Float sous) {
        this.sous = sous;
    }

    public Float getPoids() {
        return poids;
    }

    public TabParamVitaux poids(Float poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public TabConsultation getTabConsultation() {
        return tabConsultation;
    }

    public TabParamVitaux tabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
        return this;
    }

    public void setTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
    }

    public TabGynecologie getTabGynecologie() {
        return tabGynecologie;
    }

    public TabParamVitaux tabGynecologie(TabGynecologie tabGynecologie) {
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
        if (!(o instanceof TabParamVitaux)) {
            return false;
        }
        return id != null && id.equals(((TabParamVitaux) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabParamVitaux{" +
            "id=" + getId() +
            ", dateExam='" + getDateExam() + "'" +
            ", ta1=" + getTa1() +
            ", ta2=" + getTa2() +
            ", pouls=" + getPouls() +
            ", temperature=" + getTemperature() +
            ", frequence=" + getFrequence() +
            ", taille=" + getTaille() +
            ", sa02=" + getSa02() +
            ", sous=" + getSous() +
            ", poids=" + getPoids() +
            "}";
    }
}
