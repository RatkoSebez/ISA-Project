import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReportComponent } from './boat-report.component';

describe('BoatReportComponent', () => {
  let component: BoatReportComponent;
  let fixture: ComponentFixture<BoatReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
