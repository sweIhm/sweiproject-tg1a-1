package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.CommentActivationKey;
import org.springframework.data.repository.CrudRepository;

public interface ActivationKeyRepositoryComment extends CrudRepository<CommentActivationKey, Long> {
}
