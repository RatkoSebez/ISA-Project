import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatInfoPageComponent } from './boat-info-page.component';

describe('BoatInfoPageComponent', () => {
  let component: BoatInfoPageComponent;
  let fixture: ComponentFixture<BoatInfoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatInfoPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
