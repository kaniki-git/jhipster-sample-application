package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A TabAccouchement.
 */
@Entity
@Table(name = "tab_accouchement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabAccouchement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_accouche")
    private ZonedDateTime dateAccouche;

    @Column(name = "matricule_bebe")
    private String matriculeBebe;

    @Column(name = "sexe_bebe")
    private String sexeBebe;

    @Column(name = "nom_mere")
    private String nomMere;

    @Column(name = "nom_bebe")
    private String nomBebe;

    @Column(name = "prenon_bebe")
    private String prenonBebe;

    @Column(name = "age_bebe")
    private Float ageBebe;

    @Column(name = "ta_1_bebe")
    private Float ta1Bebe;

    @Column(name = "ta_2_bebe")
    private Float ta2Bebe;

    @Column(name = "poids_bebe")
    private String poidsBebe;

    @Column(name = "taille_bebe")
    private String tailleBebe;

    @Column(name = "allaitement")
    private String allaitement;

    @Column(name = "conclusion")
    private String conclusion;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "tab_accouchement_tab_personnel",
               joinColumns = @JoinColumn(name = "tab_accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tab_personnel_id", referencedColumnName = "id"))
    private Set<TabPersonnel> tabPersonnels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateAccouche() {
        return dateAccouche;
    }

    public TabAccouchement dateAccouche(ZonedDateTime dateAccouche) {
        this.dateAccouche = dateAccouche;
        return this;
    }

    public void setDateAccouche(ZonedDateTime dateAccouche) {
        this.dateAccouche = dateAccouche;
    }

    public String getMatriculeBebe() {
        return matriculeBebe;
    }

    public TabAccouchement matriculeBebe(String matriculeBebe) {
        this.matriculeBebe = matriculeBebe;
        return this;
    }

    public void setMatriculeBebe(String matriculeBebe) {
        this.matriculeBebe = matriculeBebe;
    }

    public String getSexeBebe() {
        return sexeBebe;
    }

    public TabAccouchement sexeBebe(String sexeBebe) {
        this.sexeBebe = sexeBebe;
        return this;
    }

    public void setSexeBebe(String sexeBebe) {
        this.sexeBebe = sexeBebe;
    }

    public String getNomMere() {
        return nomMere;
    }

    public TabAccouchement nomMere(String nomMere) {
        this.nomMere = nomMere;
        return this;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getNomBebe() {
        return nomBebe;
    }

    public TabAccouchement nomBebe(String nomBebe) {
        this.nomBebe = nomBebe;
        return this;
    }

    public void setNomBebe(String nomBebe) {
        this.nomBebe = nomBebe;
    }

    public String getPrenonBebe() {
        return prenonBebe;
    }

    public TabAccouchement prenonBebe(String prenonBebe) {
        this.prenonBebe = prenonBebe;
        return this;
    }

    public void setPrenonBebe(String prenonBebe) {
        this.prenonBebe = prenonBebe;
    }

    public Float getAgeBebe() {
        return ageBebe;
    }

    public TabAccouchement ageBebe(Float ageBebe) {
        this.ageBebe = ageBebe;
        return this;
    }

    public void setAgeBebe(Float ageBebe) {
        this.ageBebe = ageBebe;
    }

    public Float getTa1Bebe() {
        return ta1Bebe;
    }

    public TabAccouchement ta1Bebe(Float ta1Bebe) {
        this.ta1Bebe = ta1Bebe;
        return this;
    }

    public void setTa1Bebe(Float ta1Bebe) {
        this.ta1Bebe = ta1Bebe;
    }

    public Float getTa2Bebe() {
        return ta2Bebe;
    }

    public TabAccouchement ta2Bebe(Float ta2Bebe) {
        this.ta2Bebe = ta2Bebe;
        return this;
    }

    public void setTa2Bebe(Float ta2Bebe) {
        this.ta2Bebe = ta2Bebe;
    }

    public String getPoidsBebe() {
        return poidsBebe;
    }

    public TabAccouchement poidsBebe(String poidsBebe) {
        this.poidsBebe = poidsBebe;
        return this;
    }

    public void setPoidsBebe(String poidsBebe) {
        this.poidsBebe = poidsBebe;
    }

    public String getTailleBebe() {
        return tailleBebe;
    }

    public TabAccouchement tailleBebe(String tailleBebe) {
        this.tailleBebe = tailleBebe;
        return this;
    }

    public void setTailleBebe(String tailleBebe) {
        this.tailleBebe = tailleBebe;
    }

    public String getAllaitement() {
        return allaitement;
    }

    public TabAccouchement allaitement(String allaitement) {
        this.allaitement = allaitement;
        return this;
    }

    public void setAllaitement(String allaitement) {
        this.allaitement = allaitement;
    }

    public String getConclusion() {
        return conclusion;
    }

    public TabAccouchement conclusion(String conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Set<TabPersonnel> getTabPersonnels() {
        return tabPersonnels;
    }

    public TabAccouchement tabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
        return this;
    }

    public TabAccouchement addTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.add(tabPersonnel);
        tabPersonnel.getTabAccouchements().add(this);
        return this;
    }

    public TabAccouchement removeTabPersonnel(TabPersonnel tabPersonnel) {
        this.tabPersonnels.remove(tabPersonnel);
        tabPersonnel.getTabAccouchements().remove(this);
        return this;
    }

    public void setTabPersonnels(Set<TabPersonnel> tabPersonnels) {
        this.tabPersonnels = tabPersonnels;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabAccouchement)) {
            return false;
        }
        return id != null && id.equals(((TabAccouchement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabAccouchement{" +
            "id=" + getId() +
            ", dateAccouche='" + getDateAccouche() + "'" +
            ", matriculeBebe='" + getMatriculeBebe() + "'" +
            ", sexeBebe='" + getSexeBebe() + "'" +
            ", nomMere='" + getNomMere() + "'" +
            ", nomBebe='" + getNomBebe() + "'" +
            ", prenonBebe='" + getPrenonBebe() + "'" +
            ", ageBebe=" + getAgeBebe() +
            ", ta1Bebe=" + getTa1Bebe() +
            ", ta2Bebe=" + getTa2Bebe() +
            ", poidsBebe='" + getPoidsBebe() + "'" +
            ", tailleBebe='" + getTailleBebe() + "'" +
            ", allaitement='" + getAllaitement() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            "}";
    }
}
