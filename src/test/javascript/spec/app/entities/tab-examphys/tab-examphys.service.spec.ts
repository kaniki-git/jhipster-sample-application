import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabExamphysService } from 'app/entities/tab-examphys/tab-examphys.service';
import { ITabExamphys, TabExamphys } from 'app/shared/model/tab-examphys.model';

describe('Service Tests', () => {
  describe('TabExamphys Service', () => {
    let injector: TestBed;
    let service: TabExamphysService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabExamphys;
    let expectedResult: ITabExamphys | ITabExamphys[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabExamphysService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabExamphys(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateExamPhys: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabExamphys', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateExamPhys: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExamPhys: currentDate,
          },
          returnedFromService
        );

        service.create(new TabExamphys()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabExamphys', () => {
        const returnedFromService = Object.assign(
          {
            dateExamPhys: currentDate.format(DATE_TIME_FORMAT),
            nomAppareil: 'BBBBBB',
            tete: 'BBBBBB',
            cou: 'BBBBBB',
            bouche: 'BBBBBB',
            thorax: 'BBBBBB',
            ausculationCard: 'BBBBBB',
            ausculationPulmo: 'BBBBBB',
            souffle: 'BBBBBB',
            rate: 'BBBBBB',
            bonchospame: 'BBBBBB',
            percussionThorax: 'BBBBBB',
            abdomen: 'BBBBBB',
            poulsFemoralG: true,
            poulsFemoralD: true,
            poulsPopliteG: true,
            poulsPopliteD: true,
            poulsPedieuxG: true,
            poulsPedieuxD: true,
            poulstibialPostG: true,
            poulstibialPostD: true,
            souffleAbdo: true,
            souffleFemoralG: true,
            souffleFemoralD: true,
            spectPeau: 'BBBBBB',
            examNeuro: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExamPhys: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabExamphys', () => {
        const returnedFromService = Object.assign(
          {
            dateExamPhys: currentDate.format(DATE_TIME_FORMAT),
            nomAppareil: 'BBBBBB',
            tete: 'BBBBBB',
            cou: 'BBBBBB',
            bouche: 'BBBBBB',
            thorax: 'BBBBBB',
            ausculationCard: 'BBBBBB',
            ausculationPulmo: 'BBBBBB',
            souffle: 'BBBBBB',
            rate: 'BBBBBB',
            bonchospame: 'BBBBBB',
            percussionThorax: 'BBBBBB',
            abdomen: 'BBBBBB',
            poulsFemoralG: true,
            poulsFemoralD: true,
            poulsPopliteG: true,
            poulsPopliteD: true,
            poulsPedieuxG: true,
            poulsPedieuxD: true,
            poulstibialPostG: true,
            poulstibialPostD: true,
            souffleAbdo: true,
            souffleFemoralG: true,
            souffleFemoralD: true,
            spectPeau: 'BBBBBB',
            examNeuro: 'BBBBBB',
            rapport: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateExamPhys: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TabExamphys', () => {
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
