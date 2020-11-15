import { Moment } from 'moment';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

export interface ITabAccouchement {
  id?: number;
  dateAccouche?: Moment;
  matriculeBebe?: string;
  sexeBebe?: string;
  nomBebe?: string;
  prenon1Bebe?: string;
  prenon2Bebe?: string;
  nomMere?: string;
  ageBebe?: number;
  ta1Bebe?: number;
  ta2Bebe?: number;
  poidsBebe?: string;
  tailleBebe?: string;
  allaitement?: string;
  conclusion?: string;
  tabGynecologies?: ITabGynecologie[];
}

export class TabAccouchement implements ITabAccouchement {
  constructor(
    public id?: number,
    public dateAccouche?: Moment,
    public matriculeBebe?: string,
    public sexeBebe?: string,
    public nomBebe?: string,
    public prenon1Bebe?: string,
    public prenon2Bebe?: string,
    public nomMere?: string,
    public ageBebe?: number,
    public ta1Bebe?: number,
    public ta2Bebe?: number,
    public poidsBebe?: string,
    public tailleBebe?: string,
    public allaitement?: string,
    public conclusion?: string,
    public tabGynecologies?: ITabGynecologie[]
  ) {}
}
