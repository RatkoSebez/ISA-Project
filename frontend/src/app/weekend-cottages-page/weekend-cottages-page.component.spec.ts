import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekendCottagesPageComponent } from './weekend-cottages-page.component';

describe('WeekendCottagesPageComponent', () => {
  let component: WeekendCottagesPageComponent;
  let fixture: ComponentFixture<WeekendCottagesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeekendCottagesPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekendCottagesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
