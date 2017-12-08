package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.repositories.KeyRepo;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activation")
public class ActivationController {

  @Autowired
  private ActivationKeyRepository keyrepo;

  @Autowired
  private PostRepository postrepo;

  @GetMapping("{id}")
  public String activate(@PathVariable Long id, @RequestParam(name = "key",
      defaultValue = "") String key) {
    ActivationKey activationKey = keyrepo.findOne(id);
    if (activationKey != null && key.equals(activationKey.getKey())) {
      activationKey.getPost().setPublished(true);
      postrepo.save(activationKey.getPost());
      keyrepo.delete(id);
      return String.format("redirect:/view/%d;alert=activationsucceeded", id);
    }
    return "redirect:/dashboard;alert=activationfailed";
  }
}
