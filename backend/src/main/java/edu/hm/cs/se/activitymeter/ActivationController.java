package edu.hm.cs.se.activitymeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activation")
public class ActivationController {

    @GetMapping("{key}")
    public void aktivate (@PathVariable Long key) {
    }


}
