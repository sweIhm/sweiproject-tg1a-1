package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.ActivationKeyComment;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepositoryComment;
import edu.hm.cs.se.activitymeter.model.repositories.CommentRepository;
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

  @Autowired
  private ActivationKeyRepositoryComment commentkeyrepo;

  @Autowired
  private CommentRepository commentRepo;

  @Autowired
  private EmailController emailController;

  @GetMapping("{id}")
  public String activatePost(@PathVariable Long id, @RequestParam(name = "key",
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

  @GetMapping("comment/{id}")
  public String activateComment(@PathVariable Long id, @RequestParam(name = "key",
      defaultValue = "") String key) {
    ActivationKeyComment activationKey = commentkeyrepo.findOne(id);
    if (activationKey != null && key.equals(activationKey.getKey())) {
      activationKey.getComment().setPublished(true);
      emailController.sendNotificationMails(activationKey.getComment().getPost(),
              activationKey.getComment());
      commentRepo.save(activationKey.getComment());
      commentkeyrepo.delete(id);
      return String.format("redirect:/view/%d;alert=commentactivationsucceeded",
          activationKey.getComment().getPost().getId());
    }
    return "redirect:/dashboard;alert=commentactivationfailed";
  }

  @GetMapping("{id}/delete")
  public String deletePost(@PathVariable Long id,
      @RequestParam(name = "key", defaultValue = "") String key) {
    ActivationKey activationKey = keyrepo.findOne(id);
    if (activationKey != null && key.equals(activationKey.getKey())) {
      postrepo.delete(activationKey.getPost());
      keyrepo.delete(id);
      return "redirect:/dashboard;alert=deletesucceeded";
    }
    return "redirect:/dashboard;alert=deletefailed";
  }

  @GetMapping("comment/{id}/delete")
  public String deleteComment(@PathVariable Long id, @RequestParam(name = "key",
      defaultValue = "") String key) {
    ActivationKeyComment activationKey = commentkeyrepo.findOne(id);
    if (activationKey != null && key.equals(activationKey.getKey())) {
      commentRepo.delete(activationKey.getComment());
      commentkeyrepo.delete(id);
      return String.format("redirect:/view/%d;alert=commentdeletesucceeded",
          activationKey.getComment().getPost().getId());
    }
    return "redirect:/dashboard;alert=commentdeletefailed";
  }
}
