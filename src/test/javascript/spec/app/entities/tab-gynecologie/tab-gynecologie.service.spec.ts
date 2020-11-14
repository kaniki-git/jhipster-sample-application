import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabGynecologieService } from 'app/entities/tab-gynecologie/tab-gynecologie.service';
import { ITabGynecologie, TabGynecologie } from 'app/shared/model/tab-gynecologie.model';

describe('Service Tests', () => {
  describe('TabGynecologie Service', () => {
    let injector: TestBed;
    let service: TabGynecologieService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabGynecologie;
    let expectedResult: ITabGynecologie | ITabGynecologie[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabGynecologieService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabGynecologie(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabGynecologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            dateFin: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsult: currentDate,
            dateOvulation: currentDate,
            dateFin: currentDate,
          },
          returnedFromService
        );

        service.create(new TabGynecologie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabGynecologie', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            ddr: 'BBBBBB',
            termeUs: 'BBBBBB',
            termCorrige: 'BBBBBB',
            termDdr: 'BBBBBB',
            cycle: 'BBBBBB',
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            ageGestationel: 'BBBBBB',
            dateFin: currentDate.format(DATE_TIME_FORMAT),
            testPeri: 'BBBBBB',
            ecouvillon: 'BBBBBB',
            perine: 'BBBBBB',
            bassin: 'BBBBBB',
            ogtt: 'BBBBBB',
            imc: 'BBBBBB',
            poidsMereInitial: 1,
            poidsMereTheoriBebe: 1,
            tailleMere: 1,
            tailleTheoriBebe: 1,
            gSgMari: 'BBBBBB',
            laboTri21: 'BBBBBB',
            caryotype: 'BBBBBB',
            suiviPar: 'BBBBBB',
            pediatre: 'BBBBBB',
            risqueOrl: 'BBBBBB',
            resumeGrossesse: 'BBBBBB',
            conduiteAccouche: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsult: currentDate,
            dateOvulation: currentDate,
            dateFin: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabGynecologie', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            ddr: 'BBBBBB',
            termeUs: 'BBBBBB',
            termCorrige: 'BBBBBB',
            termDdr: 'BBBBBB',
            cycle: 'BBBBBB',
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            ageGestationel: 'BBBBBB',
            dateFin: currentDate.format(DATE_TIME_FORMAT),
            testPeri: 'BBBBBB',
            ecouvillon: 'BBBBBB',
            perine: 'BBBBBB',
            bassin: 'BBBBBB',
            ogtt: 'BBBBBB',
            imc: 'BBBBBB',
            poidsMereInitial: 1,
            poidsMereTheoriBebe: 1,
            tailleMere: 1,
            tailleTheoriBebe: 1,
            gSgMari: 'BBBBBB',
            laboTri21: 'BBBBBB',
            caryotype: 'BBBBBB',
            suiviPar: 'BBBBBB',
            pediatre: 'BBBBBB',
            risqueOrl: 'BBBBBB',
            resumeGrossesse: 'BBBBBB',
            conduiteAccouche: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsult: currentDate,
            dateOvulation: currentDate,
            dateFin: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabGynecologie', () => {
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
