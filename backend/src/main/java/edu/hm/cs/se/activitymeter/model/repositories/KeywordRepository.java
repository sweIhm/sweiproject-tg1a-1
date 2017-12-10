package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.Keyword;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
  List<Keyword> findAll();

  List<Keyword> findAllByContentIn(Iterable<String> content);
}
