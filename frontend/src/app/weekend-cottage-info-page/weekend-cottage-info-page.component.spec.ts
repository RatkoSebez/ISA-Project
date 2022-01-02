import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendCottageInfoPageComponent } from './weekend-cottage-info-page.component';

describe('WeekendCottageInfoPageComponent', () => {
  let component: WeekendCottageInfoPageComponent;
  let fixture: ComponentFixture<WeekendCottageInfoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendCottageInfoPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendCottageInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
