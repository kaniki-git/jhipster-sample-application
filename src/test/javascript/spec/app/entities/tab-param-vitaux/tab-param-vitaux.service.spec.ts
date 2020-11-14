import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabParamVitauxService } from 'app/entities/tab-param-vitaux/tab-param-vitaux.service';
import { ITabParamVitaux, TabParamVitaux } from 'app/shared/model/tab-param-vitaux.model';

describe('Service Tests', () => {
  describe('TabParamVitaux Service', () => {
    let injector: TestBed;
    let service: TabParamVitauxService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabParamVitaux;
    let expectedResult: ITabParamVitaux | ITabParamVitaux[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabParamVitauxService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabParamVitaux(0, currentDate, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateExam: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabParamVitaux', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateExam: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExam: currentDate,
          },
          returnedFromService
        );

        service.create(new TabParamVitaux()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabParamVitaux', () => {
        const returnedFromService = Object.assign(
          {
            dateExam: currentDate.format(DATE_TIME_FORMAT),
            ta1: 1,
            ta2: 1,
            pouls: 1,
            temperature: 1,
            frequence: 1,
            taille: 1,
            sa02: 1,
            sous: 1,
            poids: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExam: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabParamVitaux', () => {
        const returnedFromService = Object.assign(
          {
            dateExam: currentDate.format(DATE_TIME_FORMAT),
            ta1: 1,
            ta2: 1,
            pouls: 1,
            temperature: 1,
            frequence: 1,
            taille: 1,
            sa02: 1,
            sous: 1,
            poids: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExam: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabParamVitaux', () => {
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
