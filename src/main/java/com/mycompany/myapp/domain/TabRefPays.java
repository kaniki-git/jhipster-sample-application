package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TabRefPays.
 */
@Entity
@Table(name = "tab_ref_pays")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabRefPays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_pays")
    private Integer idPays;

    @Column(name = "libelle")
    private String libelle;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public TabRefPays idPays(Integer idPays) {
        this.idPays = idPays;
        return this;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public String getLibelle() {
        return libelle;
    }

    public TabRefPays libelle(String libelle) {
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
        if (!(o instanceof TabRefPays)) {
            return false;
        }
        return id != null && id.equals(((TabRefPays) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabRefPays{" +
            "id=" + getId() +
            ", idPays=" + getIdPays() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
