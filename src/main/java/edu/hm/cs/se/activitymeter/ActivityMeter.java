package edu.hm.cs.se.activitymeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.edu.hm.cs.se.activitymeter.ActivityController;

@SpringBootApplication
public class ActivityMeter {
    public static void main(String[] args) {
        SpringApplication.run(ActivityController.class, args);
    }
}