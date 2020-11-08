import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabPatientService } from 'app/entities/tab-patient/tab-patient.service';
import { ITabPatient, TabPatient } from 'app/shared/model/tab-patient.model';

describe('Service Tests', () => {
  describe('TabPatient Service', () => {
    let injector: TestBed;
    let service: TabPatientService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabPatient;
    let expectedResult: ITabPatient | ITabPatient[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabPatientService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabPatient(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
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
            datenaissance: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a TabPatient', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            datenaissance: currentDate.format(DATE_TIME_FORMAT),
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datenaissance: currentDate,
            datecreation: currentDate,
            datemodif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabPatient()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabPatient', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            sexe: 'BBBBBB',
            etatCivil: 'BBBBBB',
            nombreEnfant: 1,
            nombreGrossesse: 'BBBBBB',
            nom: 'BBBBBB',
            prenom1: 'BBBBBB',
            prenom2: 'BBBBBB',
            datenaissance: currentDate.format(DATE_TIME_FORMAT),
            lieunaissance: 'BBBBBB',
            nationalite: 'BBBBBB',
            activite: 'BBBBBB',
            photoUrl: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datenaissance: currentDate,
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

      it('should return a list of TabPatient', () => {
        const returnedFromService = Object.assign(
          {
            matricule: 'BBBBBB',
            sexe: 'BBBBBB',
            etatCivil: 'BBBBBB',
            nombreEnfant: 1,
            nombreGrossesse: 'BBBBBB',
            nom: 'BBBBBB',
            prenom1: 'BBBBBB',
            prenom2: 'BBBBBB',
            datenaissance: currentDate.format(DATE_TIME_FORMAT),
            lieunaissance: 'BBBBBB',
            nationalite: 'BBBBBB',
            activite: 'BBBBBB',
            photoUrl: 'BBBBBB',
            matriculecreation: 'BBBBBB',
            datecreation: currentDate.format(DATE_TIME_FORMAT),
            matriculemodif: 'BBBBBB',
            datemodif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datenaissance: currentDate,
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

      it('should delete a TabPatient', () => {
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
