package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.KeyRepo;
import edu.hm.cs.se.activitymeter.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activation")
public class ActivationController {

  @Autowired
  private KeyRepo keyrepo;

  @Autowired
  private PostRepository postrepo;

  @GetMapping("{id}")
  public boolean activate(@PathVariable Long id, @RequestParam(name = "key",
      defaultValue = "") String key) {
    ActivationKey activationKey = keyrepo.findOne(id);
    boolean published = false;
    if (activationKey != null && key.equals(activationKey.getKey())) {
      activationKey.getPost().setPublished(true);
      postrepo.save(activationKey.getPost());
      keyrepo.delete(id);
      published = true;
    }
    return String.format("redirect:/activation/%d/verify?success=%s", id, published);
  }

  @GetMapping("/{id}/verify")
  public String activated() {
    return "../../index.html";
  }
}
