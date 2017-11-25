package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
import edu.hm.cs.se.activitymeter.model.ActivationKeyRepository;
import edu.hm.cs.se.activitymeter.model.Activity;
import edu.hm.cs.se.activitymeter.model.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
    private ActivationKeyRepository keyrepo;

    @Autowired
    private ActivityRepository actirepo;

    @GetMapping("{id}")
    public boolean activate (@PathVariable Long id, @RequestParam(name = "key", defaultValue = "") String key) {
        ActivationKey activationKey = keyrepo.findOne(id);
            if (activationKey != null && key.equals(activationKey.getKey())) {
                activationKey.getActivity().setPublished(true);
                actirepo.save(activationKey.getActivity());
                keyrepo.delete(id);
                return true;
            }
        return false;
    }
}
