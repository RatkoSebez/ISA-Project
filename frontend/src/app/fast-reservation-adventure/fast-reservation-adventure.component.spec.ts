import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FastReservationAdventureComponent } from './fast-reservation-adventure.component';

describe('FastReservationAdventureComponent', () => {
  let component: FastReservationAdventureComponent;
  let fixture: ComponentFixture<FastReservationAdventureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FastReservationAdventureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FastReservationAdventureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
