import { Moment } from 'moment';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

export interface ITabHospital {
  id?: number;
  dateAdmission?: Moment;
  motifAdmission?: string;
  provenancePatient?: ProvenancePatient;
  evolJour?: string;
  evolSynthese?: string;
  plantTherapeute?: string;
  ledevenirPatient?: LedevenirPatient;
  prochainRdv?: Moment;
  lieuRdv?: string;
  conclusionSejour?: string;
  nomConsultant?: string;
  bilanAdmission?: string;
  rapport?: string;
  tabConsultations?: ITabConsultation[];
  tabPersonnels?: ITabPersonnel[];
}

export class TabHospital implements ITabHospital {
  constructor(
    public id?: number,
    public dateAdmission?: Moment,
    public motifAdmission?: string,
    public provenancePatient?: ProvenancePatient,
    public evolJour?: string,
    public evolSynthese?: string,
    public plantTherapeute?: string,
    public ledevenirPatient?: LedevenirPatient,
    public prochainRdv?: Moment,
    public lieuRdv?: string,
    public conclusionSejour?: string,
    public nomConsultant?: string,
    public bilanAdmission?: string,
    public rapport?: string,
    public tabConsultations?: ITabConsultation[],
    public tabPersonnels?: ITabPersonnel[]
  ) {}
}
