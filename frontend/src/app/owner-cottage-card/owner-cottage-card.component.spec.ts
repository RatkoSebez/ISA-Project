import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerCottageCardComponent } from './owner-cottage-card.component';

describe('OwnerCottageCardComponent', () => {
  let component: OwnerCottageCardComponent;
  let fixture: ComponentFixture<OwnerCottageCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerCottageCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerCottageCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
