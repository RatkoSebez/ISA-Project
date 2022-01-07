import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeComplaintComponent } from './make-complaint.component';

describe('MakeComplaintComponent', () => {
  let component: MakeComplaintComponent;
  let fixture: ComponentFixture<MakeComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeComplaintComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
