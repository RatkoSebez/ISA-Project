import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendCottageReportComponent } from './weekend-cottage-report.component';

describe('WeekendCottageReportComponent', () => {
  let component: WeekendCottageReportComponent;
  let fixture: ComponentFixture<WeekendCottageReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendCottageReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendCottageReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
