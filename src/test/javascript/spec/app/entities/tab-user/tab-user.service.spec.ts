import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabUserService } from 'app/entities/tab-user/tab-user.service';
import { ITabUser, TabUser } from 'app/shared/model/tab-user.model';

describe('Service Tests', () => {
  describe('TabUser Service', () => {
    let injector: TestBed;
    let service: TabUserService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabUser;
    let expectedResult: ITabUser | ITabUser[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabUserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabUser(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            resetDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabUser', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            resetDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            resetDate: currentDate,
          },
          returnedFromService
        );

        service.create(new TabUser()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabUser', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            login: 'BBBBBB',
            mdp: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            dateNaissance: 'BBBBBB',
            genre: 'BBBBBB',
            sexe: 'BBBBBB',
            telephone: 'BBBBBB',
            email: 'BBBBBB',
            estActif: true,
            dMajMdp: 'BBBBBB',
            staConnex: true,
            matriculeCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateCreation: 'BBBBBB',
            dateModif: 'BBBBBB',
            langue: 'BBBBBB',
            resetKey: 'BBBBBB',
            resetDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            resetDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabUser', () => {
        const returnedFromService = Object.assign(
          {
            userId: 1,
            login: 'BBBBBB',
            mdp: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            dateNaissance: 'BBBBBB',
            genre: 'BBBBBB',
            sexe: 'BBBBBB',
            telephone: 'BBBBBB',
            email: 'BBBBBB',
            estActif: true,
            dMajMdp: 'BBBBBB',
            staConnex: true,
            matriculeCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateCreation: 'BBBBBB',
            dateModif: 'BBBBBB',
            langue: 'BBBBBB',
            resetKey: 'BBBBBB',
            resetDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            resetDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabUser', () => {
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
