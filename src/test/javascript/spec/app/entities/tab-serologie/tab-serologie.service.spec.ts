import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabSerologieService } from 'app/entities/tab-serologie/tab-serologie.service';
import { ITabSerologie, TabSerologie } from 'app/shared/model/tab-serologie.model';

describe('Service Tests', () => {
  describe('TabSerologie Service', () => {
    let injector: TestBed;
    let service: TabSerologieService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabSerologie;
    let expectedResult: ITabSerologie | ITabSerologie[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabSerologieService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabSerologie(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateSerologie: currentDate.format(DATE_TIME_FORMAT),
            tarifSerologie: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabSerologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateSerologie: currentDate.format(DATE_TIME_FORMAT),
            tarifSerologie: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSerologie: currentDate,
            tarifSerologie: currentDate,
          },
          returnedFromService
        );

        service.create(new TabSerologie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabSerologie', () => {
        const returnedFromService = Object.assign(
          {
            dateSerologie: currentDate.format(DATE_TIME_FORMAT),
            grSang: 'BBBBBB',
            grSangGeni: 'BBBBBB',
            caryotype: 'BBBBBB',
            tarifSerologie: currentDate.format(DATE_TIME_FORMAT),
            autres: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSerologie: currentDate,
            tarifSerologie: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabSerologie', () => {
        const returnedFromService = Object.assign(
          {
            dateSerologie: currentDate.format(DATE_TIME_FORMAT),
            grSang: 'BBBBBB',
            grSangGeni: 'BBBBBB',
            caryotype: 'BBBBBB',
            tarifSerologie: currentDate.format(DATE_TIME_FORMAT),
            autres: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSerologie: currentDate,
            tarifSerologie: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabSerologie', () => {
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
