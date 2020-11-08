import { Moment } from 'moment';
import { ITabPersonnel } from 'app/shared/model/tab-personnel.model';

export interface ITabAccouchement {
  id?: number;
  dateAccouche?: Moment;
  matriculeBebe?: string;
  sexeBebe?: string;
  nomMere?: string;
  nomBebe?: string;
  prenonBebe?: string;
  ageBebe?: number;
  ta1Bebe?: number;
  ta2Bebe?: number;
  poidsBebe?: string;
  tailleBebe?: string;
  allaitement?: string;
  conclusion?: string;
  tabPersonnels?: ITabPersonnel[];
}

export class TabAccouchement implements ITabAccouchement {
  constructor(
    public id?: number,
    public dateAccouche?: Moment,
    public matriculeBebe?: string,
    public sexeBebe?: string,
    public nomMere?: string,
    public nomBebe?: string,
    public prenonBebe?: string,
    public ageBebe?: number,
    public ta1Bebe?: number,
    public ta2Bebe?: number,
    public poidsBebe?: string,
    public tailleBebe?: string,
    public allaitement?: string,
    public conclusion?: string,
    public tabPersonnels?: ITabPersonnel[]
  ) {}
}
