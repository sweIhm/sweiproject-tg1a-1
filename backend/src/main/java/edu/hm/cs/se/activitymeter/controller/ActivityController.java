package edu.hm.cs.se.activitymeter.controller;
<<<<<<< HEAD
<<<<<<< HEAD
import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

=======
=======
>>>>>>> 1fe7c08... Sprint 2 Branch

import edu.hm.cs.se.activitymeter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
<<<<<<< HEAD
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
import org.springframework.web.bind.annotation.RestController;
import edu.hm.cs.se.activitymeter.controller.email.EmailController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
<<<<<<< HEAD
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
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
    private PostRepository activityRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<PostDTO> listAll() {
<<<<<<< HEAD
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
<<<<<<< HEAD
        activityRepository.findAll().forEach(post -> activities.add(new PostDTO(post)));
>>>>>>> 668e259... fix
=======
        activityRepository.findAllByPublished(true).forEach(post -> activities.add(new PostDTO(post)));
>>>>>>> eac58b2... Fixed get returning unpublished activities
=======
        ArrayList<PostDTO> activities = new ArrayList<>();
        activityRepository.findAllByPublished(true).forEach(post -> activities.add(new PostDTO(post)));
>>>>>>> 1fe7c08... Sprint 2 Branch
        return activities;
    }

    @GetMapping("{id}")
<<<<<<< HEAD
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
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
    public PostDTO find(@PathVariable Long id) {
        return new PostDTO(activityRepository.findOne(id));
    }

    @PostMapping
    public PostDTO create(@RequestBody Post input) {
        Post newPost = activityRepository.save(new Post(input.getText(), input.getTitle(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newPost.getId(), EmailController.generateKey()));
        emailController.sendEmail(newPost, activationKey.getKey());
        return new PostDTO(newPost);
<<<<<<< HEAD
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        activityRepository.delete(id);
    }

    @PutMapping("{id}")
<<<<<<< HEAD
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
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
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
<<<<<<< HEAD
>>>>>>> fa24110... Added Data Transfer Object (DTO) for Post.
}
=======
}
>>>>>>> 5721b05... Added EmailController to ActiviyController
=======
}
>>>>>>> 1fe7c08... Sprint 2 Branch
