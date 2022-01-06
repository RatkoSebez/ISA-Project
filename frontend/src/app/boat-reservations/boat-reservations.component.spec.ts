import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatReservationsComponent } from './boat-reservations.component';

describe('BoatReservationsComponent', () => {
  let component: BoatReservationsComponent;
  let fixture: ComponentFixture<BoatReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
