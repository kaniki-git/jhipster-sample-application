package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TabUserProfil.
 */
@Entity
@Table(name = "tab_user_profil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUserProfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "usr_id")
    private Integer usrId;

    @Column(name = "profile_id")
    private Integer profileID;

    @Column(name = "est_actif")
    private Boolean estActif;

    @Column(name = "matricule_creation")
    private String matriculeCreation;

    @Column(name = "date_creation")
    private String dateCreation;

    @Column(name = "matricule_modif")
    private String matriculeModif;

    @Column(name = "date_modif")
    private ZonedDateTime dateModif;

    @OneToOne
    @JoinColumn(unique = true)
    private TabUser tabUser;

    @OneToOne
    @JoinColumn(unique = true)
    private TabProfil tabProfil;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public TabUserProfil usrId(Integer usrId) {
        this.usrId = usrId;
        return this;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public Integer getProfileID() {
        return profileID;
    }

    public TabUserProfil profileID(Integer profileID) {
        this.profileID = profileID;
        return this;
    }

    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }

    public Boolean isEstActif() {
        return estActif;
    }

    public TabUserProfil estActif(Boolean estActif) {
        this.estActif = estActif;
        return this;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public String getMatriculeCreation() {
        return matriculeCreation;
    }

    public TabUserProfil matriculeCreation(String matriculeCreation) {
        this.matriculeCreation = matriculeCreation;
        return this;
    }

    public void setMatriculeCreation(String matriculeCreation) {
        this.matriculeCreation = matriculeCreation;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public TabUserProfil dateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getMatriculeModif() {
        return matriculeModif;
    }

    public TabUserProfil matriculeModif(String matriculeModif) {
        this.matriculeModif = matriculeModif;
        return this;
    }

    public void setMatriculeModif(String matriculeModif) {
        this.matriculeModif = matriculeModif;
    }

    public ZonedDateTime getDateModif() {
        return dateModif;
    }

    public TabUserProfil dateModif(ZonedDateTime dateModif) {
        this.dateModif = dateModif;
        return this;
    }

    public void setDateModif(ZonedDateTime dateModif) {
        this.dateModif = dateModif;
    }

    public TabUser getTabUser() {
        return tabUser;
    }

    public TabUserProfil tabUser(TabUser tabUser) {
        this.tabUser = tabUser;
        return this;
    }

    public void setTabUser(TabUser tabUser) {
        this.tabUser = tabUser;
    }

    public TabProfil getTabProfil() {
        return tabProfil;
    }

    public TabUserProfil tabProfil(TabProfil tabProfil) {
        this.tabProfil = tabProfil;
        return this;
    }

    public void setTabProfil(TabProfil tabProfil) {
        this.tabProfil = tabProfil;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TabUserProfil)) {
            return false;
        }
        return id != null && id.equals(((TabUserProfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TabUserProfil{" +
            "id=" + getId() +
            ", usrId=" + getUsrId() +
            ", profileID=" + getProfileID() +
            ", estActif='" + isEstActif() + "'" +
            ", matriculeCreation='" + getMatriculeCreation() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", matriculeModif='" + getMatriculeModif() + "'" +
            ", dateModif='" + getDateModif() + "'" +
            "}";
    }
}
