package edu.hm.cs.se.activitymeter.model;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
  Iterable<Post> findAllByPublished(boolean published);
}