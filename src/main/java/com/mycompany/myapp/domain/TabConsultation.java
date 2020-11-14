package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.ProvenancePatient;

import com.mycompany.myapp.domain.enumeration.LedevenirPatient;

/**
 * A TabConsultation.
 */
@Entity
@Table(name = "tab_consultation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabConsultation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_consulte")
    private ZonedDateTime dateConsulte;

    @Enumerated(EnumType.STRING)
    @Column(name = "provenance_patient")
    private ProvenancePatient provenancePatient;

    @Column(name = "motif")
    private String motif;

    @Column(name = "affect_actuel")
    private String affectActuel;

    @Column(name = "antecedent")
    private String antecedent;

    @Column(name = "traite_habituel")
    private String traiteHabituel;

    @Column(name = "allergie")
    private String allergie;

    @Column(name = "tabac")
    private Boolean tabac;

    @Column(name = "alcool")
    private Boolean alcool;

    @Column(name = "facteur_risque")
    private String facteurRisque;

    @Column(name = "hypothese_diag")
    private String hypotheseDiag;

    @Column(name = "avis_medical")
    private String avisMedical;

    @Column(name = "ordre_medical")
    private String ordreMedical;

    @Column(name = "traite_propose")
    private String traitePropose;

    @Enumerated(EnumType.STRING)
    @Column(name = "ledevenir_patient")
    private LedevenirPatient ledevenirPatient;

    @Column(name = "tarif_consult")
    private ZonedDateTime tarifConsult;

    @Column(name = "rapport")
    private String rapport;

    @OneToMany(mappedBy = "tabConsultation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabParamVitaux> tabParamVitauxes = new HashSet<>();

    @OneToMany(mappedBy = "tabConsultation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabBiologie> tabBiologies = new HashSet<>();

    @OneToMany(mappedBy = "tabConsultation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabUrgence> tabUrgences = new HashSet<>();

    @OneToMany(mappedBy = "tabConsultation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabExamPhys> tabExamPhys = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tab_consultation_tab_personnel",
               joinColumns = @JoinColumn(name = "tab_consultation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tab_personnel_id", referencedColumnName = "id"))
    private Set<TabPersonnel> tabPersonnels = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "tabConsultations", allowSetters = true)
    private TabHospital tabHospital;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateConsulte() {
        return dateConsulte;
    }

    public TabConsultation dateConsulte(ZonedDateTime dateConsulte) {
        this.dateConsulte = dateConsulte;
        return this;
    }

    public void setDateConsulte(ZonedDateTime dateConsulte) {
        this.dateConsulte = dateConsulte;
    }

    public ProvenancePatient getProvenancePatient() {
        return provenancePatient;
    }

    public TabConsultation provenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
        return this;
    }

    public void setProvenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
    }

    public String getMotif() {
        return motif;
    }

    public TabConsultation motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getAffectActuel() {
        return affectActuel;
    }

    public TabConsultation affectActuel(String affectActuel) {
        this.affectActuel = affectActuel;
        return this;
    }

    public void setAffectActuel(String affectActuel) {
        this.affectActuel = affectActuel;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public TabConsultation antecedent(String antecedent) {
        this.antecedent = antecedent;
        return this;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public String getTraiteHabituel() {
        return traiteHabituel;
    }

    public TabConsultation traiteHabituel(String traiteHabituel) {
        this.traiteHabituel = traiteHabituel;
        return this;
    }

    public void setTraiteHabituel(String traiteHabituel) {
        this.traiteHabituel = traiteHabituel;
    }

    public String getAllergie() {
        return allergie;
    }

    public TabConsultation allergie(String allergie) {
        this.allergie = allergie;
        return this;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public Boolean isTabac() {
        return tabac;
    }

    public TabConsultation tabac(Boolean tabac) {
        this.tabac = tabac;
        return this;
    }

    public void setTabac(Boolean tabac) {
        this.tabac = tabac;
    }

    public Boolean isAlcool() {
        return alcool;
    }

    public TabConsultation alcool(Boolean alcool) {
        this.alcool = alcool;
        return this;
    }

    public void setAlcool(Boolean alcool) {
        this.alcool = alcool;
    }

    public String getFacteurRisque() {
        return facteurRisque;
    }

    public TabConsultation facteurRisque(String facteurRisque) {
        this.facteurRisque = facteurRisque;
        return this;
    }

    public void setFacteurRisque(String facteurRisque) {
        this.facteurRisque = facteurRisque;
    }

    public String getHypotheseDiag() {
        return hypotheseDiag;
    }

    public TabConsultation hypotheseDiag(String hypotheseDiag) {
        this.hypotheseDiag = hypotheseDiag;
        return this;
    }

    public void setHypotheseDiag(String hypotheseDiag) {
        this.hypotheseDiag = hypotheseDiag;
    }

    public String getAvisMedical() {
        return avisMedical;
    }

    public TabConsultation avisMedical(String avisMedical) {
        this.avisMedical = avisMedical;
        return this;
    }

    public void setAvisMedical(String avisMedical) {
        this.avisMedical = avisMedical;
    }

    public String getOrdreMedical() {
        return ordreMedical;
    }

    public TabConsultation ordreMedical(String ordreMedical) {
        this.ordreMedical = ordreMedical;
        return this;
    }

    public void setOrdreMedical(String ordreMedical) {
        this.ordreMedical = ordreMedical;
    }

    public String getTraitePropose() {
        return traitePropose;
    }

    public TabConsultation traitePropose(String traitePropose) {
        this.traitePropose = traitePropose;
        return this;
    }

    public void setTraitePropose(String traitePropose) {
        this.traitePropose = traitePropose;
    }

    public LedevenirPatient getLedevenirPatient() {
        return ledevenirPatient;
    }

    public TabConsultation ledevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
        return this;
    }

    public void setLedevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
    }

    public ZonedDateTime getTarifConsult() {
        return tarifConsult;
    }

    public TabConsultation tarifConsult(ZonedDateTime tarifConsult) {
        this.tarifConsult = tarifConsult;
        return this;
    }

    public void setTarifConsult(ZonedDateTime tarifConsult) {
        this.tarifConsult = tarifConsult;
    }

    public String getRapport() {
        return rapport;
    }

    public TabConsultation rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Set<TabParamVitaux> getTabParamVitauxes() {
        return tabParamVitauxes;
    }

    public TabConsultation tabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
        return this;
    }

    public TabConsultation addTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.add(tabParamVitaux);
        tabParamVitaux.setTabConsultation(this);
        return this;
    }

    public TabConsultation removeTabParamVitaux(TabParamVitaux tabParamVitaux) {
        this.tabParamVitauxes.remove(tabParamVitaux);
        tabParamVitaux.setTabConsultation(null);
        return this;
    }

    public void setTabParamVitauxes(Set<TabParamVitaux> tabParamVitauxes) {
        this.tabParamVitauxes = tabParamVitauxes;
    }

    public Set<TabBiologie> getTabBiologies() {
        return tabBiologies;
    }

    public TabConsultation tabBiologies(Set<TabBiologie> tabBiologies) {
        this.tabBiologies = tabBiologies;
        return this;
    }

    public TabConsultation addTabBiologie(TabBiologie tabBiologie) {
        this.tabBiologies.add(tabBiologie);
        tabBiologie.setTabConsultation(this);
        return this;
    }

    public TabConsultation removeTabBiologie(TabBiologie tabBiologie) {
        this.tabBiologies.remove(tabBiologie);
        tabBiologie.setTabConsultation(null);
        return this;
    }

    public void setTabBiologies(Set<TabBiologie> tabBiologies) {
        this.tabBiologies = tabBiologies;
    }

    public Set<TabUrgence> getTabUrgences() {
        return tabUrgences;
    }

    public TabConsultation tabUrgences(Set<TabUrgence> tabUrgences) {
        this.tabUrgences = tabUrgences;
        return this;
    }

    public TabConsultation addTabUrgence(TabUrgence tabUrgence) {
        this.tabUrgences.add(tabUrgence);
        tabUrgence.setTabConsultation(this);
        return this;
    }

    public TabConsultation removeTabUrgence(TabUrgence tabUrgence) {
        this.tabUrgences.remove(tabUrgence);
        tabUrgence.setTabConsultation(null);
        return this;
    }

    public void setTabUrgences(Set<TabUrgence> tabUrgences) {
        this.tabUrgences = tabUrgences;
    }

    public Set<TabExamPhys> getTabExamPhys() {
        return tabExamPhys;
    }

    public TabConsultation tabExamPhys(Set<TabExamPhys> tabExamPhys) {
        this.tabExamPhys = tabExamPhys;
        return this;
    }

    public TabConsultation addTabExamPhys(TabExamPhys tabExamPhys) {
        this.tabExamPhys.add(tabExamPhys);
        tabExamPhys.setTabConsultation(this);
        return this;
    }

    public TabConsultation removeTabExamPhys(TabExamPhys tabExamPhys) {
        this.tabExamPhys.remove(tabExamPhys);
        tabExamPhys.setTabConsultation(null);
        return this;
    }

    public void setTabExamPhys(Set<TabExamPhys> tabExamPhys) {
        this.tabExamPhys = tabExamPhys;
    }

    public Set<TabPersonnel> getTabPersonnels() {
        return tabPersonnels;
    }

    public TabConsultation tabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
        return this;
    }

    public TabConsultation addTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.add(tabPersonnel);
        tabPersonnel.getTabConsultations().add(this);
        return this;
    }

    public TabConsultation removeTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.remove(tabPersonnel);
        tabPersonnel.getTabConsultations().remove(this);
        return this;
    }

    public void setTabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
    }

    public TabHospital getTabHospital() {
        return tabHospital;
    }

    public TabConsultation tabHospital(TabHospital tabHospital) {
        this.tabHospital = tabHospital;
        return this;
    }

    public void setTabHospital(TabHospital tabHospital) {
        this.tabHospital = tabHospital;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabConsultation)) {
            return false;
        }
        return id != null && id.equals(((TabConsultation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabConsultation{" +
            "id=" + getId() +
            ", dateConsulte='" + getDateConsulte() + "'" +
            ", provenancePatient='" + getProvenancePatient() + "'" +
            ", motif='" + getMotif() + "'" +
            ", affectActuel='" + getAffectActuel() + "'" +
            ", antecedent='" + getAntecedent() + "'" +
            ", traiteHabituel='" + getTraiteHabituel() + "'" +
            ", allergie='" + getAllergie() + "'" +
            ", tabac='" + isTabac() + "'" +
            ", alcool='" + isAlcool() + "'" +
            ", facteurRisque='" + getFacteurRisque() + "'" +
            ", hypotheseDiag='" + getHypotheseDiag() + "'" +
            ", avisMedical='" + getAvisMedical() + "'" +
            ", ordreMedical='" + getOrdreMedical() + "'" +
            ", traitePropose='" + getTraitePropose() + "'" +
            ", ledevenirPatient='" + getLedevenirPatient() + "'" +
            ", tarifConsult='" + getTarifConsult() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
