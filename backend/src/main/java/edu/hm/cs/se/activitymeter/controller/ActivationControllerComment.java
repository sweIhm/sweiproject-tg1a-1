package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKeyComment;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepositoryComment;
import edu.hm.cs.se.activitymeter.model.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activation/comment")
public class ActivationControllerComment {

  @Autowired
  private ActivationKeyRepositoryComment keyrepo;

  @Autowired
  private CommentRepository commentRepo;

  @GetMapping("{id}")
  public String activate(@PathVariable Long id, @RequestParam(name = "key",
      defaultValue = "") String key) {
    ActivationKeyComment activationKey = keyrepo.findOne(id);
    boolean published = false;
    if (activationKey != null && key.equals(activationKey.getKey())) {
      activationKey.getComment().setPublished(true);
      EmailController emailController = new EmailController();
      emailController.sendNotification(activationKey.getComment().getPost());
      commentRepo.save(activationKey.getComment());
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
