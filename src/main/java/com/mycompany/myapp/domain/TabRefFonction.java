package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TabRefFonction.
 */
@Entity
@Table(name = "tab_ref_fonction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabRefFonction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_fonction")
    private Integer idFonction;

    @Column(name = "libelle")
    private String libelle;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFonction() {
        return idFonction;
    }

    public TabRefFonction idFonction(Integer idFonction) {
        this.idFonction = idFonction;
        return this;
    }

    public void setIdFonction(Integer idFonction) {
        this.idFonction = idFonction;
    }

    public String getLibelle() {
        return libelle;
    }

    public TabRefFonction libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabRefFonction)) {
            return false;
        }
        return id != null && id.equals(((TabRefFonction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabRefFonction{" +
            "id=" + getId() +
            ", idFonction=" + getIdFonction() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
