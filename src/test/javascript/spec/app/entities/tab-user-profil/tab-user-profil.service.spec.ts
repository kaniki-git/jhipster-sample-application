import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TabUserProfilService } from 'app/entities/tab-user-profil/tab-user-profil.service';
import { ITabUserProfil, TabUserProfil } from 'app/shared/model/tab-user-profil.model';

describe('Service Tests', () => {
  describe('TabUserProfil Service', () => {
    let injector: TestBed;
    let service: TabUserProfilService;
    let httpMock: HttpTestingController;
    let elemDefault: ITabUserProfil;
    let expectedResult: ITabUserProfil | ITabUserProfil[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TabUserProfilService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TabUserProfil(0, false, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TabUserProfil()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            estActif: true,
            matriculeCreation: 'BBBBBB',
            dateCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateModif: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TabUserProfil', () => {
        const returnedFromService = Object.assign(
          {
            estActif: true,
            matriculeCreation: 'BBBBBB',
            dateCreation: 'BBBBBB',
            matriculeModif: 'BBBBBB',
            dateModif: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

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
