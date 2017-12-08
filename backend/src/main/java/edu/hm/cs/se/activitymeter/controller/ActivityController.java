package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.Post;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

  @Autowired
  private PostRepository activityRepository;

  @Autowired
  private ActivationKeyRepository activationKeyRepository;

  @Autowired
  private EmailController emailController;

  @GetMapping
  public ArrayList<PostDTO> listAll() {
    ArrayList<PostDTO> activities = new ArrayList<>();
    activityRepository.findAllByPublished(true).forEach(post -> activities.add(new PostDTO(post)));
    return activities;
  }

  @GetMapping("{id}")
  public PostDTO find(@PathVariable Long id) {
    return new PostDTO(activityRepository.findOne(id));
  }

  @PostMapping
  public PostDTO create(@RequestBody Post input) {
    Post newPost = activityRepository.save(new Post(input.getAuthor(), input.getTitle(),
        input.getText(),input.getEmail(), false));
    ActivationKey activationKey = activationKeyRepository.save(
        new ActivationKey(newPost.getId(), emailController.generateKey()));
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
}