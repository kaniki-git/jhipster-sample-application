import { Moment } from 'moment';
import { ITabPatient } from 'app/shared/model/tab-patient.model';
import { ITabExamTech } from 'app/shared/model/tab-exam-tech.model';
import { ITabSerologie } from 'app/shared/model/tab-serologie.model';

export interface ITabGrossesse {
  id?: number;
  dateConsult?: Moment;
  ddr?: string;
  termeUs?: string;
  dateOvulation?: Moment;
  ageGestationel?: string;
  termCorrige?: string;
  perine?: string;
  bassin?: string;
  ogtt?: string;
  suiviPar?: string;
  imc?: string;
  poidsMereInitial?: number;
  poidsMereTheoriBebe?: number;
  tailleMere?: number;
  tailleTheoriBebe?: number;
  laboTri21?: string;
  resumeGrossesse?: string;
  conduiteAccouche?: string;
  rapport?: string;
  tabPatient?: ITabPatient;
  tabExamTeches?: ITabExamTech[];
  tabSerologies?: ITabSerologie[];
}

export class TabGrossesse implements ITabGrossesse {
  constructor(
    public id?: number,
    public dateConsult?: Moment,
    public ddr?: string,
    public termeUs?: string,
    public dateOvulation?: Moment,
    public ageGestationel?: string,
    public termCorrige?: string,
    public perine?: string,
    public bassin?: string,
    public ogtt?: string,
    public suiviPar?: string,
    public imc?: string,
    public poidsMereInitial?: number,
    public poidsMereTheoriBebe?: number,
    public tailleMere?: number,
    public tailleTheoriBebe?: number,
    public laboTri21?: string,
    public resumeGrossesse?: string,
    public conduiteAccouche?: string,
    public rapport?: string,
    public tabPatient?: ITabPatient,
    public tabExamTeches?: ITabExamTech[],
    public tabSerologies?: ITabSerologie[]
  ) {}
}
