import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabUserProfilService } from 'app/entities/tab-user-profil/tab-user-profil.service';
import { ITabUserProfil, TabUserProfil } from 'app/shared/model/tab-user-profil.model';

describe('Service Tests', () => {
  describe('TabUserProfil Service', () => {
    let injector: TestBed;
    let service: TabUserProfilService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabUserProfil;
    let expectedResult: ITabUserProfil | ITabUserProfil[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabUserProfilService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabUserProfil(0, 0, 0, false, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateModif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateModif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateModif: currentDate,
          },
          returnedFromService
        );

        service.create(new TabUserProfil()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            usrId: 1,
            profileID: 1,
            estActif: true,
            matriculeCreation: 'BBBBBB',
            dateCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateModif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateModif: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            usrId: 1,
            profileID: 1,
            estActif: true,
            matriculeCreation: 'BBBBBB',
            dateCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateModif: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateModif: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabUserProfil', () => {
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
