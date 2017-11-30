package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
<<<<<<< HEAD
import edu.hm.cs.se.activitymeter.model.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.Activity;
import edu.hm.cs.se.activitymeter.model.ActivityRepository;
=======
import edu.hm.cs.se.activitymeter.model.KeyRepo;
import edu.hm.cs.se.activitymeter.model.PostRepository;
>>>>>>> f883b29... email
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
<<<<<<< HEAD
    private ActivationKeyRepository keyrepo;

    @Autowired
    private ActivityRepository actirepo;
=======
    private KeyRepo keyrepo;

    @Autowired
    private PostRepository postrepo;
>>>>>>> f883b29... email

    @GetMapping("{id}")
    public boolean activate (@PathVariable Long id, @RequestParam(name = "key", defaultValue = "") String key) {
        ActivationKey activationKey = keyrepo.findOne(id);
<<<<<<< HEAD
            if (activationKey != null && key.equals(activationKey.getKey())) {
                activationKey.getActivity().setPublished(true);
                actirepo.save(activationKey.getActivity());
                keyrepo.delete(id);
                return true;
            }
        return false;
    }
}
=======
        if (activationKey != null && key.equals(activationKey.getKey())) {
            activationKey.getPost().setPublished(true);
            postrepo.save(activationKey.getPost());
            keyrepo.delete(id);
            return true;
        }
        return false;
    }
}
<<<<<<< HEAD
>>>>>>> f883b29... email
=======
>>>>>>> dcb03df... Missing import added
