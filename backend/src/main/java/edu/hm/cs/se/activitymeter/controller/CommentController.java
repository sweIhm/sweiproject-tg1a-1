package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import edu.hm.cs.se.activitymeter.controller.email.EmailController;

@RestController
@RequestMapping("/activity/{id}/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ActivationKeyRepository activationKeyRepository;

    @Autowired
    private EmailController emailController;

    @GetMapping
    public ArrayList<CommentDTO> listAll() {
        ArrayList<CommentDTO> activities = new ArrayList<>();
        commentRepository.findAllByPublished(true).forEach(post -> activities.add(new CommentDTO(post)));
        return activities;
    }

    @GetMapping("{id}")
    public CommentDTO find(@PathVariable Long id) {
        return new CommentDTO(commentRepository.findOne(id));
    }

    @PostMapping
    public CommentDTO create(@RequestBody Comment input) {
        Comment newComment = commentRepository.save(new Comment(input.getText(), input.getAuthor(), input.getEmail(), false));
        ActivationKey activationKey = activationKeyRepository.save(new ActivationKey(newComment.getId(), emailController.generateKey()));
        emailController.sendEmail(newComment, activationKey.getKey());
        return new CommentDTO(newComment);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        commentRepository.delete(id);
    }

    @PutMapping("{id}")
    public CommentDTO update(@PathVariable Long id, @RequestBody CommentDTO input) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            return null;
        } else {
            comment.setText(input.getText());
            comment.setAuthor(input.getAuthor());
            return new CommentDTO(commentRepository.save(comment));
        }
    }
}
