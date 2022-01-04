import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeForClientComponent } from './home-for-client.component';

describe('HomeForClientComponent', () => {
  let component: HomeForClientComponent;
  let fixture: ComponentFixture<HomeForClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeForClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeForClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
