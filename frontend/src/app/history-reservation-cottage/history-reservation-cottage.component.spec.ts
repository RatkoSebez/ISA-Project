import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryReservationCottageComponent } from './history-reservation-cottage.component';

describe('HistoryReservationCottageComponent', () => {
  let component: HistoryReservationCottageComponent;
  let fixture: ComponentFixture<HistoryReservationCottageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryReservationCottageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryReservationCottageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
