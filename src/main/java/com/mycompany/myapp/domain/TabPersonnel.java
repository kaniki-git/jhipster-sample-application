package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A TabPersonnel.
 */
@Entity
@Table(name = "tab_personnel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabPersonnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "etat_civil")
    private String etatCivil;

    @Column(name = "type_personnel")
    private String typePersonnel;

    @Column(name = "activite")
    private String activite;

    @Column(name = "grade")
    private String grade;

    @Column(name = "dateentreeservice")
    private ZonedDateTime dateentreeservice;

    @Column(name = "nomstatut")
    private String nomstatut;

    @Column(name = "matriculecreation")
    private String matriculecreation;

    @Column(name = "datecreation")
    private ZonedDateTime datecreation;

    @Column(name = "matriculemodif")
    private String matriculemodif;

    @Column(name = "datemodif")
    private ZonedDateTime datemodif;

    @OneToOne
    @JoinColumn(unique = true)
    private TabPatient tabPatient;

    @OneToOne
    @JoinColumn(unique = true)
    private TabCoordonnee tabCoordonnee;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabPersonnels", allowSetters = true)
    private TabSpecialite tabSpecialite;

    @ManyToMany(mappedBy = "tabPersonnels")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<TabHospital> tabHospitals = new HashSet<>();

    @ManyToMany(mappedBy = "tabPersonnels")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<TabConsultation> tabConsultations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public TabPersonnel matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEtatCivil() {
        return etatCivil;
    }

    public TabPersonnel etatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
        return this;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public String getTypePersonnel() {
        return typePersonnel;
    }

    public TabPersonnel typePersonnel(String typePersonnel) {
        this.typePersonnel = typePersonnel;
        return this;
    }

    public void setTypePersonnel(String typePersonnel) {
        this.typePersonnel = typePersonnel;
    }

    public String getActivite() {
        return activite;
    }

    public TabPersonnel activite(String activite) {
        this.activite = activite;
        return this;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getGrade() {
        return grade;
    }

    public TabPersonnel grade(String grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ZonedDateTime getDateentreeservice() {
        return dateentreeservice;
    }

    public TabPersonnel dateentreeservice(ZonedDateTime dateentreeservice) {
        this.dateentreeservice = dateentreeservice;
        return this;
    }

    public void setDateentreeservice(ZonedDateTime dateentreeservice) {
        this.dateentreeservice = dateentreeservice;
    }

    public String getNomstatut() {
        return nomstatut;
    }

    public TabPersonnel nomstatut(String nomstatut) {
        this.nomstatut = nomstatut;
        return this;
    }

    public void setNomstatut(String nomstatut) {
        this.nomstatut = nomstatut;
    }

    public String getMatriculecreation() {
        return matriculecreation;
    }

    public TabPersonnel matriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
        return this;
    }

    public void setMatriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
    }

    public ZonedDateTime getDatecreation() {
        return datecreation;
    }

    public TabPersonnel datecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
        return this;
    }

    public void setDatecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getMatriculemodif() {
        return matriculemodif;
    }

    public TabPersonnel matriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
        return this;
    }

    public void setMatriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
    }

    public ZonedDateTime getDatemodif() {
        return datemodif;
    }

    public TabPersonnel datemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
        return this;
    }

    public void setDatemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabPersonnel tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }

    public TabCoordonnee getTabCoordonnee() {
        return tabCoordonnee;
    }

    public TabPersonnel tabCoordonnee(TabCoordonnee tabCoordonnee) {
        this.tabCoordonnee = tabCoordonnee;
        return this;
    }

    public void setTabCoordonnee(TabCoordonnee tabCoordonnee) {
        this.tabCoordonnee = tabCoordonnee;
    }

    public TabSpecialite getTabSpecialite() {
        return tabSpecialite;
    }

    public TabPersonnel tabSpecialite(TabSpecialite tabSpecialite) {
        this.tabSpecialite = tabSpecialite;
        return this;
    }

    public void setTabSpecialite(TabSpecialite tabSpecialite) {
        this.tabSpecialite = tabSpecialite;
    }

    public Set<TabHospital> getTabHospitals() {
        return tabHospitals;
    }

    public TabPersonnel tabHospitals(Set<TabHospital> tabHospitals) {
        this.tabHospitals = tabHospitals;
        return this;
    }

    public TabPersonnel addTabHospital(TabHospital tabHospital) {
        this.tabHospitals.add(tabHospital);
        tabHospital.getTabPersonnels().add(this);
        return this;
    }

    public TabPersonnel removeTabHospital(TabHospital tabHospital) {
        this.tabHospitals.remove(tabHospital);
        tabHospital.getTabPersonnels().remove(this);
        return this;
    }

    public void setTabHospitals(Set<TabHospital> tabHospitals) {
        this.tabHospitals = tabHospitals;
    }

    public Set<TabConsultation> getTabConsultations() {
        return tabConsultations;
    }

    public TabPersonnel tabConsultations(Set<TabConsultation> tabConsultations) {
        this.tabConsultations = tabConsultations;
        return this;
    }

    public TabPersonnel addTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.add(tabConsultation);
        tabConsultation.getTabPersonnels().add(this);
        return this;
    }

    public TabPersonnel removeTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultations.remove(tabConsultation);
        tabConsultation.getTabPersonnels().remove(this);
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
        if (!(o instanceof TabPersonnel)) {
            return false;
        }
        return id != null && id.equals(((TabPersonnel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabPersonnel{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", typePersonnel='" + getTypePersonnel() + "'" +
            ", activite='" + getActivite() + "'" +
            ", grade='" + getGrade() + "'" +
            ", dateentreeservice='" + getDateentreeservice() + "'" +
            ", nomstatut='" + getNomstatut() + "'" +
            ", matriculecreation='" + getMatriculecreation() + "'" +
            ", datecreation='" + getDatecreation() + "'" +
            ", matriculemodif='" + getMatriculemodif() + "'" +
            ", datemodif='" + getDatemodif() + "'" +
            "}";
    }
}
