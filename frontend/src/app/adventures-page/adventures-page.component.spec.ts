import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventuresPageComponent } from './adventures-page.component';

describe('AdventuresPageComponent', () => {
  let component: AdventuresPageComponent;
  let fixture: ComponentFixture<AdventuresPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventuresPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventuresPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
