import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiclesComponent } from './acticles.component';

describe('ActiclesComponent', () => {
  let component: ActiclesComponent;
  let fixture: ComponentFixture<ActiclesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActiclesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
