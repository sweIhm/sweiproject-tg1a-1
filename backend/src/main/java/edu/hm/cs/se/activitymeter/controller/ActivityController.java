package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.PostDTO;
import edu.hm.cs.se.activitymeter.model.PostRepository;
import edu.hm.cs.se.activitymeter.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import edu.hm.cs.se.activitymeter.controller.email.EmailController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private PostRepository activityRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<PostDTO> listAll() {
        ArrayList<Post> activities = new ArrayList<>();
        activityRepository.findAll().forEach(post -> new PostDTO(activities.add(post)));
        return activities;
    }

    @GetMapping("{id}")
    public PostDTO find(@PathVariable Long id) {
        return new PostDTO(activityRepository.findOne(id));
    }

    @PostMapping
    public PostDTO create(@RequestBody Post input) {
        Post newPost = activityRepository.save(new Post(input.getText(), input.getTitle(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newPost.getId(), emailController.generateKey()));
        emailController.sendEmail(newPost, activationKey.getKey());
        return new PostDTO(newPost);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        activityRepository.delete(id);
    }

    @PutMapping("{id}")
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

}
