import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryReservationBoatComponent } from './history-reservation-boat.component';

describe('HistoryReservationBoatComponent', () => {
  let component: HistoryReservationBoatComponent;
  let fixture: ComponentFixture<HistoryReservationBoatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryReservationBoatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryReservationBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
