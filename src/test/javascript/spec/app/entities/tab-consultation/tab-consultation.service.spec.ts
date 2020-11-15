import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabConsultationService } from 'app/entities/tab-consultation/tab-consultation.service';
import { ITabConsultation, TabConsultation } from 'app/shared/model/tab-consultation.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

describe('Service Tests', () => {
  describe('TabConsultation Service', () => {
    let injector: TestBed;
    let service: TabConsultationService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabConsultation;
    let expectedResult: ITabConsultation | ITabConsultation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabConsultationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabConsultation(
        0,
        currentDate,
        ProvenancePatient.Domicile,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        LedevenirPatient.TransHopi,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateConsulte: currentDate.format(DATE_TIME_FORMAT),
            tarifConsult: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabConsultation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateConsulte: currentDate.format(DATE_TIME_FORMAT),
            tarifConsult: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsulte: currentDate,
            tarifConsult: currentDate,
          },
          returnedFromService
        );

        service.create(new TabConsultation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabConsultation', () => {
        const returnedFromService = Object.assign(
          {
            dateConsulte: currentDate.format(DATE_TIME_FORMAT),
            provenancePatient: 'BBBBBB',
            motif: 'BBBBBB',
            affectActuel: 'BBBBBB',
            antecedent: 'BBBBBB',
            traiteHabituel: 'BBBBBB',
            allergie: 'BBBBBB',
            tabac: true,
            alcool: true,
            facteurRisque: 'BBBBBB',
            hypotheseDiag: 'BBBBBB',
            avisMedical: 'BBBBBB',
            ordreMedical: 'BBBBBB',
            traitePropose: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            tarifConsult: currentDate.format(DATE_TIME_FORMAT),
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsulte: currentDate,
            tarifConsult: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabConsultation', () => {
        const returnedFromService = Object.assign(
          {
            dateConsulte: currentDate.format(DATE_TIME_FORMAT),
            provenancePatient: 'BBBBBB',
            motif: 'BBBBBB',
            affectActuel: 'BBBBBB',
            antecedent: 'BBBBBB',
            traiteHabituel: 'BBBBBB',
            allergie: 'BBBBBB',
            tabac: true,
            alcool: true,
            facteurRisque: 'BBBBBB',
            hypotheseDiag: 'BBBBBB',
            avisMedical: 'BBBBBB',
            ordreMedical: 'BBBBBB',
            traitePropose: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            tarifConsult: currentDate.format(DATE_TIME_FORMAT),
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateConsulte: currentDate,
            tarifConsult: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabConsultation', () => {
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
