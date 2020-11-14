package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TabComptabilite.
 */
@Entity
@Table(name = "tab_comptabilite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabComptabilite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom_appareil")
    private String nomAppareil;

    @Column(name = "tarif_appareil")
    private Float tarifAppareil;

    @Column(name = "tarif_specialite")
    private Float tarifSpecialite;

    @Column(name = "tarif_consultation")
    private Float tarifConsultation;

    @Column(name = "tarif_chambre")
    private Float tarifChambre;

    @Column(name = "tarif_sejour")
    private Float tarifSejour;

    @Column(name = "facture_patient")
    private Float facturePatient;

    @Column(name = "recette")
    private Float recette;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabComptabilites", allowSetters = true)
    private TabPatient tabPatient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomAppareil() {
        return nomAppareil;
    }

    public TabComptabilite nomAppareil(String nomAppareil) {
        this.nomAppareil = nomAppareil;
        return this;
    }

    public void setNomAppareil(String nomAppareil) {
        this.nomAppareil = nomAppareil;
    }

    public Float getTarifAppareil() {
        return tarifAppareil;
    }

    public TabComptabilite tarifAppareil(Float tarifAppareil) {
        this.tarifAppareil = tarifAppareil;
        return this;
    }

    public void setTarifAppareil(Float tarifAppareil) {
        this.tarifAppareil = tarifAppareil;
    }

    public Float getTarifSpecialite() {
        return tarifSpecialite;
    }

    public TabComptabilite tarifSpecialite(Float tarifSpecialite) {
        this.tarifSpecialite = tarifSpecialite;
        return this;
    }

    public void setTarifSpecialite(Float tarifSpecialite) {
        this.tarifSpecialite = tarifSpecialite;
    }

    public Float getTarifConsultation() {
        return tarifConsultation;
    }

    public TabComptabilite tarifConsultation(Float tarifConsultation) {
        this.tarifConsultation = tarifConsultation;
        return this;
    }

    public void setTarifConsultation(Float tarifConsultation) {
        this.tarifConsultation = tarifConsultation;
    }

    public Float getTarifChambre() {
        return tarifChambre;
    }

    public TabComptabilite tarifChambre(Float tarifChambre) {
        this.tarifChambre = tarifChambre;
        return this;
    }

    public void setTarifChambre(Float tarifChambre) {
        this.tarifChambre = tarifChambre;
    }

    public Float getTarifSejour() {
        return tarifSejour;
    }

    public TabComptabilite tarifSejour(Float tarifSejour) {
        this.tarifSejour = tarifSejour;
        return this;
    }

    public void setTarifSejour(Float tarifSejour) {
        this.tarifSejour = tarifSejour;
    }

    public Float getFacturePatient() {
        return facturePatient;
    }

    public TabComptabilite facturePatient(Float facturePatient) {
        this.facturePatient = facturePatient;
        return this;
    }

    public void setFacturePatient(Float facturePatient) {
        this.facturePatient = facturePatient;
    }

    public Float getRecette() {
        return recette;
    }

    public TabComptabilite recette(Float recette) {
        this.recette = recette;
        return this;
    }

    public void setRecette(Float recette) {
        this.recette = recette;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabComptabilite tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabComptabilite)) {
            return false;
        }
        return id != null && id.equals(((TabComptabilite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabComptabilite{" +
            "id=" + getId() +
            ", nomAppareil='" + getNomAppareil() + "'" +
            ", tarifAppareil=" + getTarifAppareil() +
            ", tarifSpecialite=" + getTarifSpecialite() +
            ", tarifConsultation=" + getTarifConsultation() +
            ", tarifChambre=" + getTarifChambre() +
            ", tarifSejour=" + getTarifSejour() +
            ", facturePatient=" + getFacturePatient() +
            ", recette=" + getRecette() +
            "}";
    }
}
