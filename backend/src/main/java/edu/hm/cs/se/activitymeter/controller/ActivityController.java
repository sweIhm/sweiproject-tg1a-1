package edu.hm.cs.se.activitymeter.controller;
import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.ActivityRepository;
import edu.hm.cs.se.activitymeter.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<Activity> listAll() {
        ArrayList<Activity> activities = new ArrayList<>();
        activityRepository.findAllByPublished(true).forEach(activity -> activities.add(activity));
        return activities;
    }

    @GetMapping("{id}")
    public Activity find(@PathVariable Long id) {
        return activityRepository.findOne(id);
    }

    @PostMapping
    public Activity create(@RequestBody Activity input) {
        Activity newActivity = activityRepository.save(new Activity(input.getText(), input.getTitle(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newActivity.getId(), emailController.generateKey()));
        emailController.sendEmail(newActivity, activationKey.getKey());
        return newActivity;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        activityRepository.delete(id);
    }

    @PutMapping("{id}")
    public Activity update(@PathVariable Long id, @RequestBody Activity input) {
        Activity activity = activityRepository.findOne(id);
        if (activity == null) {
            return null;
        } else {
            activity.setText(input.getText());
            activity.setTitle(input.getTitle());
            activity.setAuthor(input.getAuthor());
            activity.setEmail(input.getEmail());
            activity.setPublished(input.isPublished());
            return activityRepository.save(activity);
        }
    }

    @Bean
    public static EmailController newEmailController() {
        return new EmailController();
    }
}