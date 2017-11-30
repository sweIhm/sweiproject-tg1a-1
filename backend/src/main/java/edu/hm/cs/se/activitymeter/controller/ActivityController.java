package edu.hm.cs.se.activitymeter.controller;
<<<<<<< HEAD
import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

=======

import edu.hm.cs.se.activitymeter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
import org.springframework.web.bind.annotation.RestController;
import edu.hm.cs.se.activitymeter.controller.email.EmailController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
<<<<<<< HEAD
    private ActivityRepository activityRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<Activity> listAll() {
        ArrayList<Activity> activities = new ArrayList<>();
        activityRepository.findAllByPublished(true).forEach(activity -> activities.add(activity));
=======
    private PostRepository activityRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<PostDTO> listAll() {
<<<<<<< HEAD
        ArrayList<Post> activities = new ArrayList<>();
<<<<<<< HEAD
        activityRepository.findAll().forEach(post -> activities.add(post));
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
=======
        activityRepository.findAll().forEach(post -> new PostDTO(activities.add(post)));
>>>>>>> 5721b05... Added EmailController to ActiviyController
=======
        ArrayList<PostDTO> activities = new ArrayList<>();
        activityRepository.findAll().forEach(post -> activities.add(new PostDTO(post)));
>>>>>>> 668e259... fix
        return activities;
    }

    @GetMapping("{id}")
<<<<<<< HEAD
    public Activity find(@PathVariable Long id) {
        return activityRepository.findOne(id);
    }

    @PostMapping
    public ActivityDTO create(@RequestBody Activity input) {
        Activity newActivity = activityRepository.save(new Activity(input.getText(), input.getTitle(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newActivity.getId(), emailController.generateKey()));
        emailController.sendEmail(newActivity, activationKey.getKey());
        return new ActivityDTO(newActivity);
=======
    public PostDTO find(@PathVariable Long id) {
        return new PostDTO(activityRepository.findOne(id));
    }

    @PostMapping
    public PostDTO create(@RequestBody Post input) {
        Post newPost = activityRepository.save(new Post(input.getText(), input.getTitle(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newPost.getId(), emailController.generateKey()));
        emailController.sendEmail(newPost, activationKey.getKey());
        return new PostDTO(newPost);
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        activityRepository.delete(id);
    }

    @PutMapping("{id}")
<<<<<<< HEAD
    public ActivityDTO update(@PathVariable Long id, @RequestBody ActivityDTO input) {
        Activity activity = activityRepository.findOne(id);
        if (activity == null) {
            return null;
        } else {
            activity.setText(input.getText());
            activity.setTitle(input.getTitle());
            activity.setAuthor(input.getAuthor());
            return new ActivityDTO(activityRepository.save(activity));
        }
    }

    @Bean
    public static EmailController newEmailController() {
        return new EmailController();
    }
=======
    public PostDTO update(@PathVariable Long id, @RequestBody PostDTO input) {
        Post post = activityRepository.findOne(id);
        if (post == null) {
            return null;
        } else {
            post.setText(input.getText());
            post.setTitle(input.getTitle());
            post.setAuthor(input.getAuthor());
            return new PostDTO(activityRepository.save(post));
        }
    }

    @Bean
    public static EmailController newEmailController() {
        return new EmailController();
    }

<<<<<<< HEAD
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
}
=======
}
>>>>>>> 5721b05... Added EmailController to ActiviyController
