import { Moment } from 'moment';
import { ITabHospital } from 'app/shared/model/tab-hospital.model';

export interface ITabExamTech {
  id?: number;
  typeExamTech?: string;
  dateExamTech?: Moment;
  tarifExamTech?: Moment;
  conclusionExamTech?: string;
  tabHospital?: ITabHospital;
}

export class TabExamTech implements ITabExamTech {
  constructor(
    public id?: number,
    public typeExamTech?: string,
    public dateExamTech?: Moment,
    public tarifExamTech?: Moment,
    public conclusionExamTech?: string,
    public tabHospital?: ITabHospital
  ) {}
}
