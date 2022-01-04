import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatsPageComponent } from './boats-page.component';

describe('BoatsPageComponent', () => {
  let component: BoatsPageComponent;
  let fixture: ComponentFixture<BoatsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
