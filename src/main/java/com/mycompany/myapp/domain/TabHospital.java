package com.mycompany.myapp.domain;

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
 * A TabHospital.
 */
@Entity
@Table(name = "tab_hospital")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabHospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_admission")
    private ZonedDateTime dateAdmission;

    @Column(name = "motif_admission")
    private String motifAdmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "provenance_patient")
    private ProvenancePatient provenancePatient;

    @Column(name = "evol_jour")
    private String evolJour;

    @Column(name = "evol_synthese")
    private String evolSynthese;

    @Column(name = "plant_therapeute")
    private String plantTherapeute;

    @Enumerated(EnumType.STRING)
    @Column(name = "ledevenir_patient")
    private LedevenirPatient ledevenirPatient;

    @Column(name = "prochain_rdv")
    private ZonedDateTime prochainRdv;

    @Column(name = "lieu_rdv")
    private String lieuRdv;

    @Column(name = "conclusion_sejour")
    private String conclusionSejour;

    @Column(name = "nom_consultant")
    private String nomConsultant;

    @Column(name = "bilan_admission")
    private String bilanAdmission;

    @Column(name = "rapport")
    private String rapport;

    @OneToMany(mappedBy = "tabHospital")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabConsultation> tabConsultations = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tab_hospital_tab_personnel",
               joinColumns = @JoinColumn(name = "tab_hospital_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tab_personnel_id", referencedColumnName = "id"))
    private Set<TabPersonnel> tabPersonnels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateAdmission() {
        return dateAdmission;
    }

    public TabHospital dateAdmission(ZonedDateTime dateAdmission) {
        this.dateAdmission = dateAdmission;
        return this;
    }

    public void setDateAdmission(ZonedDateTime dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public String getMotifAdmission() {
        return motifAdmission;
    }

    public TabHospital motifAdmission(String motifAdmission) {
        this.motifAdmission = motifAdmission;
        return this;
    }

    public void setMotifAdmission(String motifAdmission) {
        this.motifAdmission = motifAdmission;
    }

    public ProvenancePatient getProvenancePatient() {
        return provenancePatient;
    }

    public TabHospital provenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
        return this;
    }

    public void setProvenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
    }

    public String getEvolJour() {
        return evolJour;
    }

    public TabHospital evolJour(String evolJour) {
        this.evolJour = evolJour;
        return this;
    }

    public void setEvolJour(String evolJour) {
        this.evolJour = evolJour;
    }

    public String getEvolSynthese() {
        return evolSynthese;
    }

    public TabHospital evolSynthese(String evolSynthese) {
        this.evolSynthese = evolSynthese;
        return this;
    }

    public void setEvolSynthese(String evolSynthese) {
        this.evolSynthese = evolSynthese;
    }

    public String getPlantTherapeute() {
        return plantTherapeute;
    }

    public TabHospital plantTherapeute(String plantTherapeute) {
        this.plantTherapeute = plantTherapeute;
        return this;
    }

    public void setPlantTherapeute(String plantTherapeute) {
        this.plantTherapeute = plantTherapeute;
    }

    public LedevenirPatient getLedevenirPatient() {
        return ledevenirPatient;
    }

    public TabHospital ledevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
        return this;
    }

    public void setLedevenirPatient(LedevenirPatient ledevenirPatient) {
        this.ledevenirPatient = ledevenirPatient;
    }

    public ZonedDateTime getProchainRdv() {
        return prochainRdv;
    }

    public TabHospital prochainRdv(ZonedDateTime prochainRdv) {
        this.prochainRdv = prochainRdv;
        return this;
    }

    public void setProchainRdv(ZonedDateTime prochainRdv) {
        this.prochainRdv = prochainRdv;
    }

    public String getLieuRdv() {
        return lieuRdv;
    }

    public TabHospital lieuRdv(String lieuRdv) {
        this.lieuRdv = lieuRdv;
        return this;
    }

    public void setLieuRdv(String lieuRdv) {
        this.lieuRdv = lieuRdv;
    }

    public String getConclusionSejour() {
        return conclusionSejour;
    }

    public TabHospital conclusionSejour(String conclusionSejour) {
        this.conclusionSejour = conclusionSejour;
        return this;
    }

    public void setConclusionSejour(String conclusionSejour) {
        this.conclusionSejour = conclusionSejour;
    }

    public String getNomConsultant() {
        return nomConsultant;
    }

    public TabHospital nomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
        return this;
    }

    public void setNomConsultant(String nomConsultant) {
        this.nomConsultant = nomConsultant;
    }

    public String getBilanAdmission() {
        return bilanAdmission;
    }

    public TabHospital bilanAdmission(String bilanAdmission) {
        this.bilanAdmission = bilanAdmission;
        return this;
    }

    public void setBilanAdmission(String bilanAdmission) {
        this.bilanAdmission = bilanAdmission;
    }

    public String getRapport() {
        return rapport;
    }

    public TabHospital rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public Set<TabConsultation> getTabConsultations() {
        return tabConsultations;
    }

    public TabHospital tabConsultations(Set<TabConsultation> tabConsultations) {
        this.tabConsultations = tabConsultations;
        return this;
    }

    public TabHospital addTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.add(tabConsultation);
        tabConsultation.setTabHospital(this);
        return this;
    }

    public TabHospital removeTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.remove(tabConsultation);
        tabConsultation.setTabHospital(null);
        return this;
    }

    public void setTabConsultations(Set<TabConsultation> tabConsultations) {
        this.tabConsultations = tabConsultations;
    }

    public Set<TabPersonnel> getTabPersonnels() {
        return tabPersonnels;
    }

    public TabHospital tabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
        return this;
    }

    public TabHospital addTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.add(tabPersonnel);
        tabPersonnel.getTabHospitals().add(this);
        return this;
    }

    public TabHospital removeTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.remove(tabPersonnel);
        tabPersonnel.getTabHospitals().remove(this);
        return this;
    }

    public void setTabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabHospital)) {
            return false;
        }
        return id != null && id.equals(((TabHospital) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabHospital{" +
            "id=" + getId() +
            ", dateAdmission='" + getDateAdmission() + "'" +
            ", motifAdmission='" + getMotifAdmission() + "'" +
            ", provenancePatient='" + getProvenancePatient() + "'" +
            ", evolJour='" + getEvolJour() + "'" +
            ", evolSynthese='" + getEvolSynthese() + "'" +
            ", plantTherapeute='" + getPlantTherapeute() + "'" +
            ", ledevenirPatient='" + getLedevenirPatient() + "'" +
            ", prochainRdv='" + getProchainRdv() + "'" +
            ", lieuRdv='" + getLieuRdv() + "'" +
            ", conclusionSejour='" + getConclusionSejour() + "'" +
            ", nomConsultant='" + getNomConsultant() + "'" +
            ", bilanAdmission='" + getBilanAdmission() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
