import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabUrgenceService } from 'app/entities/tab-urgence/tab-urgence.service';
import { ITabUrgence, TabUrgence } from 'app/shared/model/tab-urgence.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

describe('Service Tests', () => {
  describe('TabUrgence Service', () => {
    let injector: TestBed;
    let service: TabUrgenceService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabUrgence;
    let expectedResult: ITabUrgence | ITabUrgence[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabUrgenceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabUrgence(0, currentDate, ProvenancePatient.Domicile, LedevenirPatient.TransHopi, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateArriveeUrgence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabUrgence', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateArriveeUrgence: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateArriveeUrgence: currentDate,
          },
          returnedFromService
        );

        service.create(new TabUrgence()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabUrgence', () => {
        const returnedFromService = Object.assign(
          {
            dateArriveeUrgence: currentDate.format(DATE_TIME_FORMAT),
            provenancePatient: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            rapportUrgence: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateArriveeUrgence: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabUrgence', () => {
        const returnedFromService = Object.assign(
          {
            dateArriveeUrgence: currentDate.format(DATE_TIME_FORMAT),
            provenancePatient: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            rapportUrgence: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateArriveeUrgence: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabUrgence', () => {
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
