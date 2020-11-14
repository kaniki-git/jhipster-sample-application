import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabHistoriquePatientService } from 'app/entities/tab-historique-patient/tab-historique-patient.service';
import { ITabHistoriquePatient, TabHistoriquePatient } from 'app/shared/model/tab-historique-patient.model';

describe('Service Tests', () => {
  describe('TabHistoriquePatient Service', () => {
    let injector: TestBed;
    let service: TabHistoriquePatientService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabHistoriquePatient;
    let expectedResult: ITabHistoriquePatient | ITabHistoriquePatient[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabHistoriquePatientService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabHistoriquePatient(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate);
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

      it('should create a TabHistoriquePatient', () => {
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

        service.create(new TabHistoriquePatient()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabHistoriquePatient', () => {
        const returnedFromService = Object.assign(
          {
            numeroDossier: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            matriculeUtilisateur: 'BBBBBB',
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

      it('should return a list of TabHistoriquePatient', () => {
        const returnedFromService = Object.assign(
          {
            numeroDossier: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            matriculeUtilisateur: 'BBBBBB',
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

      it('should delete a TabHistoriquePatient', () => {
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
