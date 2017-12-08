package edu.hm.cs.se.activitymeter;

import org.junit.Test;

public class ActivityMeterTest {

  @Test
  public void main() throws Exception {
    ActivityMeter.main(new String[] {
        "--spring.main.web-environment=false"
    });
  }

}