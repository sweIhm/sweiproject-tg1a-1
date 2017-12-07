package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKeyComment;
import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.dto.CommentDTO;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepositoryComment;
import edu.hm.cs.se.activitymeter.model.repositories.CommentRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity/{id}/comment")
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ActivationKeyRepositoryComment activationKeyRepository;

  @Autowired
  private EmailController emailController;

  @GetMapping
  public ArrayList<CommentDTO> listAll() {
    ArrayList<CommentDTO> comments = new ArrayList<>();
    commentRepository.findAllByPublished(true)
        .forEach(comment -> comments.add(new CommentDTO(comment)));
    return comments;
  }

  @GetMapping("{id}")
  public CommentDTO find(@PathVariable Long id) {
    return new CommentDTO(commentRepository.findOne(id));
  }

  @PostMapping
  public CommentDTO create(@RequestBody Comment input) {
    Comment newComment = commentRepository.save(new Comment(input.getText(), input.getAuthor(),
        input.getEmail(), false));
    ActivationKeyComment activationKey = activationKeyRepository.save(
        new ActivationKeyComment(newComment.getId(), emailController.generateKey()));
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