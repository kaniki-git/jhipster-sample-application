import { Moment } from 'moment';
import { ITabAdministratif } from 'app/shared/model/tab-administratif.model';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

export interface ITabUrgence {
  id?: number;
  dateArriveeUrgence?: Moment;
  provenancePatient?: ProvenancePatient;
  ledevenirPatient?: LedevenirPatient;
  rapportUrgence?: string;
  tabAdministratif?: ITabAdministratif;
  tabConsultation?: ITabConsultation;
}

export class TabUrgence implements ITabUrgence {
  constructor(
    public id?: number,
    public dateArriveeUrgence?: Moment,
    public provenancePatient?: ProvenancePatient,
    public ledevenirPatient?: LedevenirPatient,
    public rapportUrgence?: string,
    public tabAdministratif?: ITabAdministratif,
    public tabConsultation?: ITabConsultation
  ) {}
}
