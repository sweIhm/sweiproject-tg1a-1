package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.dto.KeywordDTO;
import edu.hm.cs.se.activitymeter.model.dto.PostDTO;
import edu.hm.cs.se.activitymeter.model.repositories.KeywordRepository;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lookup/")
public class LookupController {

  @Autowired
  private KeywordRepository keywordRepository;

  @Autowired
  private PostRepository postRepository;

  @GetMapping("keywords")
  public List<KeywordDTO> getKeywords() {
    return keywordRepository.countAll();
  }

  @GetMapping("search")
  public List<PostDTO> getPostsbyKeyword(@RequestParam(value = "keywords",
      defaultValue = "") List<String> keywords) {

    keywords = keywordRepository.findAllByContentIn(keywords).parallelStream()
        .map(Keyword::getContent).collect(Collectors.toList());

    return keywords.size() > 0 ? postRepository.searchFor(keywords).stream()
        .map(PostDTO::new)
        .collect(Collectors.toList()) : new ArrayList<>();
  }
}
