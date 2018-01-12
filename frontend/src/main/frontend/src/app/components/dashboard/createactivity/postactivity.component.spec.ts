import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostactivityComponent } from './postactivity.component';

describe('PostactivityComponent', () => {
  let component: PostactivityComponent;
  let fixture: ComponentFixture<PostactivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostactivityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostactivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
