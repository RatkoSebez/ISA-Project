import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureInfoPageComponent } from './adventure-info-page.component';

describe('AdventureInfoPageComponent', () => {
  let component: AdventureInfoPageComponent;
  let fixture: ComponentFixture<AdventureInfoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureInfoPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
