import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabAccouchementService } from 'app/entities/tab-accouchement/tab-accouchement.service';
import { ITabAccouchement, TabAccouchement } from 'app/shared/model/tab-accouchement.model';

describe('Service Tests', () => {
  describe('TabAccouchement Service', () => {
    let injector: TestBed;
    let service: TabAccouchementService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabAccouchement;
    let expectedResult: ITabAccouchement | ITabAccouchement[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabAccouchementService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabAccouchement(
        0,
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
            dateAccouche: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabAccouchement', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAccouche: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAccouche: currentDate,
          },
          returnedFromService
        );

        service.create(new TabAccouchement()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabAccouchement', () => {
        const returnedFromService = Object.assign(
          {
            dateAccouche: currentDate.format(DATE_TIME_FORMAT),
            matriculeBebe: 'BBBBBB',
            sexeBebe: 'BBBBBB',
            nomBebe: 'BBBBBB',
            prenon1Bebe: 'BBBBBB',
            prenon2Bebe: 'BBBBBB',
            nomMere: 'BBBBBB',
            ageBebe: 1,
            ta1Bebe: 1,
            ta2Bebe: 1,
            poidsBebe: 'BBBBBB',
            tailleBebe: 'BBBBBB',
            allaitement: 'BBBBBB',
            conclusion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAccouche: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabAccouchement', () => {
        const returnedFromService = Object.assign(
          {
            dateAccouche: currentDate.format(DATE_TIME_FORMAT),
            matriculeBebe: 'BBBBBB',
            sexeBebe: 'BBBBBB',
            nomBebe: 'BBBBBB',
            prenon1Bebe: 'BBBBBB',
            prenon2Bebe: 'BBBBBB',
            nomMere: 'BBBBBB',
            ageBebe: 1,
            ta1Bebe: 1,
            ta2Bebe: 1,
            poidsBebe: 'BBBBBB',
            tailleBebe: 'BBBBBB',
            allaitement: 'BBBBBB',
            conclusion: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAccouche: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabAccouchement', () => {
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
