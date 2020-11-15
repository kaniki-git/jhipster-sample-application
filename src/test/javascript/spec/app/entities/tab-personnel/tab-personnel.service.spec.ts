import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabPersonnelService } from 'app/entities/tab-personnel/tab-personnel.service';
import { ITabPersonnel, TabPersonnel } from 'app/shared/model/tab-personnel.model';

describe('Service Tests', () => {
  describe('TabPersonnel Service', () => {
    let injector: TestBed;
    let service: TabPersonnelService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabPersonnel;
    let expectedResult: ITabPersonnel | ITabPersonnel[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabPersonnelService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabPersonnel(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
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
            dateentreeservice: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a TabPersonnel', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateentreeservice: currentDate.format(DATE_TIME_FORMAT),
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateentreeservice: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabPersonnel()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabPersonnel', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            etatCivil: 'BBBBBB',
            typePersonnel: 'BBBBBB',
            activite: 'BBBBBB',
            grade: 'BBBBBB',
            dateentreeservice: currentDate.format(DATE_TIME_FORMAT),
            nomstatut: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateentreeservice: currentDate,
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

      it('should return a list of TabPersonnel', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            etatCivil: 'BBBBBB',
            typePersonnel: 'BBBBBB',
            activite: 'BBBBBB',
            grade: 'BBBBBB',
            dateentreeservice: currentDate.format(DATE_TIME_FORMAT),
            nomstatut: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateentreeservice: currentDate,
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

      it('should delete a TabPersonnel', () => {
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
