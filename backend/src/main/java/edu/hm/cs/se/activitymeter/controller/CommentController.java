package edu.hm.cs.se.activitymeter.controller;

import edu.hm.cs.se.activitymeter.controller.email.EmailController;
import edu.hm.cs.se.activitymeter.model.ActivationKeyComment;
import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import edu.hm.cs.se.activitymeter.model.dto.CommentDTO;
import edu.hm.cs.se.activitymeter.model.repositories.ActivationKeyRepositoryComment;
import edu.hm.cs.se.activitymeter.model.repositories.CommentRepository;
import edu.hm.cs.se.activitymeter.model.repositories.PostRepository;

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
@RequestMapping("/api/activity/{id}/comment")
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ActivationKeyRepositoryComment activationKeyRepository;

  @Autowired
  private EmailController emailController;

  @Autowired
  private PostRepository activityRepository;

  @GetMapping
  public ArrayList<CommentDTO> listAll(@PathVariable Long id) {
    ArrayList<CommentDTO> comments = new ArrayList<>();
    Iterable<Comment> publishedComments = commentRepository.findAllByPublished(true);
    for (Comment comment: publishedComments) {
      if (comment.getPost().getId() == id) {
        comments.add(new CommentDTO(comment));
      }
    }
    return comments;
  }

  @GetMapping("{id}")
  public CommentDTO find(@PathVariable Long id) {
    return new CommentDTO(commentRepository.findOne(id));
  }

  @PostMapping
  public CommentDTO create(@PathVariable Long id,@RequestBody Comment input) {
    Post post = activityRepository.findOne(id);
    Comment newComment = commentRepository.save(new Comment(input.getText(), input.getAuthor(),
            input.getEmail(), false, post));
    ActivationKeyComment activationKey = activationKeyRepository.save(
        new ActivationKeyComment(newComment.getId(), emailController.generateKey()));
    emailController.sendActivationMail(newComment, activationKey.getKey());
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
