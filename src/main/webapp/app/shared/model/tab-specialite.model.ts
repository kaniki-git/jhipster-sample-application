export interface ITabSpecialite {
  id?: number;
  libelle?: string;
  nomPersonnel?: string;
}

export class TabSpecialite implements ITabSpecialite {
  constructor(public id?: number, public libelle?: string, public nomPersonnel?: string) {}
}
