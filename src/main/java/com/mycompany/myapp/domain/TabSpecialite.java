package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TabSpecialite.
 */
@Entity
@Table(name = "tab_specialite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabSpecialite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "nom_personnel")
    private String nomPersonnel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public TabSpecialite libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public TabSpecialite nomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
        return this;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabSpecialite)) {
            return false;
        }
        return id != null && id.equals(((TabSpecialite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabSpecialite{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", nomPersonnel='" + getNomPersonnel() + "'" +
            "}";
    }
}
