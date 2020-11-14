import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabAdministratifService } from 'app/entities/tab-administratif/tab-administratif.service';
import { ITabAdministratif, TabAdministratif } from 'app/shared/model/tab-administratif.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';

describe('Service Tests', () => {
  describe('TabAdministratif Service', () => {
    let injector: TestBed;
    let service: TabAdministratifService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabAdministratif;
    let expectedResult: ITabAdministratif | ITabAdministratif[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabAdministratifService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabAdministratif(
        0,
        ProvenancePatient.Domicile,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateSortie: currentDate.format(DATE_TIME_FORMAT),
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabAdministratif', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateSortie: currentDate.format(DATE_TIME_FORMAT),
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEntree: currentDate,
            dateSortie: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabAdministratif()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabAdministratif', () => {
        const returnedFromService = Object.assign(
          {
            provenancePatient: 'BBBBBB',
            numeroChambre: 'BBBBBB',
            batiment: 'BBBBBB',
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateSortie: currentDate.format(DATE_TIME_FORMAT),
            commentaire: 'BBBBBB',
            couverture: 'BBBBBB',
            numerosecu: 'BBBBBB',
            nomassurrance: 'BBBBBB',
            coordonneesecu: 'BBBBBB',
            nomMedecinPerso: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEntree: currentDate,
            dateSortie: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabAdministratif', () => {
        const returnedFromService = Object.assign(
          {
            provenancePatient: 'BBBBBB',
            numeroChambre: 'BBBBBB',
            batiment: 'BBBBBB',
            dateEntree: currentDate.format(DATE_TIME_FORMAT),
            dateSortie: currentDate.format(DATE_TIME_FORMAT),
            commentaire: 'BBBBBB',
            couverture: 'BBBBBB',
            numerosecu: 'BBBBBB',
            nomassurrance: 'BBBBBB',
            coordonneesecu: 'BBBBBB',
            nomMedecinPerso: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEntree: currentDate,
            dateSortie: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabAdministratif', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
