package edu.hm.cs.se.activitymeter.model;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Kommentar, Long> {
    Iterable<Kommentar> findAllByPublished(boolean published);
}