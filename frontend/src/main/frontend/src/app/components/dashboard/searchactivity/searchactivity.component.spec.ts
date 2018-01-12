import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchactivityComponent } from './searchactivity.component';

describe('SearchactivityComponent', () => {
  let component: SearchactivityComponent;
  let fixture: ComponentFixture<SearchactivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchactivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchactivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
