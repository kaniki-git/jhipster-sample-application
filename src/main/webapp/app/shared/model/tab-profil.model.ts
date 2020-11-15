export interface ITabProfil {
  id?: number;
  profileId?: number;
  libelle?: string;
  estActif?: boolean;
}

export class TabProfil implements ITabProfil {
  constructor(public id?: number, public profileId?: number, public libelle?: string, public estActif?: boolean) {
    this.estActif = this.estActif || false;
  }
}
