package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.KeyRepo;
import edu.hm.cs.se.activitymeter.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
    private KeyRepo keyrepo;

    @Autowired
    private PostRepository postrepo;

    @GetMapping("{id}")
    public boolean activate (@PathVariable Long id, @RequestParam(name = "key", defaultValue = "") String key) {
        ActivationKey activationKey = keyrepo.findOne(id);
        if (activationKey != null && key.equals(activationKey.getKey())) {
            activationKey.getPost().setPublished(true);
            postrepo.save(activationKey.getPost());
            keyrepo.delete(id);
            return true;
        }
        return false;
    }
}
