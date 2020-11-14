package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.ProvenancePatient;

/**
 * A TabAdministratif.
 */
@Entity
@Table(name = "tab_administratif")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabAdministratif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "provenance_patient")
    private ProvenancePatient provenancePatient;

    @Column(name = "numero_chambre")
    private String numeroChambre;

    @Column(name = "batiment")
    private String batiment;

    @Column(name = "date_entree")
    private ZonedDateTime dateEntree;

    @Column(name = "date_sortie")
    private ZonedDateTime dateSortie;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "couverture")
    private String couverture;

    @Column(name = "numerosecu")
    private String numerosecu;

    @Column(name = "nomassurrance")
    private String nomassurrance;

    @Column(name = "coordonneesecu")
    private String coordonneesecu;

    @Column(name = "nom_medecin_perso")
    private String nomMedecinPerso;

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
    private TabCoordonnee tabCoordonnee;

    @OneToMany(mappedBy = "tabAdministratif")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TabUrgence> tabUrgences = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProvenancePatient getProvenancePatient() {
        return provenancePatient;
    }

    public TabAdministratif provenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
        return this;
    }

    public void setProvenancePatient(ProvenancePatient provenancePatient) {
        this.provenancePatient = provenancePatient;
    }

    public String getNumeroChambre() {
        return numeroChambre;
    }

    public TabAdministratif numeroChambre(String numeroChambre) {
        this.numeroChambre = numeroChambre;
        return this;
    }

    public void setNumeroChambre(String numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public String getBatiment() {
        return batiment;
    }

    public TabAdministratif batiment(String batiment) {
        this.batiment = batiment;
        return this;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public ZonedDateTime getDateEntree() {
        return dateEntree;
    }

    public TabAdministratif dateEntree(ZonedDateTime dateEntree) {
        this.dateEntree = dateEntree;
        return this;
    }

    public void setDateEntree(ZonedDateTime dateEntree) {
        this.dateEntree = dateEntree;
    }

    public ZonedDateTime getDateSortie() {
        return dateSortie;
    }

    public TabAdministratif dateSortie(ZonedDateTime dateSortie) {
        this.dateSortie = dateSortie;
        return this;
    }

    public void setDateSortie(ZonedDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public TabAdministratif commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCouverture() {
        return couverture;
    }

    public TabAdministratif couverture(String couverture) {
        this.couverture = couverture;
        return this;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    public String getNumerosecu() {
        return numerosecu;
    }

    public TabAdministratif numerosecu(String numerosecu) {
        this.numerosecu = numerosecu;
        return this;
    }

    public void setNumerosecu(String numerosecu) {
        this.numerosecu = numerosecu;
    }

    public String getNomassurrance() {
        return nomassurrance;
    }

    public TabAdministratif nomassurrance(String nomassurrance) {
        this.nomassurrance = nomassurrance;
        return this;
    }

    public void setNomassurrance(String nomassurrance) {
        this.nomassurrance = nomassurrance;
    }

    public String getCoordonneesecu() {
        return coordonneesecu;
    }

    public TabAdministratif coordonneesecu(String coordonneesecu) {
        this.coordonneesecu = coordonneesecu;
        return this;
    }

    public void setCoordonneesecu(String coordonneesecu) {
        this.coordonneesecu = coordonneesecu;
    }

    public String getNomMedecinPerso() {
        return nomMedecinPerso;
    }

    public TabAdministratif nomMedecinPerso(String nomMedecinPerso) {
        this.nomMedecinPerso = nomMedecinPerso;
        return this;
    }

    public void setNomMedecinPerso(String nomMedecinPerso) {
        this.nomMedecinPerso = nomMedecinPerso;
    }

    public String getMatriculecreation() {
        return matriculecreation;
    }

    public TabAdministratif matriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
        return this;
    }

    public void setMatriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
    }

    public ZonedDateTime getDatecreation() {
        return datecreation;
    }

    public TabAdministratif datecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
        return this;
    }

    public void setDatecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getMatriculemodif() {
        return matriculemodif;
    }

    public TabAdministratif matriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
        return this;
    }

    public void setMatriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
    }

    public ZonedDateTime getDatemodif() {
        return datemodif;
    }

    public TabAdministratif datemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
        return this;
    }

    public void setDatemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
    }

    public TabCoordonnee getTabCoordonnee() {
        return tabCoordonnee;
    }

    public TabAdministratif tabCoordonnee(TabCoordonnee tabCoordonnee) {
        this.tabCoordonnee = tabCoordonnee;
        return this;
    }

    public void setTabCoordonnee(TabCoordonnee tabCoordonnee) {
        this.tabCoordonnee = tabCoordonnee;
    }

    public Set<TabUrgence> getTabUrgences() {
        return tabUrgences;
    }

    public TabAdministratif tabUrgences(Set<TabUrgence> tabUrgences) {
        this.tabUrgences = tabUrgences;
        return this;
    }

    public TabAdministratif addTabUrgence(TabUrgence tabUrgence) {
        this.tabUrgences.add(tabUrgence);
        tabUrgence.setTabAdministratif(this);
        return this;
    }

    public TabAdministratif removeTabUrgence(TabUrgence tabUrgence) {
        this.tabUrgences.remove(tabUrgence);
        tabUrgence.setTabAdministratif(null);
        return this;
    }

    public void setTabUrgences(Set<TabUrgence> tabUrgences) {
        this.tabUrgences = tabUrgences;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabAdministratif)) {
            return false;
        }
        return id != null && id.equals(((TabAdministratif) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabAdministratif{" +
            "id=" + getId() +
            ", provenancePatient='" + getProvenancePatient() + "'" +
            ", numeroChambre='" + getNumeroChambre() + "'" +
            ", batiment='" + getBatiment() + "'" +
            ", dateEntree='" + getDateEntree() + "'" +
            ", dateSortie='" + getDateSortie() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", couverture='" + getCouverture() + "'" +
            ", numerosecu='" + getNumerosecu() + "'" +
            ", nomassurrance='" + getNomassurrance() + "'" +
            ", coordonneesecu='" + getCoordonneesecu() + "'" +
            ", nomMedecinPerso='" + getNomMedecinPerso() + "'" +
            ", matriculecreation='" + getMatriculecreation() + "'" +
            ", datecreation='" + getDatecreation() + "'" +
            ", matriculemodif='" + getMatriculemodif() + "'" +
            ", datemodif='" + getDatemodif() + "'" +
            "}";
    }
}
