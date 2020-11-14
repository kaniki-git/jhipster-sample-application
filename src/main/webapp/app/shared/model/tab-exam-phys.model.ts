import { Moment } from 'moment';
import { ITabConsultation } from 'app/shared/model/tab-consultation.model';

export interface ITabExamPhys {
  id?: number;
  dateExamPhys?: Moment;
  nomAppareil?: string;
  tete?: string;
  cou?: string;
  bouche?: string;
  thorax?: string;
  ausculationCard?: string;
  ausculationPulmo?: string;
  souffle?: string;
  rate?: string;
  bonchospame?: string;
  percussionThorax?: string;
  abdomen?: string;
  poulsFemoralG?: boolean;
  poulsFemoralD?: boolean;
  poulsPopliteG?: boolean;
  poulsPopliteD?: boolean;
  poulsPedieuxG?: boolean;
  poulsPedieuxD?: boolean;
  poulstibialPostG?: boolean;
  poulstibialPostD?: boolean;
  souffleAbdo?: boolean;
  souffleFemoralG?: boolean;
  souffleFemoralD?: boolean;
  spectPeau?: string;
  examNeuro?: string;
  rapport?: string;
  tabConsultation?: ITabConsultation;
}

export class TabExamPhys implements ITabExamPhys {
  constructor(
    public id?: number,
    public dateExamPhys?: Moment,
    public nomAppareil?: string,
    public tete?: string,
    public cou?: string,
    public bouche?: string,
    public thorax?: string,
    public ausculationCard?: string,
    public ausculationPulmo?: string,
    public souffle?: string,
    public rate?: string,
    public bonchospame?: string,
    public percussionThorax?: string,
    public abdomen?: string,
    public poulsFemoralG?: boolean,
    public poulsFemoralD?: boolean,
    public poulsPopliteG?: boolean,
    public poulsPopliteD?: boolean,
    public poulsPedieuxG?: boolean,
    public poulsPedieuxD?: boolean,
    public poulstibialPostG?: boolean,
    public poulstibialPostD?: boolean,
    public souffleAbdo?: boolean,
    public souffleFemoralG?: boolean,
    public souffleFemoralD?: boolean,
    public spectPeau?: string,
    public examNeuro?: string,
    public rapport?: string,
    public tabConsultation?: ITabConsultation
  ) {
    this.poulsFemoralG = this.poulsFemoralG || false;
    this.poulsFemoralD = this.poulsFemoralD || false;
    this.poulsPopliteG = this.poulsPopliteG || false;
    this.poulsPopliteD = this.poulsPopliteD || false;
    this.poulsPedieuxG = this.poulsPedieuxG || false;
    this.poulsPedieuxD = this.poulsPedieuxD || false;
    this.poulstibialPostG = this.poulstibialPostG || false;
    this.poulstibialPostD = this.poulstibialPostD || false;
    this.souffleAbdo = this.souffleAbdo || false;
    this.souffleFemoralG = this.souffleFemoralG || false;
    this.souffleFemoralD = this.souffleFemoralD || false;
  }
}
