import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendCottageInfoCardComponent } from './weekend-cottage-info-card.component';

describe('WeekendCottageInfoCardComponent', () => {
  let component: WeekendCottageInfoCardComponent;
  let fixture: ComponentFixture<WeekendCottageInfoCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendCottageInfoCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendCottageInfoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
