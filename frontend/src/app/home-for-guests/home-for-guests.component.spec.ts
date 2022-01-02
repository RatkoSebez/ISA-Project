import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeForGuestsComponent } from './home-for-guests.component';

describe('HomeForGuestsComponent', () => {
  let component: HomeForGuestsComponent;
  let fixture: ComponentFixture<HomeForGuestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeForGuestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeForGuestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
