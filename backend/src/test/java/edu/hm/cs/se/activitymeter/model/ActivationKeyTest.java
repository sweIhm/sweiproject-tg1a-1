package edu.hm.cs.se.activitymeter.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActivationKeyTest {
  @Test
  public void createKey() throws Exception {
    ActivationKey k = new ActivationKey(100L, "12345");
    assertEquals(100L, k.getPostId());
    assertEquals("12345", k.getKey());
  }

  @Test
  public void setterANdGetter() throws Exception {
    ActivationKey k = new ActivationKey(100L, "12345");
    k.setPostId(10L);
    k.setKey("123456");
    assertEquals(10L, k.getPostId());
    assertEquals("123456", k.getKey());
  }

}