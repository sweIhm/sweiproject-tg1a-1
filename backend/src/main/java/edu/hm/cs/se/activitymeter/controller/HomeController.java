package edu.hm.cs.se.activitymeter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "index.html";
  }

  @GetMapping("/view/{id}")
  public String view() {
    return "/index.html";
  }

  @GetMapping("/dashboard")
  public String dashboard() {
    return "/index.html";
  }
}
