export interface ITabRefPays {
  id?: number;
  idPays?: number;
  libelle?: string;
}

export class TabRefPays implements ITabRefPays {
  constructor(public id?: number, public idPays?: number, public libelle?: string) {}
}
