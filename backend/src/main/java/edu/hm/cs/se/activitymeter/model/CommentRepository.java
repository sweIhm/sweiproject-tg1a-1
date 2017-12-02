package edu.hm.cs.se.activitymeter.model;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByPublished(boolean published);
}