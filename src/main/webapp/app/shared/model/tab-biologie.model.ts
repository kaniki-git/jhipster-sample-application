import { Moment } from 'moment';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

export interface ITabBiologie {
  id?: number;
  dateBiologie?: Moment;
  nomBiologie?: string;
  nomSerologie?: string;
  gSanguin?: string;
  grSangGeni?: string;
  caryotype?: string;
  tarifBiologie?: number;
  autresBiologie?: string;
  tabConsultation?: ITabConsultation;
  tabGynecologie?: ITabGynecologie;
}

export class TabBiologie implements ITabBiologie {
  constructor(
    public id?: number,
    public dateBiologie?: Moment,
    public nomBiologie?: string,
    public nomSerologie?: string,
    public gSanguin?: string,
    public grSangGeni?: string,
    public caryotype?: string,
    public tarifBiologie?: number,
    public autresBiologie?: string,
    public tabConsultation?: ITabConsultation,
    public tabGynecologie?: ITabGynecologie
  ) {}
}
