import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerBoatCardComponent } from './owner-boat-card.component';

describe('OwnerBoatCardComponent', () => {
  let component: OwnerBoatCardComponent;
  let fixture: ComponentFixture<OwnerBoatCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerBoatCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerBoatCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
