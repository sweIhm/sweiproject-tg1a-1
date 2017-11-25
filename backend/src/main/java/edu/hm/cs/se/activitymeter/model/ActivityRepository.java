package edu.hm.cs.se.activitymeter.model;

import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
	Iterable<Activity> findAllByPublished(boolean published);
}