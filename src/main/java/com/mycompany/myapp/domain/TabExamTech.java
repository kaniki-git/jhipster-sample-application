package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabExamTech.
 */
@Entity
@Table(name = "tab_exam_tech")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabExamTech implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type_exam_tech")
    private String typeExamTech;

    @Column(name = "date_exam_tech")
    private ZonedDateTime dateExamTech;

    @Column(name = "tarif_exam_tech")
    private ZonedDateTime tarifExamTech;

    @Column(name = "conclusion_exam_tech")
    private String conclusionExamTech;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabExamTeches", allowSetters = true)
    private TabHospital tabHospital;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeExamTech() {
        return typeExamTech;
    }

    public TabExamTech typeExamTech(String typeExamTech) {
        this.typeExamTech = typeExamTech;
        return this;
    }

    public void setTypeExamTech(String typeExamTech) {
        this.typeExamTech = typeExamTech;
    }

    public ZonedDateTime getDateExamTech() {
        return dateExamTech;
    }

    public TabExamTech dateExamTech(ZonedDateTime dateExamTech) {
        this.dateExamTech = dateExamTech;
        return this;
    }

    public void setDateExamTech(ZonedDateTime dateExamTech) {
        this.dateExamTech = dateExamTech;
    }

    public ZonedDateTime getTarifExamTech() {
        return tarifExamTech;
    }

    public TabExamTech tarifExamTech(ZonedDateTime tarifExamTech) {
        this.tarifExamTech = tarifExamTech;
        return this;
    }

    public void setTarifExamTech(ZonedDateTime tarifExamTech) {
        this.tarifExamTech = tarifExamTech;
    }

    public String getConclusionExamTech() {
        return conclusionExamTech;
    }

    public TabExamTech conclusionExamTech(String conclusionExamTech) {
        this.conclusionExamTech = conclusionExamTech;
        return this;
    }

    public void setConclusionExamTech(String conclusionExamTech) {
        this.conclusionExamTech = conclusionExamTech;
    }

    public TabHospital getTabHospital() {
        return tabHospital;
    }

    public TabExamTech tabHospital(TabHospital tabHospital) {
        this.tabHospital = tabHospital;
        return this;
    }

    public void setTabHospital(TabHospital tabHospital) {
        this.tabHospital = tabHospital;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabExamTech)) {
            return false;
        }
        return id != null && id.equals(((TabExamTech) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabExamTech{" +
            "id=" + getId() +
            ", typeExamTech='" + getTypeExamTech() + "'" +
            ", dateExamTech='" + getDateExamTech() + "'" +
            ", tarifExamTech='" + getTarifExamTech() + "'" +
            ", conclusionExamTech='" + getConclusionExamTech() + "'" +
            "}";
    }
}
