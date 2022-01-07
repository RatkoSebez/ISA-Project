import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureReservationsComponent } from './adventure-reservations.component';

describe('AdventureReservationsComponent', () => {
  let component: AdventureReservationsComponent;
  let fixture: ComponentFixture<AdventureReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
