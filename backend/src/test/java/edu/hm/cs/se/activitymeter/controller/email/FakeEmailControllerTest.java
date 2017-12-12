package edu.hm.cs.se.activitymeter.controller.email;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FakeEmailControllerTest {

  @Test
  public void generateKey() throws Exception {
    assertEquals("1234", new FakeEmailController().generateKey());
  }

}