package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabCoordonnee.
 */
@Entity
@Table(name = "tab_coordonnee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabCoordonnee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quartier")
    private String quartier;

    @Column(name = "commune")
    private String commune;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_ville")
    private String codeVille;

    @Column(name = "rue")
    private String rue;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "portable")
    private String portable;

    @Column(name = "prevenir")
    private String prevenir;

    @Column(name = "fax")
    private String fax;

    @Column(name = "adresse_mail")
    private String adresseMail;

    @Column(name = "adresse_prevenir")
    private String adressePrevenir;

    @Column(name = "adresse_libelle_long")
    private String adresseLibelleLong;

    @Column(name = "matriculecreation")
    private String matriculecreation;

    @Column(name = "datecreation")
    private ZonedDateTime datecreation;

    @Column(name = "matriculemodif")
    private String matriculemodif;

    @Column(name = "datemodif")
    private ZonedDateTime datemodif;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuartier() {
        return quartier;
    }

    public TabCoordonnee quartier(String quartier) {
        this.quartier = quartier;
        return this;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getCommune() {
        return commune;
    }

    public TabCoordonnee commune(String commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getVille() {
        return ville;
    }

    public TabCoordonnee ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public TabCoordonnee codeVille(String codeVille) {
        this.codeVille = codeVille;
        return this;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public String getRue() {
        return rue;
    }

    public TabCoordonnee rue(String rue) {
        this.rue = rue;
        return this;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getNumero() {
        return numero;
    }

    public TabCoordonnee numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTelephone() {
        return telephone;
    }

    public TabCoordonnee telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPortable() {
        return portable;
    }

    public TabCoordonnee portable(String portable) {
        this.portable = portable;
        return this;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public String getPrevenir() {
        return prevenir;
    }

    public TabCoordonnee prevenir(String prevenir) {
        this.prevenir = prevenir;
        return this;
    }

    public void setPrevenir(String prevenir) {
        this.prevenir = prevenir;
    }

    public String getFax() {
        return fax;
    }

    public TabCoordonnee fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public TabCoordonnee adresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
        return this;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getAdressePrevenir() {
        return adressePrevenir;
    }

    public TabCoordonnee adressePrevenir(String adressePrevenir) {
        this.adressePrevenir = adressePrevenir;
        return this;
    }

    public void setAdressePrevenir(String adressePrevenir) {
        this.adressePrevenir = adressePrevenir;
    }

    public String getAdresseLibelleLong() {
        return adresseLibelleLong;
    }

    public TabCoordonnee adresseLibelleLong(String adresseLibelleLong) {
        this.adresseLibelleLong = adresseLibelleLong;
        return this;
    }

    public void setAdresseLibelleLong(String adresseLibelleLong) {
        this.adresseLibelleLong = adresseLibelleLong;
    }

    public String getMatriculecreation() {
        return matriculecreation;
    }

    public TabCoordonnee matriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
        return this;
    }

    public void setMatriculecreation(String matriculecreation) {
        this.matriculecreation = matriculecreation;
    }

    public ZonedDateTime getDatecreation() {
        return datecreation;
    }

    public TabCoordonnee datecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
        return this;
    }

    public void setDatecreation(ZonedDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public String getMatriculemodif() {
        return matriculemodif;
    }

    public TabCoordonnee matriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
        return this;
    }

    public void setMatriculemodif(String matriculemodif) {
        this.matriculemodif = matriculemodif;
    }

    public ZonedDateTime getDatemodif() {
        return datemodif;
    }

    public TabCoordonnee datemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
        return this;
    }

    public void setDatemodif(ZonedDateTime datemodif) {
        this.datemodif = datemodif;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabCoordonnee)) {
            return false;
        }
        return id != null && id.equals(((TabCoordonnee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabCoordonnee{" +
            "id=" + getId() +
            ", quartier='" + getQuartier() + "'" +
            ", commune='" + getCommune() + "'" +
            ", ville='" + getVille() + "'" +
            ", codeVille='" + getCodeVille() + "'" +
            ", rue='" + getRue() + "'" +
            ", numero=" + getNumero() +
            ", telephone='" + getTelephone() + "'" +
            ", portable='" + getPortable() + "'" +
            ", prevenir='" + getPrevenir() + "'" +
            ", fax='" + getFax() + "'" +
            ", adresseMail='" + getAdresseMail() + "'" +
            ", adressePrevenir='" + getAdressePrevenir() + "'" +
            ", adresseLibelleLong='" + getAdresseLibelleLong() + "'" +
            ", matriculecreation='" + getMatriculecreation() + "'" +
            ", datecreation='" + getDatecreation() + "'" +
            ", matriculemodif='" + getMatriculemodif() + "'" +
            ", datemodif='" + getDatemodif() + "'" +
            "}";
    }
}
