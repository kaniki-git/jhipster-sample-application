package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabExamPhys.
 */
@Entity
@Table(name = "tab_exam_phys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabExamPhys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_exam_phys")
    private ZonedDateTime dateExamPhys;

    @Column(name = "nom_appareil")
    private String nomAppareil;

    @Column(name = "tete")
    private String tete;

    @Column(name = "cou")
    private String cou;

    @Column(name = "bouche")
    private String bouche;

    @Column(name = "thorax")
    private String thorax;

    @Column(name = "ausculation_card")
    private String ausculationCard;

    @Column(name = "ausculation_pulmo")
    private String ausculationPulmo;

    @Column(name = "souffle")
    private String souffle;

    @Column(name = "rate")
    private String rate;

    @Column(name = "bonchospame")
    private String bonchospame;

    @Column(name = "percussion_thorax")
    private String percussionThorax;

    @Column(name = "abdomen")
    private String abdomen;

    @Column(name = "pouls_femoral_g")
    private Boolean poulsFemoralG;

    @Column(name = "pouls_femoral_d")
    private Boolean poulsFemoralD;

    @Column(name = "pouls_poplite_g")
    private Boolean poulsPopliteG;

    @Column(name = "pouls_poplite_d")
    private Boolean poulsPopliteD;

    @Column(name = "pouls_pedieux_g")
    private Boolean poulsPedieuxG;

    @Column(name = "pouls_pedieux_d")
    private Boolean poulsPedieuxD;

    @Column(name = "poulstibial_post_g")
    private Boolean poulstibialPostG;

    @Column(name = "poulstibial_post_d")
    private Boolean poulstibialPostD;

    @Column(name = "souffle_abdo")
    private Boolean souffleAbdo;

    @Column(name = "souffle_femoral_g")
    private Boolean souffleFemoralG;

    @Column(name = "souffle_femoral_d")
    private Boolean souffleFemoralD;

    @Column(name = "spect_peau")
    private String spectPeau;

    @Column(name = "exam_neuro")
    private String examNeuro;

    @Column(name = "rapport")
    private String rapport;

    @ManyToOne
    @JsonIgnoreProperties(value = "tabExamPhys", allowSetters = true)
    private TabConsultation tabConsultation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateExamPhys() {
        return dateExamPhys;
    }

    public TabExamPhys dateExamPhys(ZonedDateTime dateExamPhys) {
        this.dateExamPhys = dateExamPhys;
        return this;
    }

    public void setDateExamPhys(ZonedDateTime dateExamPhys) {
        this.dateExamPhys = dateExamPhys;
    }

    public String getNomAppareil() {
        return nomAppareil;
    }

    public TabExamPhys nomAppareil(String nomAppareil) {
        this.nomAppareil = nomAppareil;
        return this;
    }

    public void setNomAppareil(String nomAppareil) {
        this.nomAppareil = nomAppareil;
    }

    public String getTete() {
        return tete;
    }

    public TabExamPhys tete(String tete) {
        this.tete = tete;
        return this;
    }

    public void setTete(String tete) {
        this.tete = tete;
    }

    public String getCou() {
        return cou;
    }

    public TabExamPhys cou(String cou) {
        this.cou = cou;
        return this;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    public String getBouche() {
        return bouche;
    }

    public TabExamPhys bouche(String bouche) {
        this.bouche = bouche;
        return this;
    }

    public void setBouche(String bouche) {
        this.bouche = bouche;
    }

    public String getThorax() {
        return thorax;
    }

    public TabExamPhys thorax(String thorax) {
        this.thorax = thorax;
        return this;
    }

    public void setThorax(String thorax) {
        this.thorax = thorax;
    }

    public String getAusculationCard() {
        return ausculationCard;
    }

    public TabExamPhys ausculationCard(String ausculationCard) {
        this.ausculationCard = ausculationCard;
        return this;
    }

    public void setAusculationCard(String ausculationCard) {
        this.ausculationCard = ausculationCard;
    }

    public String getAusculationPulmo() {
        return ausculationPulmo;
    }

    public TabExamPhys ausculationPulmo(String ausculationPulmo) {
        this.ausculationPulmo = ausculationPulmo;
        return this;
    }

    public void setAusculationPulmo(String ausculationPulmo) {
        this.ausculationPulmo = ausculationPulmo;
    }

    public String getSouffle() {
        return souffle;
    }

    public TabExamPhys souffle(String souffle) {
        this.souffle = souffle;
        return this;
    }

    public void setSouffle(String souffle) {
        this.souffle = souffle;
    }

    public String getRate() {
        return rate;
    }

    public TabExamPhys rate(String rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBonchospame() {
        return bonchospame;
    }

    public TabExamPhys bonchospame(String bonchospame) {
        this.bonchospame = bonchospame;
        return this;
    }

    public void setBonchospame(String bonchospame) {
        this.bonchospame = bonchospame;
    }

    public String getPercussionThorax() {
        return percussionThorax;
    }

    public TabExamPhys percussionThorax(String percussionThorax) {
        this.percussionThorax = percussionThorax;
        return this;
    }

    public void setPercussionThorax(String percussionThorax) {
        this.percussionThorax = percussionThorax;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public TabExamPhys abdomen(String abdomen) {
        this.abdomen = abdomen;
        return this;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public Boolean isPoulsFemoralG() {
        return poulsFemoralG;
    }

    public TabExamPhys poulsFemoralG(Boolean poulsFemoralG) {
        this.poulsFemoralG = poulsFemoralG;
        return this;
    }

    public void setPoulsFemoralG(Boolean poulsFemoralG) {
        this.poulsFemoralG = poulsFemoralG;
    }

    public Boolean isPoulsFemoralD() {
        return poulsFemoralD;
    }

    public TabExamPhys poulsFemoralD(Boolean poulsFemoralD) {
        this.poulsFemoralD = poulsFemoralD;
        return this;
    }

    public void setPoulsFemoralD(Boolean poulsFemoralD) {
        this.poulsFemoralD = poulsFemoralD;
    }

    public Boolean isPoulsPopliteG() {
        return poulsPopliteG;
    }

    public TabExamPhys poulsPopliteG(Boolean poulsPopliteG) {
        this.poulsPopliteG = poulsPopliteG;
        return this;
    }

    public void setPoulsPopliteG(Boolean poulsPopliteG) {
        this.poulsPopliteG = poulsPopliteG;
    }

    public Boolean isPoulsPopliteD() {
        return poulsPopliteD;
    }

    public TabExamPhys poulsPopliteD(Boolean poulsPopliteD) {
        this.poulsPopliteD = poulsPopliteD;
        return this;
    }

    public void setPoulsPopliteD(Boolean poulsPopliteD) {
        this.poulsPopliteD = poulsPopliteD;
    }

    public Boolean isPoulsPedieuxG() {
        return poulsPedieuxG;
    }

    public TabExamPhys poulsPedieuxG(Boolean poulsPedieuxG) {
        this.poulsPedieuxG = poulsPedieuxG;
        return this;
    }

    public void setPoulsPedieuxG(Boolean poulsPedieuxG) {
        this.poulsPedieuxG = poulsPedieuxG;
    }

    public Boolean isPoulsPedieuxD() {
        return poulsPedieuxD;
    }

    public TabExamPhys poulsPedieuxD(Boolean poulsPedieuxD) {
        this.poulsPedieuxD = poulsPedieuxD;
        return this;
    }

    public void setPoulsPedieuxD(Boolean poulsPedieuxD) {
        this.poulsPedieuxD = poulsPedieuxD;
    }

    public Boolean isPoulstibialPostG() {
        return poulstibialPostG;
    }

    public TabExamPhys poulstibialPostG(Boolean poulstibialPostG) {
        this.poulstibialPostG = poulstibialPostG;
        return this;
    }

    public void setPoulstibialPostG(Boolean poulstibialPostG) {
        this.poulstibialPostG = poulstibialPostG;
    }

    public Boolean isPoulstibialPostD() {
        return poulstibialPostD;
    }

    public TabExamPhys poulstibialPostD(Boolean poulstibialPostD) {
        this.poulstibialPostD = poulstibialPostD;
        return this;
    }

    public void setPoulstibialPostD(Boolean poulstibialPostD) {
        this.poulstibialPostD = poulstibialPostD;
    }

    public Boolean isSouffleAbdo() {
        return souffleAbdo;
    }

    public TabExamPhys souffleAbdo(Boolean souffleAbdo) {
        this.souffleAbdo = souffleAbdo;
        return this;
    }

    public void setSouffleAbdo(Boolean souffleAbdo) {
        this.souffleAbdo = souffleAbdo;
    }

    public Boolean isSouffleFemoralG() {
        return souffleFemoralG;
    }

    public TabExamPhys souffleFemoralG(Boolean souffleFemoralG) {
        this.souffleFemoralG = souffleFemoralG;
        return this;
    }

    public void setSouffleFemoralG(Boolean souffleFemoralG) {
        this.souffleFemoralG = souffleFemoralG;
    }

    public Boolean isSouffleFemoralD() {
        return souffleFemoralD;
    }

    public TabExamPhys souffleFemoralD(Boolean souffleFemoralD) {
        this.souffleFemoralD = souffleFemoralD;
        return this;
    }

    public void setSouffleFemoralD(Boolean souffleFemoralD) {
        this.souffleFemoralD = souffleFemoralD;
    }

    public String getSpectPeau() {
        return spectPeau;
    }

    public TabExamPhys spectPeau(String spectPeau) {
        this.spectPeau = spectPeau;
        return this;
    }

    public void setSpectPeau(String spectPeau) {
        this.spectPeau = spectPeau;
    }

    public String getExamNeuro() {
        return examNeuro;
    }

    public TabExamPhys examNeuro(String examNeuro) {
        this.examNeuro = examNeuro;
        return this;
    }

    public void setExamNeuro(String examNeuro) {
        this.examNeuro = examNeuro;
    }

    public String getRapport() {
        return rapport;
    }

    public TabExamPhys rapport(String rapport) {
        this.rapport = rapport;
        return this;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public TabConsultation getTabConsultation() {
        return tabConsultation;
    }

    public TabExamPhys tabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
        return this;
    }

    public void setTabConsultation(TabConsultation tabConsultation) {
        this.tabConsultation = tabConsultation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabExamPhys)) {
            return false;
        }
        return id != null && id.equals(((TabExamPhys) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabExamPhys{" +
            "id=" + getId() +
            ", dateExamPhys='" + getDateExamPhys() + "'" +
            ", nomAppareil='" + getNomAppareil() + "'" +
            ", tete='" + getTete() + "'" +
            ", cou='" + getCou() + "'" +
            ", bouche='" + getBouche() + "'" +
            ", thorax='" + getThorax() + "'" +
            ", ausculationCard='" + getAusculationCard() + "'" +
            ", ausculationPulmo='" + getAusculationPulmo() + "'" +
            ", souffle='" + getSouffle() + "'" +
            ", rate='" + getRate() + "'" +
            ", bonchospame='" + getBonchospame() + "'" +
            ", percussionThorax='" + getPercussionThorax() + "'" +
            ", abdomen='" + getAbdomen() + "'" +
            ", poulsFemoralG='" + isPoulsFemoralG() + "'" +
            ", poulsFemoralD='" + isPoulsFemoralD() + "'" +
            ", poulsPopliteG='" + isPoulsPopliteG() + "'" +
            ", poulsPopliteD='" + isPoulsPopliteD() + "'" +
            ", poulsPedieuxG='" + isPoulsPedieuxG() + "'" +
            ", poulsPedieuxD='" + isPoulsPedieuxD() + "'" +
            ", poulstibialPostG='" + isPoulstibialPostG() + "'" +
            ", poulstibialPostD='" + isPoulstibialPostD() + "'" +
            ", souffleAbdo='" + isSouffleAbdo() + "'" +
            ", souffleFemoralG='" + isSouffleFemoralG() + "'" +
            ", souffleFemoralD='" + isSouffleFemoralD() + "'" +
            ", spectPeau='" + getSpectPeau() + "'" +
            ", examNeuro='" + getExamNeuro() + "'" +
            ", rapport='" + getRapport() + "'" +
            "}";
    }
}
