package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TabUser.
 */
@Entity
@Table(name = "tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private String dateNaissance;

    @Column(name = "genre")
    private String genre;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "est_actif")
    private Boolean estActif;

    @Column(name = "dmaj_mdp")
    private String dmajMdp;

    @Column(name = "sta_connex")
    private Boolean staConnex;

    @Column(name = "matricule_creation")
    private String matriculeCreation;

    @Column(name = "matricule_modif")
    private String matriculeModif;

    @Column(name = "date_creation")
    private String dateCreation;

    @Column(name = "date_modif")
    private String dateModif;

    @Column(name = "langue")
    private String langue;

    @OneToOne
    @JoinColumn(unique = true)
    private TabPersonnel tabPersonnel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public TabUser login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public TabUser mdp(String mdp) {
        this.mdp = mdp;
        return this;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public TabUser nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public TabUser prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public TabUser dateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public TabUser genre(String genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSexe() {
        return sexe;
    }

    public TabUser sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public TabUser telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public TabUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public TabUser estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public String getDmajMdp() {
        return dmajMdp;
    }

    public TabUser dmajMdp(String dmajMdp) {
        this.dmajMdp = dmajMdp;
        return this;
    }

    public void setDmajMdp(String dmajMdp) {
        this.dmajMdp = dmajMdp;
    }

    public Boolean isStaConnex() {
        return staConnex;
    }

    public TabUser staConnex(Boolean staConnex) {
        this.staConnex = staConnex;
        return this;
    }

    public void setStaConnex(Boolean staConnex) {
        this.staConnex = staConnex;
    }

    public String getMatriculeCreation() {
        return matriculeCreation;
    }

    public TabUser matriculeCreation(String matriculeCreation) {
        this.matriculeCreation = matriculeCreation;
        return this;
    }

    public void setMatriculeCreation(String matriculeCreation) {
        this.matriculeCreation = matriculeCreation;
    }

    public String getMatriculeModif() {
        return matriculeModif;
    }

    public TabUser matriculeModif(String matriculeModif) {
        this.matriculeModif = matriculeModif;
        return this;
    }

    public void setMatriculeModif(String matriculeModif) {
        this.matriculeModif = matriculeModif;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public TabUser dateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateModif() {
        return dateModif;
    }

    public TabUser dateModif(String dateModif) {
        this.dateModif = dateModif;
        return this;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

    public String getLangue() {
        return langue;
    }

    public TabUser langue(String langue) {
        this.langue = langue;
        return this;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public TabPersonnel getTabPersonnel() {
        return tabPersonnel;
    }

    public TabUser tabPersonnel(TabPersonnel tabPersonnel) {
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
        if (!(o instanceof TabUser)) {
            return false;
        }
        return id != null && id.equals(((TabUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabUser{" +
            "id=" + getId() +
            ", login='" + getLogin() + "'" +
            ", mdp='" + getMdp() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", estActif='" + isEstActif() + "'" +
            ", dmajMdp='" + getDmajMdp() + "'" +
            ", staConnex='" + isStaConnex() + "'" +
            ", matriculeCreation='" + getMatriculeCreation() + "'" +
            ", matriculeModif='" + getMatriculeModif() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModif='" + getDateModif() + "'" +
            ", langue='" + getLangue() + "'" +
            "}";
    }
}
