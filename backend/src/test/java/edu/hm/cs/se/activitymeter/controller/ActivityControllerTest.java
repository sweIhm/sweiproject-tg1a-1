package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.ActivityMeter;
import edu.hm.cs.se.activitymeter.model.PostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration( classes = { ActivityMeter.class} )
@AutoConfigureMockMvc
public class ActivityControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void listAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/activity")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"text\":\"TestText\",\"title\":\"TestTitel\",\"author\":\"TestAutor\"}]")).andReturn();
    }

    @Test
    public void find() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/activity/1")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"text\":\"TestText\",\"title\":\"TestTitel\",\"author\":\"TestAutor\"}")).andReturn();
    }

    @Test
    public void update() throws Exception {
        String original = "{\"id\":1,\"text\":\"TestText\",\"title\":\"TestTitel\",\"author\":\"TestAutor\"}";
        String update = "{\"id\":1,\"text\":\"TestText2\",\"title\":\"TestTite\",\"author\":\"TestMutter\"}";
        mvc.perform(MockMvcRequestBuilders.put("/activity/1").contentType("application/json").content(update)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(update)).andReturn();
        mvc.perform(MockMvcRequestBuilders.get("/activity/1")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(update)).andReturn();
        mvc.perform(MockMvcRequestBuilders.put("/activity/1").contentType("application/json").content(original)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(original)).andReturn();
    }

}