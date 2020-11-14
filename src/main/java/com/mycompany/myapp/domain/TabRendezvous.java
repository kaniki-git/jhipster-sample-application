package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabRendezvous.
 */
@Entity
@Table(name = "tab_rendezvous")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabRendezvous implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "ville")
    private String ville;

    @Column(name = "date_debut")
    private ZonedDateTime dateDebut;

    @Column(name = "date_fin")
    private ZonedDateTime dateFin;

    @Column(name = "jour_entier")
    private Boolean jourEntier;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "arriere_plan_couleur")
    private String arrierePlanCouleur;

    @Column(name = "texte_couleur")
    private String texteCouleur;

    @Column(name = "salle")
    private String salle;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "matriculecreation")
    private String matriculecreation;

    @Column(name = "datecreation")
    private ZonedDateTime datecreation;

    @Column(name = "matriculemodif")
    private String matriculemodif;

    @Column(name = "datemodif")
    private ZonedDateTime datemodif;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabRendezvous", allowSetters = true)
    private TabPatient tabPatient;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabRendezvous", allowSetters = true)
    private TabPersonnel tabPersonnel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public TabRendezvous titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public TabRendezvous ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public TabRendezvous dateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public TabRendezvous dateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean isJourEntier() {
        return jourEntier;
    }

    public TabRendezvous jourEntier(Boolean jourEntier) {
        this.jourEntier = jourEntier;
        return this;
    }

    public void setJourEntier(Boolean jourEntier) {
        this.jourEntier = jourEntier;
    }

    public String getGroupId() {
        return groupId;
    }

    public TabRendezvous groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArrierePlanCouleur() {
        return arrierePlanCouleur;
    }

    public TabRendezvous arrierePlanCouleur(String arrierePlanCouleur) {
        this.arrierePlanCouleur = arrierePlanCouleur;
        return this;
    }

    public void setArrierePlanCouleur(String arrierePlanCouleur) {
        this.arrierePlanCouleur = arrierePlanCouleur;
    }

    public String getTexteCouleur() {
        return texteCouleur;
    }

    public TabRendezvous texteCouleur(String texteCouleur) {
        this.texteCouleur = texteCouleur;
        return this;
    }

    public void setTexteCouleur(String texteCouleur) {
        this.texteCouleur = texteCouleur;
    }

    public String getSalle() {
        return salle;
    }

    public TabRendezvous salle(String salle) {
        this.salle = salle;
        return this;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public TabRendezvous commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getMatriculecreation() {
        return matriculecreation;
    }

    public TabRendezvous matriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
        return this;
    }

    public void setMatriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
    }

    public ZonedDateTime getDatecreation() {
        return datecreation;
    }

    public TabRendezvous datecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
        return this;
    }

    public void setDatecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getMatriculemodif() {
        return matriculemodif;
    }

    public TabRendezvous matriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
        return this;
    }

    public void setMatriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
    }

    public ZonedDateTime getDatemodif() {
        return datemodif;
    }

    public TabRendezvous datemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
        return this;
    }

    public void setDatemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabRendezvous tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }

    public TabPersonnel getTabPersonnel() {
        return tabPersonnel;
    }

    public TabRendezvous tabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnel = tabPersonnel;
        return this;
    }

    public void setTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnel = tabPersonnel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabRendezvous)) {
            return false;
        }
        return id != null && id.equals(((TabRendezvous) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabRendezvous{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", ville='" + getVille() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", jourEntier='" + isJourEntier() + "'" +
            ", groupId='" + getGroupId() + "'" +
            ", arrierePlanCouleur='" + getArrierePlanCouleur() + "'" +
            ", texteCouleur='" + getTexteCouleur() + "'" +
            ", salle='" + getSalle() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", matriculecreation='" + getMatriculecreation() + "'" +
            ", datecreation='" + getDatecreation() + "'" +
            ", matriculemodif='" + getMatriculemodif() + "'" +
            ", datemodif='" + getDatemodif() + "'" +
            "}";
    }
}
