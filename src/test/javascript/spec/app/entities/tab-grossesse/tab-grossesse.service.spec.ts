import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabGrossesseService } from 'app/entities/tab-grossesse/tab-grossesse.service';
import { ITabGrossesse, TabGrossesse } from 'app/shared/model/tab-grossesse.model';

describe('Service Tests', () => {
  describe('TabGrossesse Service', () => {
    let injector: TestBed;
    let service: TabGrossesseService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabGrossesse;
    let expectedResult: ITabGrossesse | ITabGrossesse[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabGrossesseService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabGrossesse(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
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
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabGrossesse', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsult: currentDate,
            dateOvulation: currentDate,
          },
          returnedFromService
        );

        service.create(new TabGrossesse()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabGrossesse', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            ddr: 'BBBBBB',
            termeUs: 'BBBBBB',
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            ageGestationel: 'BBBBBB',
            termCorrige: 'BBBBBB',
            perine: 'BBBBBB',
            bassin: 'BBBBBB',
            ogtt: 'BBBBBB',
            suiviPar: 'BBBBBB',
            imc: 'BBBBBB',
            poidsMereInitial: 1,
            poidsMereTheoriBebe: 1,
            tailleMere: 1,
            tailleTheoriBebe: 1,
            laboTri21: 'BBBBBB',
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
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabGrossesse', () => {
        const returnedFromService = Object.assign(
          {
            dateConsult: currentDate.format(DATE_TIME_FORMAT),
            ddr: 'BBBBBB',
            termeUs: 'BBBBBB',
            dateOvulation: currentDate.format(DATE_TIME_FORMAT),
            ageGestationel: 'BBBBBB',
            termCorrige: 'BBBBBB',
            perine: 'BBBBBB',
            bassin: 'BBBBBB',
            ogtt: 'BBBBBB',
            suiviPar: 'BBBBBB',
            imc: 'BBBBBB',
            poidsMereInitial: 1,
            poidsMereTheoriBebe: 1,
            tailleMere: 1,
            tailleTheoriBebe: 1,
            laboTri21: 'BBBBBB',
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
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabGrossesse', () => {
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
