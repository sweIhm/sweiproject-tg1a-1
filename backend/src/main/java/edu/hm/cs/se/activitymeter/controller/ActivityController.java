package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.Post;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.repositories.KeywordRepository;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private ActivationKeyRepository activationKeyRepository;

  @Autowired
  private KeywordRepository keywordRepository;

  @Autowired
  private EmailController emailController;

  @GetMapping
  public List<PostDTO> listAll() {
    List<PostDTO> activities = new ArrayList<>();
    postRepository.findAllByPublished(true).forEach(post -> activities.add(new PostDTO(post)));
    return activities;
  }

  @GetMapping("{id}")
  public PostDTO find(@PathVariable Long id) {
    return new PostDTO(postRepository.findOne(id));
  }

  @PostMapping
  public PostDTO create(@RequestBody Post input) {
    Post newPost = postRepository.save(new Post(input.getAuthor(), input.getTitle(),
        input.getText(),input.getEmail(), false));
    ActivationKey activationKey = activationKeyRepository.save(
        new ActivationKey(newPost.getId(), emailController.generateKey()));
    emailController.sendActivationMail(newPost, activationKey.getKey());
    return new PostDTO(newPost);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    String key = emailController.generateKey();
    activationKeyRepository.save(new ActivationKey(id, key));
    emailController.sendDeleteMail(postRepository.findOne(id), key);
  }

  @PutMapping("{id}")
  public PostDTO update(@PathVariable Long id, @RequestBody PostDTO input) {
    Post post = postRepository.findOne(id);
    if (post == null) {
      return null;
    } else {
      post.setText(input.getText());
      post.setTitle(input.getTitle());
      post.setAuthor(input.getAuthor());
      return new PostDTO(postRepository.save(post));
    }
  }

  @GetMapping("tags")
  public List<Keyword> getTags() {
    return keywordRepository.findAll();
  }

  @GetMapping("tags/find")
  public List<Post> getPostsbyTag(@RequestParam(value = "tag",
      defaultValue = "You ain't gonna get anything") List<String> tag) {
    return keywordRepository.findAllByContentIn(tag).stream()
        .flatMap(x -> x.getPosts().stream())
        .filter(x -> tag.stream().allMatch(
            k -> x.getKeywords().stream().anyMatch(c -> c.getContent().equals(k))))
        .distinct()
        .collect(Collectors.toList());
  }
}