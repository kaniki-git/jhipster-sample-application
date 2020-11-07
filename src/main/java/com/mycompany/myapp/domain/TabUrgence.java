package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Service Urgence
 */
@ApiModel(description = "Service Urgence")
@Entity
@Table(name = "tab_urgence")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUrgence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_arrivee_urgence")
    private ZonedDateTime dateArriveeUrgence;

    @Column(name = "date_depart_urgence")
    private ZonedDateTime dateDepartUrgence;

    @Column(name = "rapport_urgence")
    private String rapportUrgence;

    @OneToMany(mappedBy = "tabUrgence")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabParamVitaux> tabParamVitauxes = new HashSet<>();

    @OneToMany(mappedBy = "tabUrgence")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabConsultation> tabConsultations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateArriveeUrgence() {
        return dateArriveeUrgence;
    }

    public TabUrgence dateArriveeUrgence(ZonedDateTime dateArriveeUrgence) {
        this.dateArriveeUrgence = dateArriveeUrgence;
        return this;
    }

    public void setDateArriveeUrgence(ZonedDateTime dateArriveeUrgence) {
        this.dateArriveeUrgence = dateArriveeUrgence;
    }

    public ZonedDateTime getDateDepartUrgence() {
        return dateDepartUrgence;
    }

    public TabUrgence dateDepartUrgence(ZonedDateTime dateDepartUrgence) {
        this.dateDepartUrgence = dateDepartUrgence;
        return this;
    }

    public void setDateDepartUrgence(ZonedDateTime dateDepartUrgence) {
        this.dateDepartUrgence = dateDepartUrgence;
    }

    public String getRapportUrgence() {
        return rapportUrgence;
    }

    public TabUrgence rapportUrgence(String rapportUrgence) {
        this.rapportUrgence = rapportUrgence;
        return this;
    }

    public void setRapportUrgence(String rapportUrgence) {
        this.rapportUrgence = rapportUrgence;
    }

    public Set<TabParamVitaux> getTabParamVitauxes() {
        return tabParamVitauxes;
    }

    public TabUrgence tabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
        return this;
    }

    public TabUrgence addTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.add(tabParamVitaux);
        tabParamVitaux.setTabUrgence(this);
        return this;
    }

    public TabUrgence removeTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.remove(tabParamVitaux);
        tabParamVitaux.setTabUrgence(null);
        return this;
    }

    public void setTabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
    }

    public Set<TabConsultation> getTabConsultations() {
        return tabConsultations;
    }

    public TabUrgence tabConsultations(Set<TabConsultation> tabConsultations) {
        this.tabConsultations = tabConsultations;
        return this;
    }

    public TabUrgence addTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.add(tabConsultation);
        tabConsultation.setTabUrgence(this);
        return this;
    }

    public TabUrgence removeTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.remove(tabConsultation);
        tabConsultation.setTabUrgence(null);
        return this;
    }

    public void setTabConsultations(Set<TabConsultation> tabConsultations) {
        this.tabConsultations = tabConsultations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabUrgence)) {
            return false;
        }
        return id != null && id.equals(((TabUrgence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabUrgence{" +
            "id=" + getId() +
            ", dateArriveeUrgence='" + getDateArriveeUrgence() + "'" +
            ", dateDepartUrgence='" + getDateDepartUrgence() + "'" +
            ", rapportUrgence='" + getRapportUrgence() + "'" +
            "}";
    }
}
