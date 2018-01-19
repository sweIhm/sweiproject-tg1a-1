package edu.hm.cs.se.activitymeter.model.repositories;

import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.dto.KeywordDTO;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
  List<Keyword> findAll();

  List<Keyword> findAllByContentIn(Iterable<String> content);

  List<KeywordDTO> countAll();

  @Query(value = "SELECT k.keyword_id, k.content FROM Keyword as k JOIN "
      + "(SELECT kp.keyword_id, count(p.post_id) as amount, sum(p.val) as val FROM Post_Keyword kp"
      + " JOIN (SELECT p.post_id, ((p.views + 1) * 10 * (count(c.post_id) + 1)) * "
      + "(count(c.email) + 1) as val FROM Post as p LEFT JOIN Comment as c "
      + "ON p.post_id = c.post_id WHERE p.published = true GROUP BY p.post_id) as p "
      + "ON kp.post_id = p.post_id GROUP BY kp.keyword_id) as stats "
      + "ON k.keyword_id = stats.keyword_id ORDER BY stats.val * stats.amount DESC LIMIT 5;\n",
      nativeQuery = true)
  List<Keyword> trending();
}
