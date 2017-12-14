package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.ActivationKey;
import org.springframework.data.repository.CrudRepository;

public interface KeyRepo extends CrudRepository<ActivationKey, Long> {
}