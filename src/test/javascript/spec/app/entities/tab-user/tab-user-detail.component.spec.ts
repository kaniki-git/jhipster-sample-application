import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TabUserDetailComponent } from 'app/entities/tab-user/tab-user-detail.component';
import { TabUser } from 'app/shared/model/tab-user.model';

describe('Component Tests', () => {
  describe('TabUser Management Detail Component', () => {
    let comp: TabUserDetailComponent;
    let fixture: ComponentFixture<TabUserDetailComponent>;
    const route = ({ data: of({ tabUser: new TabUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TabUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TabUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TabUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tabUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tabUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
