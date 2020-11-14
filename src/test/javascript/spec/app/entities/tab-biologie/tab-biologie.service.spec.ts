import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabBiologieService } from 'app/entities/tab-biologie/tab-biologie.service';
import { ITabBiologie, TabBiologie } from 'app/shared/model/tab-biologie.model';

describe('Service Tests', () => {
  describe('TabBiologie Service', () => {
    let injector: TestBed;
    let service: TabBiologieService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabBiologie;
    let expectedResult: ITabBiologie | ITabBiologie[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabBiologieService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabBiologie(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateBiologie: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabBiologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateBiologie: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBiologie: currentDate,
          },
          returnedFromService
        );

        service.create(new TabBiologie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabBiologie', () => {
        const returnedFromService = Object.assign(
          {
            dateBiologie: currentDate.format(DATE_TIME_FORMAT),
            nomBiologie: 'BBBBBB',
            nomSerologie: 'BBBBBB',
            gSanguin: 'BBBBBB',
            grSangGeni: 'BBBBBB',
            caryotype: 'BBBBBB',
            tarifBiologie: 1,
            autresBiologie: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBiologie: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabBiologie', () => {
        const returnedFromService = Object.assign(
          {
            dateBiologie: currentDate.format(DATE_TIME_FORMAT),
            nomBiologie: 'BBBBBB',
            nomSerologie: 'BBBBBB',
            gSanguin: 'BBBBBB',
            grSangGeni: 'BBBBBB',
            caryotype: 'BBBBBB',
            tarifBiologie: 1,
            autresBiologie: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateBiologie: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabBiologie', () => {
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
