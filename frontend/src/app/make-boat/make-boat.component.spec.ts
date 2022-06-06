import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeBoatComponent } from './make-boat.component';

describe('MakeBoatComponent', () => {
  let component: MakeBoatComponent;
  let fixture: ComponentFixture<MakeBoatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeBoatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
