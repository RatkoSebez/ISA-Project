import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatInfoCardComponent } from './boat-info-card.component';

describe('BoatInfoCardComponent', () => {
  let component: BoatInfoCardComponent;
  let fixture: ComponentFixture<BoatInfoCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatInfoCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatInfoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
