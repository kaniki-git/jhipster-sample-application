import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabConsObstService } from 'app/entities/tab-cons-obst/tab-cons-obst.service';
import { ITabConsObst, TabConsObst } from 'app/shared/model/tab-cons-obst.model';

describe('Service Tests', () => {
  describe('TabConsObst Service', () => {
    let injector: TestBed;
    let service: TabConsObstService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabConsObst;
    let expectedResult: ITabConsObst | ITabConsObst[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabConsObstService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabConsObst(
        0,
        currentDate,
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
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateConsObst: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabConsObst', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateConsObst: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsObst: currentDate,
          },
          returnedFromService
        );

        service.create(new TabConsObst()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabConsObst', () => {
        const returnedFromService = Object.assign(
          {
            dateConsObst: currentDate.format(DATE_TIME_FORMAT),
            agePatient: 'BBBBBB',
            poids: 1,
            tas: 1,
            tad: 1,
            a: 1,
            s: 'BBBBBB',
            n: 'BBBBBB',
            hu: 'BBBBBB',
            bc: 'BBBBBB',
            sb: 'BBBBBB',
            oe: 'BBBBBB',
            pres: 'BBBBBB',
            conclusion: 'BBBBBB',
            traitement: 'BBBBBB',
            suiviPar: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsObst: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabConsObst', () => {
        const returnedFromService = Object.assign(
          {
            dateConsObst: currentDate.format(DATE_TIME_FORMAT),
            agePatient: 'BBBBBB',
            poids: 1,
            tas: 1,
            tad: 1,
            a: 1,
            s: 'BBBBBB',
            n: 'BBBBBB',
            hu: 'BBBBBB',
            bc: 'BBBBBB',
            sb: 'BBBBBB',
            oe: 'BBBBBB',
            pres: 'BBBBBB',
            conclusion: 'BBBBBB',
            traitement: 'BBBBBB',
            suiviPar: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsObst: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabConsObst', () => {
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
