import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabHospitalService } from 'app/entities/tab-hospital/tab-hospital.service';
import { ITabHospital, TabHospital } from 'app/shared/model/tab-hospital.model';
import { ProvenancePatient } from 'app/shared/model/enumerations/provenance-patient.model';
import { LedevenirPatient } from 'app/shared/model/enumerations/ledevenir-patient.model';

describe('Service Tests', () => {
  describe('TabHospital Service', () => {
    let injector: TestBed;
    let service: TabHospitalService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabHospital;
    let expectedResult: ITabHospital | ITabHospital[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabHospitalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabHospital(
        0,
        currentDate,
        'AAAAAAA',
        ProvenancePatient.Domicile,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        LedevenirPatient.TransHopi,
        currentDate,
        'AAAAAAA',
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
            dateAdmission: currentDate.format(DATE_TIME_FORMAT),
            prochainRdv: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabHospital', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAdmission: currentDate.format(DATE_TIME_FORMAT),
            prochainRdv: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdmission: currentDate,
            prochainRdv: currentDate,
          },
          returnedFromService
        );

        service.create(new TabHospital()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabHospital', () => {
        const returnedFromService = Object.assign(
          {
            dateAdmission: currentDate.format(DATE_TIME_FORMAT),
            motifAdmission: 'BBBBBB',
            provenancePatient: 'BBBBBB',
            evolJour: 'BBBBBB',
            evolSynthese: 'BBBBBB',
            plantTherapeute: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            prochainRdv: currentDate.format(DATE_TIME_FORMAT),
            lieuRdv: 'BBBBBB',
            conclusionSejour: 'BBBBBB',
            nomConsultant: 'BBBBBB',
            bilanAdmission: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdmission: currentDate,
            prochainRdv: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabHospital', () => {
        const returnedFromService = Object.assign(
          {
            dateAdmission: currentDate.format(DATE_TIME_FORMAT),
            motifAdmission: 'BBBBBB',
            provenancePatient: 'BBBBBB',
            evolJour: 'BBBBBB',
            evolSynthese: 'BBBBBB',
            plantTherapeute: 'BBBBBB',
            ledevenirPatient: 'BBBBBB',
            prochainRdv: currentDate.format(DATE_TIME_FORMAT),
            lieuRdv: 'BBBBBB',
            conclusionSejour: 'BBBBBB',
            nomConsultant: 'BBBBBB',
            bilanAdmission: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdmission: currentDate,
            prochainRdv: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabHospital', () => {
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
