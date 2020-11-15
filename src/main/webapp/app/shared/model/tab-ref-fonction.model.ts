export interface ITabRefFonction {
  id?: number;
  idFonction?: number;
  libelle?: string;
}

export class TabRefFonction implements ITabRefFonction {
  constructor(public id?: number, public idFonction?: number, public libelle?: string) {}
}
