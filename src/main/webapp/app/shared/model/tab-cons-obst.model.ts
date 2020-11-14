import { Moment } from 'moment';
import { ITabGynecologie } from 'app/shared/model/tab-gynecologie.model';

export interface ITabConsObst {
  id?: number;
  dateConsObst?: Moment;
  agePatient?: string;
  poids?: number;
  tas?: number;
  tad?: number;
  a?: number;
  s?: string;
  n?: string;
  hu?: string;
  bc?: string;
  sb?: string;
  oe?: string;
  pres?: string;
  conclusion?: string;
  traitement?: string;
  suiviPar?: string;
  tabGynecologie?: ITabGynecologie;
}

export class TabConsObst implements ITabConsObst {
  constructor(
    public id?: number,
    public dateConsObst?: Moment,
    public agePatient?: string,
    public poids?: number,
    public tas?: number,
    public tad?: number,
    public a?: number,
    public s?: string,
    public n?: string,
    public hu?: string,
    public bc?: string,
    public sb?: string,
    public oe?: string,
    public pres?: string,
    public conclusion?: string,
    public traitement?: string,
    public suiviPar?: string,
    public tabGynecologie?: ITabGynecologie
  ) {}
}
