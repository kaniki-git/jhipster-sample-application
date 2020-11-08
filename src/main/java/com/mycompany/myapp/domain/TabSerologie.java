package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabSerologie.
 */
@Entity
@Table(name = "tab_serologie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabSerologie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_serologie")
    private ZonedDateTime dateSerologie;

    @Column(name = "gr_sang")
    private String grSang;

    @Column(name = "gr_sang_geni")
    private String grSangGeni;

    @Column(name = "caryotype")
    private String caryotype;

    @Column(name = "tarif_serologie")
    private ZonedDateTime tarifSerologie;

    @Column(name = "autres")
    private String autres;

    @Column(name = "rapport")
    private String rapport;

    @OneToOne
    @JoinColumn(unique = true)
    private TabPatient tabPatient;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabSerologies", allowSetters = true)
    private TabConsObst tabConsObst;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabSerologies", allowSetters = true)
    private TabPatient tabPatient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateSerologie() {
        return dateSerologie;
    }

    public TabSerologie dateSerologie(ZonedDateTime dateSerologie) {
        this.dateSerologie = dateSerologie;
        return this;
    }

    public void setDateSerologie(ZonedDateTime dateSerologie) {
        this.dateSerologie = dateSerologie;
    }

    public String getGrSang() {
        return grSang;
    }

    public TabSerologie grSang(String grSang) {
        this.grSang = grSang;
        return this;
    }

    public void setGrSang(String grSang) {
        this.grSang = grSang;
    }

    public String getGrSangGeni() {
        return grSangGeni;
    }

    public TabSerologie grSangGeni(String grSangGeni) {
        this.grSangGeni = grSangGeni;
        return this;
    }

    public void setGrSangGeni(String grSangGeni) {
        this.grSangGeni = grSangGeni;
    }

    public String getCaryotype() {
        return caryotype;
    }

    public TabSerologie caryotype(String caryotype) {
        this.caryotype = caryotype;
        return this;
    }

    public void setCaryotype(String caryotype) {
        this.caryotype = caryotype;
    }

    public ZonedDateTime getTarifSerologie() {
        return tarifSerologie;
    }

    public TabSerologie tarifSerologie(ZonedDateTime tarifSerologie) {
        this.tarifSerologie = tarifSerologie;
        return this;
    }

    public void setTarifSerologie(ZonedDateTime tarifSerologie) {
        this.tarifSerologie = tarifSerologie;
    }

    public String getAutres() {
        return autres;
    }

    public TabSerologie autres(String autres) {
        this.autres = autres;
        return this;
    }

    public void setAutres(String autres) {
        this.autres = autres;
    }

    public String getRapport() {
        return rapport;
    }

    public TabSerologie rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabSerologie tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }

    public TabConsObst getTabConsObst() {
        return tabConsObst;
    }

    public TabSerologie tabConsObst(TabConsObst tabConsObst) {
        this.tabConsObst = tabConsObst;
        return this;
    }

    public void setTabConsObst(TabConsObst tabConsObst) {
        this.tabConsObst = tabConsObst;
    }

    public TabPatient getTabPatient() {
        return tabPatient;
    }

    public TabSerologie tabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
        return this;
    }

    public void setTabPatient(TabPatient tabPatient) {
        this.tabPatient = tabPatient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabSerologie)) {
            return false;
        }
        return id != null && id.equals(((TabSerologie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabSerologie{" +
            "id=" + getId() +
            ", dateSerologie='" + getDateSerologie() + "'" +
            ", grSang='" + getGrSang() + "'" +
            ", grSangGeni='" + getGrSangGeni() + "'" +
            ", caryotype='" + getCaryotype() + "'" +
            ", tarifSerologie='" + getTarifSerologie() + "'" +
            ", autres='" + getAutres() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
