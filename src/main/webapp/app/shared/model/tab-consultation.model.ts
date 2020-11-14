import { Moment } from 'moment';
import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { ITabBiologie } from 'app/shared/model/tab-biologie.model';
import { ITabUrgence } from 'app/shared/model/tab-urgence.model';
import { ITabExamPhys } from 'app/shared/model/tab-exam-phys.model';
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
  tabParamVitauxes?: ITabParamVitaux[];
  tabBiologies?: ITabBiologie[];
  tabUrgences?: ITabUrgence[];
  tabExamPhys?: ITabExamPhys[];
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
    public tabParamVitauxes?: ITabParamVitaux[],
    public tabBiologies?: ITabBiologie[],
    public tabUrgences?: ITabUrgence[],
    public tabExamPhys?: ITabExamPhys[],
    public tabPersonnels?: ITabPersonnel[],
    public tabHospital?: ITabHospital
  ) {
    this.tabac = this.tabac || false;
    this.alcool = this.alcool || false;
  }
}
