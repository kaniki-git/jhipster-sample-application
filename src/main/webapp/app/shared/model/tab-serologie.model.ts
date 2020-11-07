import { Moment } from 'moment';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { ITabGrossesse } from 'app/shared/model/tab-grossesse.model';

export interface ITabSerologie {
  id?: number;
  dateSerologie?: Moment;
  grSang?: string;
  grSangGeni?: string;
  caryotype?: string;
  tarifSerologie?: Moment;
  autres?: string;
  rapport?: string;
  tabPatient?: ITabPatient;
  tabGrossesse?: ITabGrossesse;
  tabPatient?: ITabPatient;
}

export class TabSerologie implements ITabSerologie {
  constructor(
    public id?: number,
    public dateSerologie?: Moment,
    public grSang?: string,
    public grSangGeni?: string,
    public caryotype?: string,
    public tarifSerologie?: Moment,
    public autres?: string,
    public rapport?: string,
    public tabPatient?: ITabPatient,
    public tabGrossesse?: ITabGrossesse,
    public tabPatient?: ITabPatient
  ) {}
}
