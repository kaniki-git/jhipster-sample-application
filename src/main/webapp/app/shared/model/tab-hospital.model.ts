import { Moment } from 'moment';
import { ITabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';
import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';

export interface ITabHospital {
  id?: number;
  motifAdmission?: string;
  evolJour?: string;
  evolSynthese?: string;
  plantTherapeute?: string;
  prochainRdv?: Moment;
  lieuRdv?: string;
  conclusionSejour?: string;
  nomConsultant?: string;
  bilanAdmission?: string;
  rapport?: string;
  tabParamVitauxes?: ITabParamVitaux[];
  tabExamTeches?: ITabExamTech[];
  tabConsultations?: ITabConsultation[];
}

export class TabHospital implements ITabHospital {
  constructor(
    public id?: number,
    public motifAdmission?: string,
    public evolJour?: string,
    public evolSynthese?: string,
    public plantTherapeute?: string,
    public prochainRdv?: Moment,
    public lieuRdv?: string,
    public conclusionSejour?: string,
    public nomConsultant?: string,
    public bilanAdmission?: string,
    public rapport?: string,
    public tabParamVitauxes?: ITabParamVitaux[],
    public tabExamTeches?: ITabExamTech[],
    public tabConsultations?: ITabConsultation[]
  ) {}
}
