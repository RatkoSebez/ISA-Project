import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FastReservationBoatComponent } from './fast-reservation-boat.component';

describe('FastReservationBoatComponent', () => {
  let component: FastReservationBoatComponent;
  let fixture: ComponentFixture<FastReservationBoatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FastReservationBoatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FastReservationBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
