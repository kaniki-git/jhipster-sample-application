import { Moment } from 'moment';
import { ITabParamvitaux } from 'app/shared/model/tab-paramvitaux.model';
import { ITabBiologie } from 'app/shared/model/tab-biologie.model';
import { ITabConsobst } from 'app/shared/model/tab-consobst.model';
import { ITabAccouchement } from 'app/shared/model/tab-accouchement.model';

export interface ITabGynecologie {
  id?: number;
  ddr?: string;
  termeUs?: string;
  termCorrige?: string;
  termDdr?: string;
  cycle?: string;
  dateOvulation?: Moment;
  ageGestationel?: string;
  dateFin?: Moment;
  testPeri?: string;
  ecouvillon?: string;
  perine?: string;
  bassin?: string;
  ogtt?: string;
  imc?: string;
  poidsMereInitial?: number;
  poidsMereTheoriBebe?: number;
  tailleMere?: number;
  tailleTheoriBebe?: number;
  gSgMari?: string;
  laboTri21?: string;
  caryotype?: string;
  suiviPar?: string;
  pediatre?: string;
  risqueOrl?: string;
  resumeGrossesse?: string;
  conduiteAccouche?: string;
  rapport?: string;
  tabParamvitauxes?: ITabParamvitaux[];
  tabBiologies?: ITabBiologie[];
  tabConsobsts?: ITabConsobst[];
  tabAccouchement?: ITabAccouchement;
}

export class TabGynecologie implements ITabGynecologie {
  constructor(
    public id?: number,
    public ddr?: string,
    public termeUs?: string,
    public termCorrige?: string,
    public termDdr?: string,
    public cycle?: string,
    public dateOvulation?: Moment,
    public ageGestationel?: string,
    public dateFin?: Moment,
    public testPeri?: string,
    public ecouvillon?: string,
    public perine?: string,
    public bassin?: string,
    public ogtt?: string,
    public imc?: string,
    public poidsMereInitial?: number,
    public poidsMereTheoriBebe?: number,
    public tailleMere?: number,
    public tailleTheoriBebe?: number,
    public gSgMari?: string,
    public laboTri21?: string,
    public caryotype?: string,
    public suiviPar?: string,
    public pediatre?: string,
    public risqueOrl?: string,
    public resumeGrossesse?: string,
    public conduiteAccouche?: string,
    public rapport?: string,
    public tabParamvitauxes?: ITabParamvitaux[],
    public tabBiologies?: ITabBiologie[],
    public tabConsobsts?: ITabConsobst[],
    public tabAccouchement?: ITabAccouchement
  ) {}
}
