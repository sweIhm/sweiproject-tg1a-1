package edu.hm.cs.se.activitymeter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
<<<<<<< HEAD:backend/src/main/java/edu/hm/cs/se/activitymeter/controller/HomeController.java
  @GetMapping
  public String home() {
=======

    @GetMapping("/")
    public String home() {
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.:backend/src/main/java/edu/hm/cs/se/activitymeter/controller/HomeController.java
    return "index.html";
  } 

}
