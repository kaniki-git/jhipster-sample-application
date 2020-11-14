import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TabExamPhysService } from 'app/entities/tab-exam-phys/tab-exam-phys.service';
import { ITabExamPhys, TabExamPhys } from 'app/shared/model/tab-exam-phys.model';

describe('Service Tests', () => {
  describe('TabExamPhys Service', () => {
    let injector: TestBed;
    let service: TabExamPhysService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabExamPhys;
    let expectedResult: ITabExamPhys | ITabExamPhys[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabExamPhysService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TabExamPhys(
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

      it('should create a TabExamPhys', () => {
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

        service.create(new TabExamPhys()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabExamPhys', () => {
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

      it('should return a list of TabExamPhys', () => {
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

      it('should delete a TabExamPhys', () => {
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
