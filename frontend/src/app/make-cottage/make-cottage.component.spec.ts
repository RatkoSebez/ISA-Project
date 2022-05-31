import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeCottageComponent } from './make-cottage.component';

describe('MakeCottageComponent', () => {
  let component: MakeCottageComponent;
  let fixture: ComponentFixture<MakeCottageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeCottageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeCottageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
