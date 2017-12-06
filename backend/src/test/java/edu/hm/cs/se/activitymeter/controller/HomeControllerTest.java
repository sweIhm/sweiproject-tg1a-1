package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.ActivityMeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { ActivityMeter.class})
@AutoConfigureMockMvc
public class HomeControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void home() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.forwardedUrl("index.html"));
  }

  @Test
  public void view() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/view/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.forwardedUrl("/index.html"));
  }

  @Test
  public void dashboard() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/dashboard"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.forwardedUrl("/index.html"));
  }

}