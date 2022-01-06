import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CottageReservationsComponent } from './cottage-reservations.component';

describe('CottageReservationsComponent', () => {
  let component: CottageReservationsComponent;
  let fixture: ComponentFixture<CottageReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CottageReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CottageReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
