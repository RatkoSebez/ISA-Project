import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FastReservationCottageComponent } from './fast-reservation-cottage.component';

describe('FastReservationCottageComponent', () => {
  let component: FastReservationCottageComponent;
  let fixture: ComponentFixture<FastReservationCottageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FastReservationCottageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FastReservationCottageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
