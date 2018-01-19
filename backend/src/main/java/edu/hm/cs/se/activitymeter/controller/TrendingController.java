package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import edu.hm.cs.se.activitymeter.model.repositories.KeywordRepository;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/trending")
public class TrendingController {

  @Autowired
  PostRepository postRepository;

  @Autowired
  KeywordRepository keywordRepository;

  @GetMapping
  public List<PostDTO> trendingActivities() {
    return postRepository.trending()
        .stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
  }

  @GetMapping("keywords")
  public List<Keyword> trendingTags() {
    return keywordRepository.trending();
  }

  @GetMapping("hm")
  public List<PostDTO> trendingOnHM() {
    return postRepository.trending("hm")
        .stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
  }

  @GetMapping("calpoly")
  public List<PostDTO> trendingOnCalpoly() {
    return postRepository.trending("calpoly")
        .stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
  }
}
