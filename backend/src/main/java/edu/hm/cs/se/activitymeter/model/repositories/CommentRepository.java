package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
  Iterable<Comment> findAllByPublished(boolean published);
}