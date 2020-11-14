import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabRendezvousService } from 'app/entities/tab-rendezvous/tab-rendezvous.service';
import { ITabRendezvous, TabRendezvous } from 'app/shared/model/tab-rendezvous.model';

describe('Service Tests', () => {
  describe('TabRendezvous Service', () => {
    let injector: TestBed;
    let service: TabRendezvousService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabRendezvous;
    let expectedResult: ITabRendezvous | ITabRendezvous[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabRendezvousService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabRendezvous(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        false,
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
            dateDebut: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a TabRendezvous', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDebut: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabRendezvous()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabRendezvous', () => {
        const returnedFromService = Object.assign(
          {
            titre: 'BBBBBB',
            ville: 'BBBBBB',
            dateDebut: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
            jourEntier: true,
            groupId: 'BBBBBB',
            arrierePlanCouleur: 'BBBBBB',
            texteCouleur: 'BBBBBB',
            salle: 'BBBBBB',
            commentaire: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
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

      it('should return a list of TabRendezvous', () => {
        const returnedFromService = Object.assign(
          {
            titre: 'BBBBBB',
            ville: 'BBBBBB',
            dateDebut: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
            jourEntier: true,
            groupId: 'BBBBBB',
            arrierePlanCouleur: 'BBBBBB',
            texteCouleur: 'BBBBBB',
            salle: 'BBBBBB',
            commentaire: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
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

      it('should delete a TabRendezvous', () => {
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
