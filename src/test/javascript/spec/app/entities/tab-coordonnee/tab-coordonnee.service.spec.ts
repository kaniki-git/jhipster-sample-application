import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabCoordonneeService } from 'app/entities/tab-coordonnee/tab-coordonnee.service';
import { ITabCoordonnee, TabCoordonnee } from 'app/shared/model/tab-coordonnee.model';

describe('Service Tests', () => {
  describe('TabCoordonnee Service', () => {
    let injector: TestBed;
    let service: TabCoordonneeService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabCoordonnee;
    let expectedResult: ITabCoordonnee | ITabCoordonnee[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabCoordonneeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabCoordonnee(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
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

      it('should create a TabCoordonnee', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabCoordonnee()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabCoordonnee', () => {
        const returnedFromService = Object.assign(
          {
            quartier: 'BBBBBB',
            commune: 'BBBBBB',
            ville: 'BBBBBB',
            codeVille: 'BBBBBB',
            rue: 'BBBBBB',
            numero: 1,
            telephone: 'BBBBBB',
            portable: 'BBBBBB',
            prevenir: 'BBBBBB',
            fax: 'BBBBBB',
            adresseMail: 'BBBBBB',
            adressePrevenir: 'BBBBBB',
            adresseLibelleLong: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should return a list of TabCoordonnee', () => {
        const returnedFromService = Object.assign(
          {
            quartier: 'BBBBBB',
            commune: 'BBBBBB',
            ville: 'BBBBBB',
            codeVille: 'BBBBBB',
            rue: 'BBBBBB',
            numero: 1,
            telephone: 'BBBBBB',
            portable: 'BBBBBB',
            prevenir: 'BBBBBB',
            fax: 'BBBBBB',
            adresseMail: 'BBBBBB',
            adressePrevenir: 'BBBBBB',
            adresseLibelleLong: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should delete a TabCoordonnee', () => {
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
