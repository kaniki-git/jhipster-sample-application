package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.ProvenancePatient;

import com.mycompany.myapp.domain.enumeration.LedevenirPatient;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "provenance_patient")
    private ProvenancePatient provenancePatient;

    @Enumerated(EnumType.STRING)
    @Column(name = "ledevenir_patient")
    private LedevenirPatient ledevenirPatient;

    @Column(name = "rapport_urgence")
    private String rapportUrgence;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabUrgences", allowSetters = true)
    private TabAdministratif tabAdministratif;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabUrgences", allowSetters = true)
    private TabConsultation tabConsultation;

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

    public ProvenancePatient getProvenancePatient() {
        return provenancePatient;
    }

    public TabUrgence provenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
        return this;
    }

    public void setProvenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
    }

    public LedevenirPatient getLedevenirPatient() {
        return ledevenirPatient;
    }

    public TabUrgence ledevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
        return this;
    }

    public void setLedevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
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

    public TabAdministratif getTabAdministratif() {
        return tabAdministratif;
    }

    public TabUrgence tabAdministratif(TabAdministratif tabAdministratif) {
        this.tabAdministratif = tabAdministratif;
        return this;
    }

    public void setTabAdministratif(TabAdministratif tabAdministratif) {
        this.tabAdministratif = tabAdministratif;
    }

    public TabConsultation getTabConsultation() {
        return tabConsultation;
    }

    public TabUrgence tabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
        return this;
    }

    public void setTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
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
            ", provenancePatient='" + getProvenancePatient() + "'" +
            ", ledevenirPatient='" + getLedevenirPatient() + "'" +
            ", rapportUrgence='" + getRapportUrgence() + "'" +
            "}";
    }
}
