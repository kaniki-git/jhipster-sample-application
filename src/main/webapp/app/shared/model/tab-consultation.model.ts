import { Moment } from 'moment';
import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { ITabBiologie } from 'app/shared/model/tab-biologie.model';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { ITabExamphys } from 'app/shared/model/tab-examphys.model';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

export interface ITabConsultation {
  id?: number;
  dateConsulte?: Moment;
  provenancePatient?: ProvenancePatient;
  motif?: string;
  affectActuel?: string;
  antecedent?: string;
  traiteHabituel?: string;
  allergie?: string;
  tabac?: boolean;
  alcool?: boolean;
  facteurRisque?: string;
  hypotheseDiag?: string;
  avisMedical?: string;
  ordreMedical?: string;
  traitePropose?: string;
  ledevenirPatient?: LedevenirPatient;
  tarifConsult?: Moment;
  rapport?: string;
  tabParamvitauxes?: ITabParamvitaux[];
  tabBiologies?: ITabBiologie[];
  tabUrgences?: ITabUrgence[];
  tabExamphys?: ITabExamphys[];
  tabPersonnels?: ITabPersonnel[];
  tabHospital?: ITabHospital;
}

export class TabConsultation implements ITabConsultation {
  constructor(
    public id?: number,
    public dateConsulte?: Moment,
    public provenancePatient?: ProvenancePatient,
    public motif?: string,
    public affectActuel?: string,
    public antecedent?: string,
    public traiteHabituel?: string,
    public allergie?: string,
    public tabac?: boolean,
    public alcool?: boolean,
    public facteurRisque?: string,
    public hypotheseDiag?: string,
    public avisMedical?: string,
    public ordreMedical?: string,
    public traitePropose?: string,
    public ledevenirPatient?: LedevenirPatient,
    public tarifConsult?: Moment,
    public rapport?: string,
    public tabParamvitauxes?: ITabParamvitaux[],
    public tabBiologies?: ITabBiologie[],
    public tabUrgences?: ITabUrgence[],
    public tabExamphys?: ITabExamphys[],
    public tabPersonnels?: ITabPersonnel[],
    public tabHospital?: ITabHospital
  ) {
    this.tabac = this.tabac || false;
    this.alcool = this.alcool || false;
  }
}
