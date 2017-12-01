package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
<<<<<<< HEAD
<<<<<<< HEAD
import edu.hm.cs.se.activitymeter.model.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.Activity;
import edu.hm.cs.se.activitymeter.model.ActivityRepository;
=======
import edu.hm.cs.se.activitymeter.model.KeyRepo;
import edu.hm.cs.se.activitymeter.model.PostRepository;
>>>>>>> f883b29... email
=======
import edu.hm.cs.se.activitymeter.model.KeyRepo;
import edu.hm.cs.se.activitymeter.model.PostRepository;
>>>>>>> 1fe7c08... Sprint 2 Branch
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
<<<<<<< HEAD
<<<<<<< HEAD
    private ActivationKeyRepository keyrepo;

    @Autowired
    private ActivityRepository actirepo;
=======
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
    private KeyRepo keyrepo;

    @Autowired
    private PostRepository postrepo;
<<<<<<< HEAD
>>>>>>> f883b29... email
=======
>>>>>>> 1fe7c08... Sprint 2 Branch

    @GetMapping("{id}")
    public boolean activate (@PathVariable Long id, @RequestParam(name = "key", defaultValue = "") String key) {
        ActivationKey activationKey = keyrepo.findOne(id);
<<<<<<< HEAD
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
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
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
<<<<<<< HEAD
>>>>>>> f883b29... email
=======
>>>>>>> dcb03df... Missing import added
=======
>>>>>>> 1fe7c08... Sprint 2 Branch
