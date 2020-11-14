package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabBiologie.
 */
@Entity
@Table(name = "tab_biologie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabBiologie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_biologie")
    private ZonedDateTime dateBiologie;

    @Column(name = "nom_biologie")
    private String nomBiologie;

    @Column(name = "nom_serologie")
    private String nomSerologie;

    @Column(name = "g_sanguin")
    private String gSanguin;

    @Column(name = "gr_sang_geni")
    private String grSangGeni;

    @Column(name = "caryotype")
    private String caryotype;

    @Column(name = "tarif_biologie")
    private Float tarifBiologie;

    @Column(name = "autres_biologie")
    private String autresBiologie;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabBiologies", allowSetters = true)
    private TabConsultation tabConsultation;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabBiologies", allowSetters = true)
    private TabGynecologie tabGynecologie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateBiologie() {
        return dateBiologie;
    }

    public TabBiologie dateBiologie(ZonedDateTime dateBiologie) {
        this.dateBiologie = dateBiologie;
        return this;
    }

    public void setDateBiologie(ZonedDateTime dateBiologie) {
        this.dateBiologie = dateBiologie;
    }

    public String getNomBiologie() {
        return nomBiologie;
    }

    public TabBiologie nomBiologie(String nomBiologie) {
        this.nomBiologie = nomBiologie;
        return this;
    }

    public void setNomBiologie(String nomBiologie) {
        this.nomBiologie = nomBiologie;
    }

    public String getNomSerologie() {
        return nomSerologie;
    }

    public TabBiologie nomSerologie(String nomSerologie) {
        this.nomSerologie = nomSerologie;
        return this;
    }

    public void setNomSerologie(String nomSerologie) {
        this.nomSerologie = nomSerologie;
    }

    public String getgSanguin() {
        return gSanguin;
    }

    public TabBiologie gSanguin(String gSanguin) {
        this.gSanguin = gSanguin;
        return this;
    }

    public void setgSanguin(String gSanguin) {
        this.gSanguin = gSanguin;
    }

    public String getGrSangGeni() {
        return grSangGeni;
    }

    public TabBiologie grSangGeni(String grSangGeni) {
        this.grSangGeni = grSangGeni;
        return this;
    }

    public void setGrSangGeni(String grSangGeni) {
        this.grSangGeni = grSangGeni;
    }

    public String getCaryotype() {
        return caryotype;
    }

    public TabBiologie caryotype(String caryotype) {
        this.caryotype = caryotype;
        return this;
    }

    public void setCaryotype(String caryotype) {
        this.caryotype = caryotype;
    }

    public Float getTarifBiologie() {
        return tarifBiologie;
    }

    public TabBiologie tarifBiologie(Float tarifBiologie) {
        this.tarifBiologie = tarifBiologie;
        return this;
    }

    public void setTarifBiologie(Float tarifBiologie) {
        this.tarifBiologie = tarifBiologie;
    }

    public String getAutresBiologie() {
        return autresBiologie;
    }

    public TabBiologie autresBiologie(String autresBiologie) {
        this.autresBiologie = autresBiologie;
        return this;
    }

    public void setAutresBiologie(String autresBiologie) {
        this.autresBiologie = autresBiologie;
    }

    public TabConsultation getTabConsultation() {
        return tabConsultation;
    }

    public TabBiologie tabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
        return this;
    }

    public void setTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
    }

    public TabGynecologie getTabGynecologie() {
        return tabGynecologie;
    }

    public TabBiologie tabGynecologie(TabGynecologie tabGynecologie) {
        this.tabGynecologie = tabGynecologie;
        return this;
    }

    public void setTabGynecologie(TabGynecologie tabGynecologie) {
        this.tabGynecologie = tabGynecologie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabBiologie)) {
            return false;
        }
        return id != null && id.equals(((TabBiologie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabBiologie{" +
            "id=" + getId() +
            ", dateBiologie='" + getDateBiologie() + "'" +
            ", nomBiologie='" + getNomBiologie() + "'" +
            ", nomSerologie='" + getNomSerologie() + "'" +
            ", gSanguin='" + getgSanguin() + "'" +
            ", grSangGeni='" + getGrSangGeni() + "'" +
            ", caryotype='" + getCaryotype() + "'" +
            ", tarifBiologie=" + getTarifBiologie() +
            ", autresBiologie='" + getAutresBiologie() + "'" +
            "}";
    }
}
