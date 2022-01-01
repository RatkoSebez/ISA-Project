import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatCardComponent } from './boat-card.component';

describe('BoatCardComponent', () => {
  let component: BoatCardComponent;
  let fixture: ComponentFixture<BoatCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
