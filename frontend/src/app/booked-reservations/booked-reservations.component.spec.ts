import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookedReservationsComponent } from './booked-reservations.component';

describe('BookedReservationsComponent', () => {
  let component: BookedReservationsComponent;
  let fixture: ComponentFixture<BookedReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookedReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookedReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
