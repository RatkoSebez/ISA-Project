import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendCottageInfoComponent } from './weekend-cottage-info.component';

describe('WeekendCottageInfoComponent', () => {
  let component: WeekendCottageInfoComponent;
  let fixture: ComponentFixture<WeekendCottageInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendCottageInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendCottageInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
