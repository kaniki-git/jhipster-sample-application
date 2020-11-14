package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A TabPatient.
 */
@Entity
@Table(name = "tab_patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabPatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "etat_civil")
    private String etatCivil;

    @Column(name = "nombre_enfant")
    private Integer nombreEnfant;

    @Column(name = "nombre_grossesse")
    private String nombreGrossesse;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom_1")
    private String prenom1;

    @Column(name = "prenom_2")
    private String prenom2;

    @Column(name = "datenaissance")
    private ZonedDateTime datenaissance;

    @Column(name = "lieunaissance")
    private String lieunaissance;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "activite")
    private String activite;

    @Column(name = "photo_url")
    private String photoUrl;

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
    private TabAdministratif tabAdministratif;

    @OneToOne
    @JoinColumn(unique = true)
    private TabHistoriquePatient tabHistoriquePatient;

    @OneToMany(mappedBy = "tabPatient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabComptabilite> tabComptabilites = new HashSet<>();

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

    public TabPatient matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getSexe() {
        return sexe;
    }

    public TabPatient sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEtatCivil() {
        return etatCivil;
    }

    public TabPatient etatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
        return this;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public Integer getNombreEnfant() {
        return nombreEnfant;
    }

    public TabPatient nombreEnfant(Integer nombreEnfant) {
        this.nombreEnfant = nombreEnfant;
        return this;
    }

    public void setNombreEnfant(Integer nombreEnfant) {
        this.nombreEnfant = nombreEnfant;
    }

    public String getNombreGrossesse() {
        return nombreGrossesse;
    }

    public TabPatient nombreGrossesse(String nombreGrossesse) {
        this.nombreGrossesse = nombreGrossesse;
        return this;
    }

    public void setNombreGrossesse(String nombreGrossesse) {
        this.nombreGrossesse = nombreGrossesse;
    }

    public String getNom() {
        return nom;
    }

    public TabPatient nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom1() {
        return prenom1;
    }

    public TabPatient prenom1(String prenom1) {
        this.prenom1 = prenom1;
        return this;
    }

    public void setPrenom1(String prenom1) {
        this.prenom1 = prenom1;
    }

    public String getPrenom2() {
        return prenom2;
    }

    public TabPatient prenom2(String prenom2) {
        this.prenom2 = prenom2;
        return this;
    }

    public void setPrenom2(String prenom2) {
        this.prenom2 = prenom2;
    }

    public ZonedDateTime getDatenaissance() {
        return datenaissance;
    }

    public TabPatient datenaissance(ZonedDateTime datenaissance) {
        this.datenaissance = datenaissance;
        return this;
    }

    public void setDatenaissance(ZonedDateTime datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public TabPatient lieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
        return this;
    }

    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public TabPatient nationalite(String nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getActivite() {
        return activite;
    }

    public TabPatient activite(String activite) {
        this.activite = activite;
        return this;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public TabPatient photoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMatriculecreation() {
        return matriculecreation;
    }

    public TabPatient matriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
        return this;
    }

    public void setMatriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
    }

    public ZonedDateTime getDatecreation() {
        return datecreation;
    }

    public TabPatient datecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
        return this;
    }

    public void setDatecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getMatriculemodif() {
        return matriculemodif;
    }

    public TabPatient matriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
        return this;
    }

    public void setMatriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
    }

    public ZonedDateTime getDatemodif() {
        return datemodif;
    }

    public TabPatient datemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
        return this;
    }

    public void setDatemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
    }

    public TabAdministratif getTabAdministratif() {
        return tabAdministratif;
    }

    public TabPatient tabAdministratif(TabAdministratif tabAdministratif) {
        this.tabAdministratif = tabAdministratif;
        return this;
    }

    public void setTabAdministratif(TabAdministratif tabAdministratif) {
        this.tabAdministratif = tabAdministratif;
    }

    public TabHistoriquePatient getTabHistoriquePatient() {
        return tabHistoriquePatient;
    }

    public TabPatient tabHistoriquePatient(TabHistoriquePatient tabHistoriquePatient) {
        this.tabHistoriquePatient = tabHistoriquePatient;
        return this;
    }

    public void setTabHistoriquePatient(TabHistoriquePatient tabHistoriquePatient) {
        this.tabHistoriquePatient = tabHistoriquePatient;
    }

    public Set<TabComptabilite> getTabComptabilites() {
        return tabComptabilites;
    }

    public TabPatient tabComptabilites(Set<TabComptabilite> tabComptabilites) {
        this.tabComptabilites = tabComptabilites;
        return this;
    }

    public TabPatient addTabComptabilite(TabComptabilite tabComptabilite) {
        this.tabComptabilites.add(tabComptabilite);
        tabComptabilite.setTabPatient(this);
        return this;
    }

    public TabPatient removeTabComptabilite(TabComptabilite tabComptabilite) {
        this.tabComptabilites.remove(tabComptabilite);
        tabComptabilite.setTabPatient(null);
        return this;
    }

    public void setTabComptabilites(Set<TabComptabilite> tabComptabilites) {
        this.tabComptabilites = tabComptabilites;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabPatient)) {
            return false;
        }
        return id != null && id.equals(((TabPatient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabPatient{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", nombreEnfant=" + getNombreEnfant() +
            ", nombreGrossesse='" + getNombreGrossesse() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom1='" + getPrenom1() + "'" +
            ", prenom2='" + getPrenom2() + "'" +
            ", datenaissance='" + getDatenaissance() + "'" +
            ", lieunaissance='" + getLieunaissance() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            ", activite='" + getActivite() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", matriculecreation='" + getMatriculecreation() + "'" +
            ", datecreation='" + getDatecreation() + "'" +
            ", matriculemodif='" + getMatriculemodif() + "'" +
            ", datemodif='" + getDatemodif() + "'" +
            "}";
    }
}
